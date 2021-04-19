package ru.job4j.garbagecollection.ref;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 19.04.2021
 */

import org.junit.Test;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class CacheDemoTest {

    @Test
    public void whenGetDataFromStorage() {
        InputStream is = CacheDemo.class.getClassLoader().getResourceAsStream("data/test1.txt");
        String result = new BufferedReader(new InputStreamReader(is))
                    .lines().collect(Collectors.joining("\n"));
            assertThat(result, is("Hello I am simple test"));
    }

    @Test
    public void whenGetDataFromCache() {
        SoftCache<String, String> softCache = new SoftCache<>();
        softCache.put("Key", "Value");
        CacheDemo demo = new CacheDemo(softCache, new FileDataStorage());
        assertThat(demo.get("Key"), is("Value"));
    }

    @Test
    public void whenGetNull() {
        CacheDemo demo = new CacheDemo(new SoftCache(), new FileDataStorage());
        assertNull(demo.get("not.txt"));
    }
}