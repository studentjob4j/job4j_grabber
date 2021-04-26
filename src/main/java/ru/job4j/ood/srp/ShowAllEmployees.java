package ru.job4j.ood.srp;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 27.04.2021
 */

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShowAllEmployees {

    public List<Employee> showAllEmployeeFromMoreToLess(Store storage) {
        Comparator<Employee> comparator = (o1, o2) -> (int) (o2.getSalary() - o1.getSalary());
        MemStore memStore = (MemStore) storage;
        List<Employee> result = memStore.getEmployees();
        Collections.sort(result, comparator);
        return result;
    }
}
