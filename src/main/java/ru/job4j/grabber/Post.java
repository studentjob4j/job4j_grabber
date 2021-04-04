package ru.job4j.grabber;

import java.sql.Date;

public class Post {

    private int id;
    private String name;
    private String description;
    private String url;
    private Date date;

    public Post(int id, String name, String desc, String url, Date date) {
        this.id = id;
        this.name = name;
        this.description = desc;
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

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" + "id_post='" + id + '\'' + "name='" + name + '\'' + ","
                + "desc='" + description + '\'' + ","
                + "url='" + url + '\'' + ", date=" + date + '}';
    }
}
