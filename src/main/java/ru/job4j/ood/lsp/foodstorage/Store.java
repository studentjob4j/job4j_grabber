package ru.job4j.ood.lsp.foodstorage;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 11.05.2021
 */

import java.util.List;

public interface Store {

  void add(Food food);

  boolean accept(Food food);

  List<Food> clear();
}
