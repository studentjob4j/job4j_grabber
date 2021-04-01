package ru.job4j.html;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SqlRuParseTest {

    // для запуска сервиса codecov
    @Test
    public void whenGetMonths() {
        SqlRuDateTimeParser parse = new SqlRuDateTimeParser();
        parse.recMonthInMap();
        assertThat(parse.getMonths().size(), is(12));
    }

    @Test
    public void whenGetListPost() {
        SqlRuParse parse = new SqlRuParse();
        List<Post> list = parse.list(
                "https://www.sql.ru/forum/1334818/vakansiya-ms-sql-razrabotchik");
        assertThat(list.size(), is(9));
    }

    @Test
    public void whenGetPost() {
        SqlRuParse parse = new SqlRuParse();
        Post post = parse.detail("https://www.sql.ru/forum/1334818/vakansiya-ms-sql-razrabotchik");
        assertThat(post.getName(), is("Вакансия MS SQL разработчик [new]"));
    }
}