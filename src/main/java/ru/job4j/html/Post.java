package ru.job4j.html;

import java.util.Calendar;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 29.03.2021
 */

public class Post {

    private int id;
    private String name;
    private String description;
    private String url;
    private Calendar date;

    public Post(int id, String name, String description, String url, Calendar date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

}
