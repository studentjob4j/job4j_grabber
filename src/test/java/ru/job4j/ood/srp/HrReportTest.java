package ru.job4j.ood.srp;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 27.04.2021
 */

import org.junit.Test;
import java.util.Calendar;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class HrReportTest {

    private Calendar hired = Calendar.getInstance();
    private Calendar fired = Calendar.getInstance();

    @Test
    public void whenGetSortListByFieldSalary() {
        MemStore store = new MemStore();
        hired.set(2020, 02, 22);
        fired.set(2021, 05, 11);
        Employee worker = new Employee("Ivan", hired, fired, 8);
        store.add(worker);
        Employee worker2 = new Employee("Petr", hired, fired, 10);
        store.add(worker2);
        Employee worker3 = new Employee("Kolya", hired, fired, 14);
        store.add(worker3);
        String result = new HrReport(store).showAll();
        assertThat(result, is("Name; Salary;\r\nKolya;14.0;\r\nPetr;10.0;\r\nIvan;8.0;"));
    }

}