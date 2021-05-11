package ru.job4j.ood.lsp.foodstorage;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 1.05.2021
 * this class uses strategy pattern
 */

import java.time.LocalDate;
import java.time.Period;
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

    public int countExpirationDatePercentage(Food food) {
        LocalDate expire = food.getExpireDate();
        LocalDate create = food.getCreateDate();
        LocalDate now = LocalDate.now();
        Period allTime = Period.between(expire, create);
        Period current = Period.between(expire, now);
        int currentDays = current.getDays();
        int allDays = allTime.getDays();
        int result = Math.abs(100 - (currentDays * 100) / allDays);
        return result;
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
