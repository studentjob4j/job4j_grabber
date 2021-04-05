package ru.job4j.grabber;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import ru.job4j.html.Post;
import ru.job4j.html.SqlRuParse;
import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import ru.job4j.html.Parse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Evgenii Shegai
 * @version 1.0
 * @since 05.04.2021
 */

public class Grabber implements Grab {

    private static final Logger LOG = LoggerFactory.getLogger(Grabber.class.getName());
    private final Properties cfg = new Properties();

    public Store store(String tableName) {
        PsqlStore store = new PsqlStore(this.cfg);
        store.createTable(tableName);
        return store;
    }

    public Scheduler scheduler() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        return scheduler;
    }

    public void cfg() {
        ClassLoader loader = PsqlStore.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("psql.properties")) {
            cfg.load(in);
        } catch (IOException e) {
          LOG.error(e.getMessage());
        }
    }

    @Override
    public void init(Parse parse, Store store, Scheduler scheduler) {
        JobDataMap data = new JobDataMap();
        data.put("store", store);
        data.put("parse", parse);
        JobDetail job = newJob(GrabJob.class)
                .usingJobData(data)
                .build();
        SimpleScheduleBuilder times = simpleSchedule()
                .withIntervalInSeconds(Integer.parseInt(cfg.getProperty("time")))
                .repeatForever();
        Trigger trigger = newTrigger()
                .startNow()
                .withSchedule(times)
                .build();
        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage());
        }

    }

    public static class GrabJob implements Job {

        @Override
        public void execute(JobExecutionContext context) {
            JobDataMap map = context.getJobDetail().getJobDataMap();
            Store store = (Store) map.get("store");
            Parse parse = (Parse) map.get("parse");
            List<Post> list = parse.list("https://www.sql.ru/forum/job-offers");
            List<ru.job4j.grabber.Post> result = changeTypeDate(list);
            for (ru.job4j.grabber.Post post : result) {
                store.save(post);
            }
        }
    }

    private  static List<ru.job4j.grabber.Post> changeTypeDate(List<Post> list) {
        List<ru.job4j.grabber.Post> result = new ArrayList<>();
        for (Post post : list) {
            Calendar calendar = post.getDate();
            Date date = new Date(calendar.getTimeInMillis());
            date.setMonth(date.getMonth() - 1);
            ru.job4j.grabber.Post post2 = new ru.job4j.grabber.Post(post.getName(),
                    post.getDescription(), post.getUrl(), date);
            result.add(post2);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        Grabber grab = new Grabber();
        grab.cfg();
        Scheduler scheduler = grab.scheduler();
        Store store = grab.store("post");
        grab.init(new SqlRuParse(), store, scheduler);
    }
}
