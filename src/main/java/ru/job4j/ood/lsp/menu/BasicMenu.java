package ru.job4j.ood.lsp.menu;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 04.05.2021
 */

import java.util.ArrayList;
import java.util.List;

public class BasicMenu {

    private List<SubMenuOneLevel> list;
    private String name;

    public BasicMenu(String name) {
        this.name = name;
        this.list = new ArrayList<>();
    }

    public List<SubMenuOneLevel> getList() {
        return list;
    }

    public String getName() {
        return name;
    }
}
