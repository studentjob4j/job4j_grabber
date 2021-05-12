package ru.job4j.ood.lsp.foodstorage;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 1.05.2021
 * this class uses strategy pattern
 */

import java.util.ArrayList;
import java.util.List;

public class ControlQualityClient {

    private List<Store> store;

    public void setStore(List<Store> store) {
        this.store = store;
    }

    public List<Store> getStore() {
        return store;
    }

    public void resort() {
      List<Food> allFood = new ArrayList<>();
      for (Store storage : this.store) {
          allFood.addAll(storage.clear());
      }
      for (Food food : allFood) {
          action(food);
      }
    }

    public void action(Food food) {
        for (Store temp : this.store) {
            if (temp.accept(food)) {
                temp.add(food);
                break;
            }
        }
    }
}
