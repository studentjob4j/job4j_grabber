package ru.job4j.ood.tdd;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 21.04.2021
 */

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = x -> x < 0;
        return minmax(comparator, value, predicate);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = x -> x > 0;
        return minmax(comparator, value, predicate);
    }

    public <T> T minmax(Comparator<T> comparator, List<T> value, Predicate<Integer> predicate) {
        T result = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            T temp = value.get(i);
            if (predicate.test(comparator.compare(result, temp))) {
                result = value.get(i);
            }
        }
        return result;
    }
}

