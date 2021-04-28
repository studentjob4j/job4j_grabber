package ru.job4j.ood.srp;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 28.04.2021
 */

import java.util.function.Predicate;

public class JsonReport implements Report {

    private Store store;

    public JsonReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("{");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append("name : ").append(employee.getName())
                    .append(";").append(System.lineSeparator())
                    .append("hired : ").append(employee.getHired())
                    .append(";").append(System.lineSeparator())
                    .append("fired : ").append(employee.getFired())
                    .append(";").append(System.lineSeparator())
                    .append("salary : ").append(employee.getSalary())
                    .append(";").append(System.lineSeparator())
                    .append("}");
        }
       return text.toString();
    }
}
