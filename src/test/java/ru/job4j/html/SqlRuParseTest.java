package ru.job4j.html;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SqlRuParseTest {

    // для запуска сервиса codecov
    @Test
    public void whenGetMonths() {
        SqlRuParse parse = new SqlRuParse();
        parse.recMonthInList();
        assertThat(parse.getMonths().size(), is(12));
    }
}