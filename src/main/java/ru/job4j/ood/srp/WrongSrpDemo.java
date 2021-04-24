package ru.job4j.ood.srp;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 24.04.2021
 * this method shows incorrect use srp  when the logic is mixed and logic in different parts
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.job4j.grabber.Post;
import java.io.IOException;

public class WrongSrpDemo {

    public int square(int a, int b) {
        WrongTwoSrpDemo twoSrpDemo = new WrongTwoSrpDemo();
        return (int) Math.pow(twoSrpDemo.summ(a, b), 2);
    }

    public Post simpleParse(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            //  some logic
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Post();
    }
}
