package ru.job4j.ood.lsp.carparking;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 05.05.2021
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class ParkingTest {

    @Test
    public void whenAddVolvoNParking() {
        Parking parking = new Parking(2, 10);
        boolean res = parking.add(new TruckVolvo());
        assertTrue(res);
    }

    @Test
    public void whenAddTeslaNParking() {
        Parking parking = new Parking(2, 10);
        boolean res = parking.add(new Tesla());
        assertTrue(res);
    }

    @Test
    public void whenAddTeslaNParkingThenRemove() {
        Parking parking = new Parking(2, 10);
        Tesla tesla = new Tesla();
        parking.add(tesla);
        boolean res = parking.remove(tesla);
        assertTrue(res);
    }
}