package ru.job4j.ood.isp;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 03.05.2021
 * this class shows incorrect use the isp principle
 */

public class Lion implements Cat {

    @Override
    public String sound() {
        String sound = "Уф уф";
        return sound;
    }

    @Override
    public void stayAtHome() {
        throw new UnsupportedOperationException();
    }
}
