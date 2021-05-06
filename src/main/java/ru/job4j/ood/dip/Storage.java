package ru.job4j.ood.dip;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 06.05.2021
 * This class shows incorrect use dip principle
 */

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private Potato potato;
    private List<Potato> list;

    public Storage(List<Potato> list) {
        this.list = new ArrayList<>();
    }

    public void add(Potato potato) {

    }

    public Potato get(int value) {
        return null;
    }
}
