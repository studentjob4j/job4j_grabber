package ru.job4j.ood.isp;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 03.05.2021
 */

public class SiamCat implements Cat {

    @Override
    public String sound() {
        String sound = "мяу - мяу";
        return sound;
    }

    @Override
    public void stayAtHome() {
    }
}
