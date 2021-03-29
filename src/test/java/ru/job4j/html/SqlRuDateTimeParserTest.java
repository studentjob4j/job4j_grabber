package ru.job4j.html;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SqlRuDateTimeParserTest {

    // для запуска сервиса codecov
    @Test
    public void whenGetMonths() {
        SqlRuDateTimeParser parse = new SqlRuDateTimeParser();
        parse.recMonthInMap();
        assertThat(parse.getMonths().size(), is(12));
    }

}