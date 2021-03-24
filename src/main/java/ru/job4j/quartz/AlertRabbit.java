package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;

public class AlertRabbit {

    private Connection connection;

    public static void main(String[] args) {

        AlertRabbit rabbit = new AlertRabbit();
        Properties properties = rabbit.getProperties();
        try (Connection connection = rabbit.init(properties)) {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("connection", connection);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(Integer.parseInt(
                            properties.getProperty("rabbit.interval")))
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (SchedulerException | SQLException | InterruptedException se) {
            se.printStackTrace();
        }
    }

    private Connection init(Properties properties) {
        try  {
            Class.forName(properties.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    private Properties getProperties() {
        Properties pro = new Properties();
        ClassLoader loader = AlertRabbit.class.getClassLoader();
        try (InputStream is = loader.getResourceAsStream("rabbit.properties")) {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pro;
    }

    public static class Rabbit implements Job {

        @Override
        public void execute(JobExecutionContext context) {
            Connection connection = (Connection) context.getJobDetail().getJobDataMap()
                    .get("connection");
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO rabbit(created_day) VALUES (?);")) {
                statement.setDate(1,
                        new Date(System.currentTimeMillis()));
                statement.executeUpdate();
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
    }
}
