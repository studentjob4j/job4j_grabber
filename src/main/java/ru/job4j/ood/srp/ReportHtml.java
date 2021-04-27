package ru.job4j.ood.srp;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 27.04.2021
 */

import java.util.function.Predicate;

public class ReportHtml implements Report {

    private Store store;

    public ReportHtml(Store store) {
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
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        String template = "<html><head><title>example</title></head><body>%s</body></html>";
        String result = String.format(template, text.toString());
        return result;
    }
}
