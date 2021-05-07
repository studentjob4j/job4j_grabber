package ru.job4j.ood.lsp.foodstorage;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 1.05.2021
 */

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    private List<Food> storage;

    public Shop() {
        this.storage = new ArrayList<>();
    }

    @Override
    public boolean add(Food food) {
        boolean result = false;
        ControlQualityClient control = new ControlQualityClient();
        int temp = control.countExpirationDatePercentage(food);
        if (temp >= 25 && temp <= 75) {
            this.storage.add(food);
            result = true;
        } else if (temp > 75 && temp < 100) {
            double newPrice = food.getPrice() - food.getDiscount();
            food.setPrice(newPrice);
            this.storage.add(food);
            result = true;
        }
        return result;
    }

    @Override
    public boolean addResort(Food food, List<Integer> condition) {
        boolean result = false;
        ControlQualityClient control = new ControlQualityClient();
        int temp = control.countExpirationDatePercentage(food);
        if (temp >= condition.get(0) && temp <= condition.get(1)) {
            this.storage.add(food);
            result = true;
        } else if (temp > condition.get(1) && temp < condition.get(2)) {
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
