package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
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

        try {
            Document document = Jsoup.connect(
                    "https://www.sql.ru/forum/1325330/lidy-be-fe-senior-"
                            + "cistemnye-analitiki-qa-i-devops-moskva-do-200t").get();
            Elements url = document.select("link[rel=canonical]");
            Elements name = document.select("td[id=id22132447]");
            Elements desc = document.select("td[class=msgBody]");
            Elements data = document.select("td[class=msgFooter]");
            Calendar calendar = Calendar.getInstance();
            String[] array = data.get(0).text().substring(0, 9).split(" ");
            calendar.set(Integer.parseInt(array[2]),
                    Integer.parseInt(array[0]), Integer.parseInt(array[0]));
            this.post = new Post(1, name.text(), desc.get(1).text(),
                    url.attr("href"), calendar);
        } catch (IOException e) {
           LOG.error(e.getMessage());
        }
        return post;
    }
}


