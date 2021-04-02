package ru.job4j.html;

import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
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
        int count = 3;

        try {
            Document document = Jsoup.connect(link).get();
            Elements url = document.select("td[class=postslisttopic]");
            while (count < url.size()) {
                Element element = url.get(count);
                Elements temp = element.select("a");
                String value = temp.attr("href");
                list.add(parsePost.createPostAfterParse(value));
                count++;
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
