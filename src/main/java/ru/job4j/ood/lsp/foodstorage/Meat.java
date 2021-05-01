package ru.job4j.ood.lsp.foodstorage;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 1.05.2021
 */

import java.time.LocalDate;

public class Meat extends Food {

    public Meat(String name, LocalDate expireDate,
                LocalDate createDate, double price, double discount) {
        super(name, expireDate, createDate, price, discount);
    }
}
