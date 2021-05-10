package ru.job4j.ood.lsp.carparking;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 05.05.2021
 */

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingTest {

    private TruckVolvo volvo;
    private TruckVolvo volvo2;
    private Tesla one;
    private Tesla two;

    @Before
    public void createCar() {
        this.volvo = new TruckVolvo();
        this.volvo2 = new TruckVolvo();
        this.one = new Tesla();
        this.two = new Tesla();
    }

    @Test
    public void whenAddVolvoInParking() {
        Parking parking = new Parking(2, 10);
        boolean res = parking.add(new TruckVolvo());
        assertTrue(res);
    }

   @Test
    public void whenAddTwoTrucksInCarParking() {
        Parking parking = new Parking(0, 10);
        parking.add(volvo);
        parking.add(volvo2);
        Auto[] result = parking.getSimpleCar();
        assertThat(result[0], is(volvo));
        assertThat(result[1], is(volvo));
        assertThat(result[2], is(volvo2));
        assertThat(result[3], is(volvo2));
    }

    @Test
    public void whenAddTeslaInParking() {
        Parking parking = new Parking(2, 10);
        boolean res = parking.add(new Tesla());
        assertTrue(res);
    }

    @Test
    public void whenAddTwoTrucksThenRemove() {
        Parking parking = new Parking(0, 4);
        parking.add(volvo);
        parking.add(volvo2);
        parking.remove(volvo2);
        parking.remove(volvo);
        int free = parking.getCar();
        assertThat(free, is(4));
    }

    @Test
    public void whenAddTwoTrucksThenRemoveFirstAndAddTwoCar() {
        Parking parking = new Parking(0, 4);
        parking.add(volvo);
        parking.add(volvo2);
        parking.remove(volvo);
        parking.add(one);
        parking.add(two);
        assertThat(parking.getCar(), is(0));
    }
}