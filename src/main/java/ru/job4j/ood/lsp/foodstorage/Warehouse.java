package ru.job4j.ood.lsp.foodstorage;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 1.05.2021
 */

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private List<Food> storage;

    public Warehouse() {
        this.storage = new ArrayList<>();
    }

    @Override
    public boolean add(Food food) {
        boolean result = false;
        ControlQualityClient control = new ControlQualityClient();
        if (control.countExpirationDatePercentage(food) < 25) {
            this.storage.add(food);
            result = true;
        }
      return result;
    }

    @Override
    public boolean addResort(Food food, List<Integer> condition) {
        boolean result = false;
        ControlQualityClient control = new ControlQualityClient();
        if (control.countExpirationDatePercentage(food) < condition.get(0)) {
            this.storage.add(food);
            result = true;
        }
        return result;
    }

    @Override
    public List<Food> get() {
        return this.storage;
    }
}
