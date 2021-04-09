package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.grabber.Post;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 29.03.2021
 */

public class SimpleParsePost {

    private static final Logger LOG = LoggerFactory.getLogger(SqlRuDateTimeParser.class.getName());
    private ru.job4j.grabber.Post post;
    private int id = 1;

    public ru.job4j.grabber.Post getPost() {
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
            Date value = new Date(result.getYear() - 1900,
                    result.getMonthValue() - 1, result.getDayOfMonth());
            this.post = new Post(name.get(0).text(), desc.get(1).text(),
                    url.attr("href"), value);
        } catch (IOException e) {
           LOG.error(e.getMessage());
        }
        return post;
    }

    private String splitDate(String value) {

        StringBuilder builder = new StringBuilder();
        String[] temp = null;
        int limit = 3;
        if (value.contains("сегодня") || value.contains("вчера")) {
            temp = value.split(" ", 2);
            String[] tmp = temp[1].split(" ");
            temp[1] = tmp[0];
        } else {
            temp = value.split(" ");
        }
        for (int i = 0; i <= limit; i++) {
            String tmp = temp[i];
            if (tmp.contains("сегодня") || tmp.contains("вчера")) {
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


