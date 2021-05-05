package ru.job4j.ood.lsp.carparking;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 05.05.2021
 */

public class Parking implements CarParking {

    private int track;
    private int car;

    public Parking(int track, int car) {
        this.track = track;
        this.car = car;
    }

    @Override
    public boolean add(Auto auto) {
        return true;
    }

    @Override
    public boolean remove(Auto auto) {
        return true;
    }
}
