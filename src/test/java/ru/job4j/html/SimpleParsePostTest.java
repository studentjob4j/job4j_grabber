package ru.job4j.html;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleParsePostTest {

    // для запуска сервиса codecov
    @Test
    public void whenGetPost() {
        SimpleParsePost post = new SimpleParsePost();
        assertThat(post.createPostAfterParse(), is(post.getPost()));
    }

}