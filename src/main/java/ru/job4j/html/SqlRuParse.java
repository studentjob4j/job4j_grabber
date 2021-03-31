package ru.job4j.html;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Evgenii Shegai
 * @version 1.0
 * @since 28.03.2021
 */

public class SqlRuParse {

    private static List<String> list = new ArrayList<>();
    private static final Logger LOG = LoggerFactory.getLogger(SqlRuParse.class.getName());

    public static void main(String[] args) {

        SqlRuDateTimeParser parser = new SqlRuDateTimeParser();

        try {
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
            Elements row = doc.select(".postslisttopic");
            Elements row2 = doc.getElementsByClass("altCol");
            int count = 0;
            for (Element e : row2) {
                Elements elements = e.getElementsByAttribute("style");
                if (elements.size() != 0) {
                    list.add(elements.text());
                }
            }
            for (Element td : row) {
                Element href = td.child(0);
                System.out.println(href.attr("href"));
                System.out.println(href.text());
                LocalDateTime result = parser.parse(list.get(count++));
                String data = parser.removeCharT(result);
                System.out.println(data);
                System.out.println();
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }
}
