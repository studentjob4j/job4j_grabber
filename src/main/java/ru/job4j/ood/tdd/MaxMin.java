package ru.job4j.ood.tdd;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 21.04.2021
 */

import java.util.Comparator;
import java.util.List;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return minmax(comparator, value);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return minmax(comparator, value);
    }

    public <T> T minmax(Comparator<T> comparator, List<T> value) {
        T result = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            T temp = value.get(i);
            if (comparator.compare(result, temp) < 0) {
                result = value.get(i);
            }
        }
        return result;
    }
}

