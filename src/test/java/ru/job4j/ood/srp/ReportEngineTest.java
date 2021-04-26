package ru.job4j.ood.srp;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 27.04.2021
 */

import org.junit.Test;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportEngineTest {

    @Test
    public void whenNewGenerated() {
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", 12);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<html><head><title>example</title></head><body>")
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
        .append("</body></html>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    //изменил тип зп на сдельную
    @Test
    public void whenChangeTypeSalary() {
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", 12);
        store.add(worker);
        Report engine = new ReportEngine(store);
        String[] temp = engine.generate(x -> true).split(";");
        assertThat(temp[temp.length - 2], is("9.24"));
    }

    @Test
    public void whenGetSortListByFieldSalary() {
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", 8);
        store.add(worker);
        Employee worker2 = new Employee("Pan", 10);
        store.add(worker2);
        Employee worker3 = new Employee("Tag", 14);
        store.add(worker3);
        List<Employee> result = new ShowAllEmployees().showAllEmployeeFromMoreToLess(store);
        List<Employee> expect = List.of(worker3, worker2, worker);
        assertThat(result, is(expect));
    }
}
