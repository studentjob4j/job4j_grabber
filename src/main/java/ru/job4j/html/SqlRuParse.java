package ru.job4j.html;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Evgenii Shegai
 * @version 2.0
 * @since 01.04.2021
 */

public class SqlRuParse implements Parse {

    private static List<String> list = new ArrayList<>();
    private static final Logger LOG = LoggerFactory.getLogger(SqlRuParse.class.getName());
    private SimpleParsePost parsePost = new SimpleParsePost();

    @Override
    public List<Post> list(String link) {
        List<Post> list = new ArrayList<>();
        SqlRuDateTimeParser parser = new SqlRuDateTimeParser();
        int id = 1;
        int index = 1;

        try {
            Document document = Jsoup.connect(link).get();
            Elements url = document.select("link[rel=canonical]");
            Elements name = document.select("td[class=messageHeader]");
            Elements desc = document.select("td[class=msgBody]");
            Elements data = document.select("td[class=msgFooter]");
            String date = data.get(0).text().substring(0, 16);
            Calendar calendar = Calendar.getInstance();
            for (int i = 0; i < name.size(); i++) {
                LocalDateTime result = parser.parse(date);
                calendar.set(result.getYear(), result.getMonthValue(), result.getDayOfMonth());
                Post post = new Post(id++, name.get(i).text(), desc.get(index).text(),
                        url.attr("href"), calendar);
                list.add(post);
                index += 2;
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return list;
    }

    @Override
    public Post detail(String link) {
        Post result =  parsePost.createPostAfterParse(link);
        return result;
    }
}
