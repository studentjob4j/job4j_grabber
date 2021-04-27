package ru.job4j.ood.srp;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 27.04.2021
 */

import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;

public class HrReport implements Report {

    private Store store;

    public HrReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";");
        }
        return text.toString();
    }

    public String showAll() {
        Comparator<Employee> comparator = (x, y) -> (int) (y.getSalary() - x.getSalary());
        MemStore memStore = (MemStore) store;
        Collections.sort(memStore.getEmployees(), comparator);
        String result = (generate(x -> true));
        return result;
    }
}
