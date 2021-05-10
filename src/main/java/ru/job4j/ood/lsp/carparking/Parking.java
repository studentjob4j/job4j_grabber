package ru.job4j.ood.lsp.carparking;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 05.05.2021
 * Every car has size one and every truck has size two by default
 */

public class Parking implements CarParking {

    private int truck;
    private int car;
    private Auto[] simpleCar;
    private Auto[] simpleTruck;
    private int truckIndex;
    private boolean check;

    public Parking(int truck, int car) {
        this.truck = truck;
        this.car = car;
        this.simpleCar = new Auto[car];
        this.simpleTruck = new Auto[truck];
    }

    public int getCar() {
        return car;
    }

    public Auto[] getSimpleCar() {
        return simpleCar;
    }

    @Override
    public boolean add(Auto auto) {
        boolean result = false;
        if (auto.getSize() == 1 && car != 0) {
            int carIndex = checkPlace(simpleCar, auto);
            this.simpleCar[carIndex] = auto;
            this.car--;
            result = true;
        } else {
            if (this.truck >= auto.getSize()) {
                this.simpleTruck[truckIndex++] = auto;
                this.truck = this.truck - auto.getSize();
                result = true;

            } else if (this.car >= auto.getSize()) {
                int index = checkPlace(simpleCar, auto);
                if (index != -1) {
                   result = addTruckOnCarParking(index, auto);
                   this.car = this.car - auto.getSize();
                   this.check = true;
                }
            }
        }
        return result;
    }

    private boolean addTruckOnCarParking(int index, Auto auto) {

        int size = auto.getSize();
        while (size != 0) {
            this.simpleCar[index++] = auto;
            size--;
        }

        return true;
    }

    private int checkPlace(Auto[] array, Auto auto) {
        boolean temp = true;
        int result = -1;
        int carSize = auto.getSize();
            for (int i = 0; i < array.length; i++) {
                if (array[i] == null) {
                    int index = i;
                    while (carSize != 0) {
                        if (array[index] != null) {
                            temp = false;
                        }
                        carSize--;
                        index++;
                    }
                    if (temp && carSize == 0) {
                        result = i;
                        break;
                    }
                }
            }
        return result;
    }

    @Override
    public boolean remove(Auto auto) {
        boolean result = false;
        if (auto.getSize() == 1) {
            result = deleteFromParking(simpleCar, auto);
            if (result) {
                this.car++;
            }
        } else if (check) {
            result = deleteFromParking(simpleCar, auto);
            if (result) {
                this.car += auto.getSize();
            }
        } else  {
          result = deleteFromParking(simpleTruck, auto);
            if (result) {
                this.truck += auto.getSize();
            }
        }
        return result;
    }

    private boolean deleteFromParking(Auto[] array, Auto auto) {
        boolean res = false;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].equals(auto)) {
                if (this.truck < 2 || auto.getSize() == 1) {
                    this.simpleCar[i] = null;
                } else {
                    this.simpleTruck[i] = null;
                }
                count++;
            }
        }
        if (count == auto.getSize()) {
            res = true;
        }
        return res;
    }
}
