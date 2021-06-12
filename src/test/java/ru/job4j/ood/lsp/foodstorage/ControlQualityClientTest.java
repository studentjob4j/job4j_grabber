package ru.job4j.ood.lsp.foodstorage;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 1.05.2021
 */

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class ControlQualityClientTest {

    private List<Store> list;
    private Milk milk;
    private Strawberry strawberry;
    private Meat meat;
    private Onion onion;
    private ControlQualityClient client;

   @Before
   public void createStorageAndProducts() {
       Warehouse warehouse = new Warehouse();
       Shop shop = new Shop();
       Trash trash = new Trash();
       list = List.of(warehouse, shop, trash);
       this.client = new ControlQualityClient();
   }

    @Test
    public void whenSetFoodInWarehouse() {
        LocalDate created = LocalDate.of(2021, 6, 10);
        LocalDate expire = LocalDate.of(2021, 7, 9);
        this.milk = new Milk("Milk", expire, created, 100, 0);
        client.setStore(list);
        client.action(milk);
        Warehouse warehouse = (Warehouse) client.getStore().get(0);
        assertThat(warehouse.getStorage().get(0), is(milk));
    }

    @Test
    public void whenSetFoodInShopWithDiscount() {
        LocalDate created = LocalDate.of(2021, 5, 25);
        LocalDate expire = LocalDate.of(2021, 6, 16);
        this.strawberry = new Strawberry("Strawberry", expire, created, 300, 12);
        client.setStore(list);
        client.action(strawberry);
        Shop shop = (Shop) client.getStore().get(1);
        assertThat(shop.getStorage().get(0).getPrice(), is(288.0));
    }

    @Test
    public void whenSetFoodInShopWithoutDiscount() {
        LocalDate created = LocalDate.of(2021, 5, 29);
        LocalDate expire = LocalDate.of(2021, 6, 19);
        this.meat = new Meat("Meat", expire, created, 400, 0);
        client.setStore(list);
        client.action(meat);
        Shop shop = (Shop) client.getStore().get(1);
        assertThat(shop.getStorage().get(0).getPrice(), is(400.0));
    }

    @Test
    public void whenSetFoodInTrash() {
        LocalDate created = LocalDate.of(2021, 5, 19);
        LocalDate expire = LocalDate.of(2021, 6, 10);
        this.onion = new Onion("Onion", expire, created, 40, 0);
        client.setStore(list);
        client.action(onion);
        Trash trash = (Trash) client.getStore().get(2);
        assertThat(trash.getStorage().get(0).getName(), is("Onion"));
    }

    @Test
    public void whenResortFood() {
        LocalDate created = LocalDate.of(2021, 6, 2);
        LocalDate expire = LocalDate.of(2021, 7, 13);
        this.strawberry = new Strawberry("strawberry", expire, created, 200, 32);
        client.setStore(list);
        client.action(strawberry);
        Shop shop = (Shop) client.getStore().get(1);
        LocalDate newExpire = LocalDate.of(2021, 6, 10);
        shop.getStorage().get(0).setExpireDate(newExpire);
        client.resort();
        Trash trash = (Trash) client.getStore().get(2);
        assertThat(trash.getStorage().get(0).getName(), is("strawberry"));
    }
}