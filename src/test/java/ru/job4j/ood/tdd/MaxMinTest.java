package ru.job4j.ood.tdd;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 21.04.2021
 */

import org.junit.Test;
import java.util.Comparator;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void whenGetMaxValue() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(1, 5, 3, 4);
        Comparator<Integer> comparator = (o1, o2) -> o1.compareTo(o2);
        int result = maxMin.max(list, comparator);
        assertThat(result, is(5));
    }

    @Test
    public void whenGetMinValue() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(4, 2, 3, 5);
        Comparator<Integer> comparator = (o1, o2) -> o2.compareTo(o1);
        int result = maxMin.min(list, comparator);
        assertThat(result, is(2));
    }
}