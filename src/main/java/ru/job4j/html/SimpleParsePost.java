package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 29.03.2021
 */

public class SimpleParsePost {

    private static final Logger LOG = LoggerFactory.getLogger(SqlRuDateTimeParser.class.getName());
    private Post post;

    public Post getPost() {
        return post;
    }

    public Post createPostAfterParse() {

        SqlRuDateTimeParser parser = new SqlRuDateTimeParser();

        try {
            Document document = Jsoup.connect(
                    "https://www.sql.ru/forum/1325330/lidy-be-fe-senior-"
                            + "cistemnye-analitiki-qa-i-devops-moskva-do-200t").get();
            Elements url = document.select("link[rel=canonical]");
            Elements name = document.select("td[id=id22132447]");
            Elements desc = document.select("td[class=msgBody]");
            Elements data = document.select("td[class=msgFooter]");
            String date = data.get(0).text().substring(0, 16);
            LocalDateTime result = parser.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.set(result.getYear(), result.getMonthValue(), result.getDayOfMonth());
            this.post = new Post(1, name.text(), desc.get(1).text(),
                    url.attr("href"), calendar);
        } catch (IOException e) {
           LOG.error(e.getMessage());
        }
        return post;
    }
}


