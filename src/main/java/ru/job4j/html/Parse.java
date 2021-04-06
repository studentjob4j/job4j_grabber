package ru.job4j.html;

import ru.job4j.grabber.Post;

import java.util.List;

public interface Parse {

    List<Post> list(String link);

    ru.job4j.grabber.Post detail(String link);
}
