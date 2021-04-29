package ru.job4j.ood.lsp;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 29.04.2021
 * this class shows incorrect use lsp principlies when child dont uses check in the method
 */

public class Auto {

    public void drive(int fuel) {
        if (fuel <= 0) {
            throw new IllegalArgumentException("dont enought fuel for drive");
        }
        System.out.println("go ahead");
    }

    public static void main(String[] args) {
        Auto auto = new Truck();
        auto.drive(-1);
    }
}

class Truck extends Auto {

    @Override
    public void drive(int fuel) {
        System.out.println("go ahead");
    }
}
