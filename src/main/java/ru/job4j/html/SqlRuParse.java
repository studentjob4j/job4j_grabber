package ru.job4j.html;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
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
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        Elements row = doc.select(".postslisttopic");
        Elements row2 = doc.getElementsByClass("altCol");
        int count = 0;
        for (Element e : row2) {
            Elements elements = e.getElementsByAttribute("style");
            list.add(elements.text());
        }
        for (Element td : row) {
            Element href = td.child(0);
            System.out.println(href.attr("href"));
            System.out.println(href.text());
            System.out.println(list.get(count++));
            System.out.println();
        }
    }
}
