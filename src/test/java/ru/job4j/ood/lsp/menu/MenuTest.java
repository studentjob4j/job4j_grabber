package ru.job4j.ood.lsp.menu;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 04.05.2021
 */

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MenuTest {

    @Test
    public void checkAdd() {
        Menu menu = new Menu();
        boolean result = menu.add(new String("test"));
        assertTrue(result);
    }

    @Test
    public void whenAddSubMenuAndGetName() {
        Menu menu = new Menu();
        menu.add(new String("test 1"));
        menu.addSubMenu("test 1", "test 1.1");
        String result = menu.get("test 1.1");
        assertThat(result, is("test 1.1"));
    }

    @Test
    public void showMenu() {
        Menu menu = new Menu();
        menu.add(new String("Задача 1"));
        menu.add(new String("Задача 2"));
        menu.addSubMenu("Задача 1", "Задача 1.1");
        menu.addSubMenu("Задача 1", "Задача 1.2");
        menu.addSubMenu("Задача 2", "Задача 2.1");
        assertTrue(menu.show());
    }
}