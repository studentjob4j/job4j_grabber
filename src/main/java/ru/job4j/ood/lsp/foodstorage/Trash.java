package ru.job4j.ood.lsp.foodstorage;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 1.05.2021
 */

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {

    private List<Food> storage;

    public Trash() {
        this.storage = new ArrayList<>();
    }

    public List<Food> getStorage() {
        return storage;
    }

    @Override
    public void add(Food food) {
            this.storage.add(food);
    }

    @Override
    public boolean accept(Food food) {
        boolean result = false;
        ControlQualityClient client = new ControlQualityClient();
        int value = client.countExpirationDatePercentage(food);
        if (value >= 100) {
            result = true;
        }
        return result;
    }

    @Override
    public List<Food> clear() {
        List<Food> list = new ArrayList<>();
        list.addAll(this.storage);
        this.storage.clear();
        return list;
    }
}
