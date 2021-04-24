package ru.job4j.ood.template;

/**
 * @author Evgenii Shegai
 * @version 1.0
 * @since 23.04.2021
 */

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GeneratorDemoTest {

    private final String template = "I am a ${name}, Who are ${subject}?";

   /* @Test
    public void whenGetValueByKey() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "John");
        map.put("subject", "you");
        String result = new GeneratorDemo().produce(template, map);
        assertThat(result, is("I am a John, Who are you"));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenKeyNotExist() {
        Map<String, String> map = new HashMap<>();
        map.put("anykey", "John");
        map.put("we", "you");
        String result = new GeneratorDemo().produce(template, map);
    }

// when map have more then 2 keys and values  throw exception
    @Test (expected = UnsupportedOperationException.class)
    public void whenMapHaveMoreThenTwoKey() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "John");
        map.put("key", "value");
        map.put("subject", "you");
        String result = new GeneratorDemo().produce(template, map);
    }*/
}