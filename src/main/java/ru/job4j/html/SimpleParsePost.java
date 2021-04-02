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
    private int id = 0;

    public Post getPost() {
        return post;
    }

    public Post createPostAfterParse(String link) {

        SqlRuDateTimeParser parser = new SqlRuDateTimeParser();

        try {
            Document document = Jsoup.connect(link).get();
            Elements url = document.select("link[rel=canonical]");
            Elements name = document.select("td[class=messageHeader]");
            Elements desc = document.select("td[class=msgBody]");
            Elements data = document.select("td[class=msgFooter]");
            String date = splitDate(data.get(0).text());
            LocalDateTime result = parser.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.set(result.getYear(), result.getMonthValue(), result.getDayOfMonth());
            this.post = new Post(id++, name.get(0).text(), desc.get(1).text(),
                    url.attr("href"), calendar);

        } catch (IOException e) {
           LOG.error(e.getMessage());
        }
        return post;
    }

    private String splitDate(String value) {
        StringBuilder builder = new StringBuilder();
        int limit = 3;
        String[] temp = value.split(" ");
        for (int i = 0; i <= limit; i++) {
            if (temp[i].contains("сегодня") || temp[i].contains("вчера")) {
                limit = 1;
            }
            builder.append(temp[i]);
            if (i != limit) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }
}


