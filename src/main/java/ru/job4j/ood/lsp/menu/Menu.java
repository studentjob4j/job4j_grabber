package ru.job4j.ood.lsp.menu;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 04.05.2021
 */

import java.util.ArrayList;
import java.util.List;

public class Menu implements AddMenu, GetMenuByName, SetSubMenu {

    private List<BasicMenu> list;

    public Menu() {
        this.list = new ArrayList<>();
    }

    @Override
    public boolean add(String name) {
        boolean res = false;
        if (this.list.size() == 0) {
            this.list.add(new BasicMenu(name));
            res = true;
        } else {
            for (BasicMenu temp : list) {
                if (temp.getName().equals(name)) {
                    break;
                } else {
                    this.list.add(new BasicMenu(name));
                    res = true;
                    break;
                }
            }
        }
        return res;
    }

    @Override
    public String get(String name) {
       String rsl = null;
       if (this.list.size() == 0) {
           throw new UnsupportedOperationException("Not data");
       }
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getName().equals(name)) {
                rsl = this.list.get(i).getName();
                break;
            }
        }
        if (rsl == null) {
            rsl = find(name);
        }
        return rsl;
    }

    @Override
    public boolean addSubMenu(String basicName, String name) {
        boolean res = false;
        for (BasicMenu menu : this.list) {
            if (menu.getName().equals(basicName)) {
                menu.getList().add(new SubMenuOneLevel(name));
                res = true;
                break;
            }
        }
        return res;
    }

    private String find(String name) {
       String res = null;
        int index = 0;
        while (index != list.size()) {
            List<SubMenuOneLevel> temp = this.list.get(index).getList();
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).getName().equals(name)) {
                    res = temp.get(i).getName();
                    index = list.size();
                }
            }
            if (res == null) {
                index++;
            }
        }
        if (res == null) {
            throw new IllegalArgumentException("Please enter correct name");
        }
        return res;
    }

    public boolean show() {
        boolean res = true;
        int count = 0;
        while (count != this.list.size()) {
            BasicMenu temp = this.list.get(count);
            List<SubMenuOneLevel> tmp = temp.getList();
            System.out.println(temp.getName());
            for (SubMenuOneLevel menu : tmp) {
                System.out.println(menu.getName());
            }
            count++;
        }
       return res;
    }
}
