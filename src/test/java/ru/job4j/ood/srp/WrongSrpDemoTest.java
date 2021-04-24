package ru.job4j.ood.srp;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 24.04.2021
 */

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class WrongSrpDemoTest {

    @Test
    public void whenGetNumber() {
        WrongSrpDemo demo = new WrongSrpDemo();
        int result = demo.square(2, 5);
        assertThat(result, is(49));
    }
}