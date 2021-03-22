package ru.job4j;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TriggerTest {

    @Test
    public void whenCreateTrigger() {
        Trigger trigger = new Trigger();
        int res = trigger.sum(2, 5);
        assertThat(res, is(7));
    }

}