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

public class AnotherTypeSalaryReportTest {

    private Calendar hired = Calendar.getInstance();
    private Calendar fired = Calendar.getInstance();

    //изменил тип зп -> доллары перевел в рубли
    @Test
    public void whenChangeTypeSalary() {
        MemStore store = new MemStore();
        hired.set(2020, 02, 22);
        fired.set(2021, 05, 11);
        Employee worker = new Employee("Ivan", hired, fired,  12);
        store.add(worker);
        Report report = new AnotherTypeSalaryReport(store);
        String[] temp = report.generate(x -> true).split(";");
        assertThat(temp[temp.length - 2], is("912.0"));
    }

}