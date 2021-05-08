package ru.job4j.ood.lsp.menu;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 06.05.2021
 */

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MenuTest {

    @Test
    public void whenAddParent() {
        Menu menu = new Menu(new Item("Basic"));
        assertTrue(menu.addParent("test"));
    }

    @Test
    public void whenAddParentAndChild() {
        Menu menu = new Menu(new Item("Basic"));
        menu.addParent("test");
        boolean result = menu.addChild("test", new Item("child"));
        assertTrue(result);
    }

    @Test
    public void whenGetParent() {
        Menu menu = new Menu(new Item("Basic"));
        menu.addParent("test");
        Item result = menu.get("test");
        assertThat(result, is(new Item("test")));
    }

    @Test
    public void whenGetChild() {
        Menu menu = new Menu(new Item("Basic"));
        menu.addParent("test");
        menu.addChild("test", new Item("child"));
        Item result = menu.get("child");
        assertThat(result, is(new Item("child")));
    }

    @Test
    public void whenGetChildWithWrongName() {
        Menu menu = new Menu(new Item("Basic"));
        menu.addParent("test");
        menu.addChild("test", new Item("child"));
        Item result = menu.get("children");
        assertThat(result, is(new Item("item not found")));
    }
}