package ru.job4j.ood.lsp.foodstorage;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 1.05.2021
 * this class uses strategy pattern
 */

import java.time.LocalDate;
import java.time.Period;
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
        int currentDays = Math.abs(current.getDays());
        int allDays = Math.abs(allTime.getDays());
        int result = 100 - (currentDays * 100) / allDays;
        return result;
    }

    public void action(Food food) {
        for (Store temp : this.store) {
            if (temp.add(food)) {
                break;
            }
        }
    }
}
