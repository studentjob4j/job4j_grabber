package ru.job4j.ood.isp;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 03.05.2021
 * this class shows incorrect use the isp principle
 */

public class ElectricCar implements Auto {

    @Override
    public int refueling(double money) {
       throw  new UnsupportedOperationException();
    }

    @Override
    public double go(int fuel) {
        return 0;
    }
}
