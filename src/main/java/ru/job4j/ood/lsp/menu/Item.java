package ru.job4j.ood.lsp.menu;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 06.05.2021
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Item implements Action {

    private String name;
    private List<Item> list;

    public Item(String name) {
        this.name = name;
        this.list = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Item> getList() {
        return list;
    }

    @Override
    public void action() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(name, item.name) && Objects.equals(list, item.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, list);
    }
}
