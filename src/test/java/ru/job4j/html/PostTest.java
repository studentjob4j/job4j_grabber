package ru.job4j.html;

import org.junit.Test;
import java.util.Calendar;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PostTest {

    @Test
    public void simpleTestForCodecov() {
        Calendar calendar = Calendar.getInstance();
        Post post = new Post(1, "name", "text", "dot.com", calendar);
        assertThat(post.getName(), is("name"));
    }

}