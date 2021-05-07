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

   @Before
   public void createStorage() {
       Warehouse warehouse = new Warehouse();
       Shop shop = new Shop();
       Trash trash = new Trash();
       list = List.of(warehouse, shop, trash);
   }

    @Test
    public void whenSetFoodInShop() {
        LocalDate created = LocalDate.of(2021, 4, 10);
        LocalDate expire = LocalDate.of(2021, 5, 9);
        Milk milk = new Milk("Milk", expire, created, 100, 0);
        ControlQualityClient client = new ControlQualityClient();
        client.setStore(list);
        client.action(milk);
        Shop shop = (Shop) client.getStore().get(1);
        assertThat(shop.get().get(0).getName(), is("Milk"));
    }

    @Test
    public void whenSetFoodInShopWithDiscount() {
        LocalDate created = LocalDate.of(2021, 4, 10);
        LocalDate expire = LocalDate.of(2021, 5, 3);
        Strawberry strawberry = new Strawberry("Strawberry", expire, created, 300, 12);
        ControlQualityClient client = new ControlQualityClient();
        client.setStore(list);
        client.action(strawberry);
        Shop shop = (Shop) client.getStore().get(1);
        assertThat(shop.get().get(0).getPrice(), is(288.0));
    }

    @Test
    public void whenSetFoodInWarehouse() {
        LocalDate created = LocalDate.of(2021, 4, 29);
        LocalDate expire = LocalDate.of(2021, 5, 10);
        Meat meat = new Meat("Meat", expire, created, 400, 0);
        ControlQualityClient client = new ControlQualityClient();
        client.setStore(list);
        client.action(meat);
        Warehouse warehouse = (Warehouse) client.getStore().get(0);
        assertThat(warehouse.get().get(0).getName(), is("Meat"));
    }

    @Test
    public void whenSetFoodInTrash() {
        LocalDate created = LocalDate.of(2021, 4, 29);
        LocalDate expire = LocalDate.of(2021, 5, 1);
        Onion onion = new Onion("Onion", expire, created, 40, 0);
        ControlQualityClient client = new ControlQualityClient();
        client.setStore(list);
        client.action(onion);
        Trash trash = (Trash) client.getStore().get(2);
        assertThat(trash.get().get(0).getName(), is("Onion"));
    }

    @Test
    public void whenSetFoodInShopWithNewCondition() {
        LocalDate created = LocalDate.of(2021, 4, 10);
        LocalDate expire = LocalDate.of(2021, 5, 9);
        Milk milk = new Milk("Milk", expire, created, 100, 0);
        ControlQualityClient client = new ControlQualityClient();
        client.setStore(list);
        client.action(milk);
        client.resort(List.of(30, 77, 100));
        Shop shop = (Shop) client.getStore().get(1);
        assertThat(shop.get().get(0).getName(), is("Milk"));
    }

    @Test
    public void whenSetFoodInShopWithDiscountWithNewCondition() {
        LocalDate created = LocalDate.of(2021, 4, 10);
        LocalDate expire = LocalDate.of(2021, 5, 3);
        Strawberry strawberry = new Strawberry("Strawberry", expire, created, 300, 12);
        ControlQualityClient client = new ControlQualityClient();
        client.setStore(list);
        client.action(strawberry);
        client.resort(List.of(20, 70, 100));
        Shop shop = (Shop) client.getStore().get(1);
        assertThat(shop.get().get(0).getPrice(), is(288.0));
    }

    @Test
    public void whenSetFoodInWarehouseWithNewCondition() {
        LocalDate created = LocalDate.of(2021, 4, 29);
        LocalDate expire = LocalDate.of(2021, 5, 10);
        Meat meat = new Meat("Meat", expire, created, 400, 0);
        ControlQualityClient client = new ControlQualityClient();
        client.setStore(list);
        client.action(meat);
        client.resort(List.of(20, 40, 100));
        Warehouse warehouse = (Warehouse) client.getStore().get(0);
        assertThat(warehouse.get().get(0).getName(), is("Meat"));
    }

    @Test
    public void whenSetFoodInTrashWithNewCondition() {
        LocalDate created = LocalDate.of(2021, 4, 29);
        LocalDate expire = LocalDate.of(2021, 5, 1);
        Onion onion = new Onion("Onion", expire, created, 40, 0);
        ControlQualityClient client = new ControlQualityClient();
        client.setStore(list);
        client.action(onion);
        client.resort(List.of(20, 70, 100));
        Trash trash = (Trash) client.getStore().get(2);
        assertThat(trash.get().get(0).getName(), is("Onion"));
    }
}