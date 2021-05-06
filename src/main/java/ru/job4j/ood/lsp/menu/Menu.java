package ru.job4j.ood.lsp.menu;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 06.05.2021
 */

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<Action> list;

    public Menu() {
        this.list = new ArrayList<>();
    }

    public List<Action> getList() {
        return list;
    }

    public void addParent(String name) {
        if (this.list.size() == 0) {
            this.list.add(new Item(name));
        } else {
            this.list.add(new Item(name));
        }
    }

    public boolean addChild(String parentName, Item child) {
        boolean check = false;
        for (Action action : this.list) {
            Item item = (Item) action;
            if (item.getName().equals(parentName)) {
                item.getList().add(child);
                check = true;
                break;
            }
        }
       if (!check) {
           throw new IllegalArgumentException("Please check parent name");
       }
       return check;
    }

    public Item get(String name) {
        Item rsl = null;
        if (this.list.size() == 0) {
            throw new UnsupportedOperationException("Not data");
        }
        for (Action action : this.list) {
            Item temp = (Item) action;
            if (temp.getName().equals(name)) {
                rsl = temp;
                break;
            }
        }
        if (rsl == null) {
            rsl = find(name);
        }
        return rsl;
    }

    private Item find(String name) {
        Item res = null;
        int index = 0;
        while (index != list.size()) {
            Item temp = (Item) this.list.get(index);
            List<Item> list = temp.getList();
            for (int i = 0; i < list.size(); i++) {
                Item tmp = list.get(i);
                if (tmp.getName().equals(name)) {
                    res = tmp;
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

    public void print() {
        int count = 0;
        while (count != this.list.size()) {
            Item temp = (Item) this.list.get(count);
            List<Item> tmp = temp.getList();
            System.out.println(temp.getName());
            for (Item menu : tmp) {
                System.out.println(menu.getName());
            }
            count++;
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.addParent("Задача 1");
        menu.addParent("Задача 2");
        menu.addChild("Задача 1", new Item("Задача 1.1"));
        menu.addChild("Задача 1", new Item("Задача 1.2"));
        menu.addChild("Задача 1", new Item("Задача 1.3"));
        menu.addChild("Задача 2", new Item("Задача 2.1"));
        menu.addChild("Задача 2", new Item("Задача 2.2"));
        menu.print();
    }
}


