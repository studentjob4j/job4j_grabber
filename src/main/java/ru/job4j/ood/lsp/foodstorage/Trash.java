package ru.job4j.ood.lsp.foodstorage;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 1.05.2021
 */

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {

    private List<Food> trash;

    public Trash() {
        this.trash = new ArrayList<>();
    }

    public List<Food> getStorage() {
        return trash;
    }

    @Override
    public boolean add(Food food) {
        boolean result = false;
        ControlQualityClient quality = new ControlQualityClient();
        if (quality.countExpirationDatePercentage(food) == 100) {
            this.trash.add(food);
            result = true;
        }
      return result;
    }
}
