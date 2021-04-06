package ru.job4j.grabber;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GrabberTest {

    @Test
    public void simpleTestForCodecov() {
        Grabber grabber = new Grabber();
        grabber.cfg();
        String res = grabber.getCfg().getProperty("username");
        assertThat(res, is("postgres"));
    }

}