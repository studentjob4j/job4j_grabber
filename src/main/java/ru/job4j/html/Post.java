package ru.job4j.html;

import java.util.Calendar;

public class Post {

    private String name;
    private String description;
    private String url;
    private Calendar date;

    public Post(String name, String description, String url, Calendar date) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.date = date;
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
