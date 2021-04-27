package ru.job4j.ood.osp;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 27.04.2021
 * this class shows incorrect use ocp principlies when dont use interface,
 * functional interface predicate
 */

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Dog {

    private String name;
    private ArrayList<Dog> dogs = new ArrayList();

    public Dog(String name) {
        this.name = name;
    }

    public void sound() {
        System.out.println("Gav gav gav");
    }

    public void add(Dog dog) {
        this.dogs.add(dog);
    }

    public Dog findByName(String name) {
        Dog result = null;
        for (Dog res : dogs) {
            if (res.name.equals(name)) {
                result = res;
                break;
            }
        }
        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }
}
