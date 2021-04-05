package ru.job4j.grabber;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Evgenii Shegai
 * @version 1.0
 * @since 03.04.2021
 */

public class PsqlStore implements Store, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(PsqlStore.class.getName());
    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("driver-class-name"));
            this.cnn = DriverManager.getConnection(cfg.getProperty("url"),
                    cfg.getProperty("username"), cfg.getProperty("password"));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement statement = cnn.prepareStatement(
                "insert into post(name, description, url, date)"
                        + "values (?, ?, ?, ?)")) {
            statement.setString(1, post.getName());
            statement.setString(2, post.getDesc());
            statement.setString(3, post.getUrl());
            statement.setDate(4, post.getDate());
            statement.execute();
        } catch (Exception e) {
           LOG.error(e.getMessage());
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement statement = cnn.prepareStatement(
                "select * from Post")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Post post = new Post(resultSet.getString("name"),
                            resultSet.getString("description"), resultSet.getString("url"),
                            resultSet.getDate("date"));
                    post.setId(resultSet.getInt("id"));
                    posts.add(post);
                }
            }
        } catch (Exception e) {
           LOG.error(e.getMessage());
        }
        return posts;
    }

    @Override
    public Post findById(String id) {
       Post result = new Post();
        try (PreparedStatement statement = cnn.prepareStatement(
                "select * from Post as p where p.id = ?")) {
            statement.setInt(1, Integer.parseInt(id));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                   result = new Post(resultSet.getString("name"),
                            resultSet.getString("description"), resultSet.getString("url"),
                            resultSet.getDate("date"));
                    result.setId(resultSet.getInt("id"));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return result;
    }

    @Override
    public void close() {
        if (cnn != null) {
            try {
                cnn.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
        }
    }

    private static Properties getProperties() {
        Properties pro = new Properties();
        ClassLoader loader = PsqlStore.class.getClassLoader();
        try (InputStream is = loader.getResourceAsStream("psql.properties")) {
            pro.load(is);
        } catch (IOException e) {
           LOG.error(e.getMessage());
        }
        return pro;
    }

    public void createTable(String tablename) {
        try (Statement statement = cnn.createStatement()) {
        String temp = String.format("create table if not exists %s (%s, %s, %s, %s, %s);",
                tablename,
                "id serial primary key", "name varchar(250)",
                "description text", "url varchar(250) unique", "date Date");
                 statement.execute(temp);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        PsqlStore store = new PsqlStore(getProperties());
        store.createTable("post");
        Date date = new Date(121, 3, 3);
        store.save(new Post(
                "one", "firstpost", "www.google.com",
                date));
        store.save(new Post("two", "secondpost", "www.mail.ru",
                date));
        Post result = store.findById("1");
        System.out.println(result);
        List<Post> res = store.getAll();
        for (Post temp : res) {
            System.out.println(temp);
        }
        store.close();
    }
}
