package ru.job4j.html;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleParsePostTest {

    // для запуска сервиса codecov
    @Test
    public void whenGetPost() {
        SimpleParsePost post = new SimpleParsePost();
        post.createPostAfterParse("https://www.sql.ru/forum/1325330/lidy-be-fe-senior-"
                + "cistemnye-analitiki-qa-i-devops-moskva-do-200t");
        assertThat(post.getPost().getName(),
                is("Лиды BE/FE/senior cистемные аналитики/QA и DevOps, Москва, до 200т. [new]"));
    }
}