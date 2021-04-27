package ru.job4j.ood.srp;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 27.04.2021
 */

import java.util.function.Predicate;

public class AnotherTypeSalaryReport implements Report {

    private Store store;

    public AnotherTypeSalaryReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(convertUsdRub(employee.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    private double convertUsdRub(double value) {
        return value * 76;
    }
}
