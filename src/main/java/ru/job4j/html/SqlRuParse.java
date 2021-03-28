package ru.job4j.html;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;
import java.time.LocalTime;

/**
 * @author Evgenii Shegai
 * @version 1.0
 * @since 28.03.2021
 */

public class SqlRuParse implements DateTimeParser {

    private static int index = 1;
    private static final Logger LOG = LoggerFactory.getLogger(SqlRuParse.class.getName());
    private static List<String> list = new ArrayList<>();
    private final List<String> months = new ArrayList<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public static void main(String[] args) {
        SqlRuParse parse = new SqlRuParse();
        int count = 0;
        parse.recMonthInList();
        while (index <= 5) {
            StringBuilder builder = new StringBuilder();
            builder.append("https://www.sql.ru/forum/job-offers/");
            Document doc = null;
            try {
                doc = getNextPage(builder);
            } catch (IOException e) {
                LOG.error(e.getMessage());
                e.printStackTrace();
            }
            Elements row = doc.select(".postslisttopic");
            Elements row2 = doc.getElementsByClass("altCol");
            for (Element e : row2) {
                Elements elements = e.select("td[style=text-align:center]");
                if (elements.size() != 0) {
                    list.add(elements.text());
                }
            }
            for (Element td : row) {
                Element href = td.child(0);
                System.out.println(href.attr("href"));
                System.out.println(href.text());
                parse.removeCharT(parse.parse(list.get(count++)));
                System.out.println();
            }
            System.out.println();
        }
    }

    public List<String> getMonths() {
        return months;
    }

    private static Document getNextPage(StringBuilder builder) throws IOException {
       Document doc = null;
       if (index == 1) {
          doc = Jsoup.connect(builder.
                  toString()).get();
           index++;
       } else {
           for (int i = index; i <= 5;) {
              doc = Jsoup.connect(builder.append(i).toString()).get();
              index++;
              break;
           }
       }
       return doc;
    }

    public void recMonthInList() {
        months.add("01");
        months.add("02");
        months.add("03");
        months.add("04");
        months.add("05");
        months.add("06");
        months.add("07");
        months.add("08");
        months.add("09");
        months.add("10");
        months.add("11");
        months.add("12");
    }

    @Override
    public LocalDateTime parse(String parse) {
       LocalTime time = LocalTime.of(getTime(parse, 0), getTime(parse, 1));
       LocalDate date = LocalDate.of(getDate(parse, 2),
               getDate(parse, 1), getDate(parse, 0));
      return LocalDateTime.of(date, time);
    }

    private String splitString(String temp, int value) {
        String[] array = temp.split(", ");
        return array[value];
    }

    private Integer getDate(String parse, int value) {
        String[] arr = new String[3];
        String date = splitString(parse, 0);
        Calendar calendar = Calendar.getInstance();
        if (date.contains("сегодня")) {
            arr[0] = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            arr[1] = String.valueOf(calendar.get(Calendar.MONTH));
            arr[2] = String.valueOf(calendar.get(Calendar.YEAR));
        } else if (date.contains("вчера")) {
            arr[0] = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            arr[1] = String.valueOf(calendar.get(Calendar.MONTH));
            arr[2] = String.valueOf(calendar.get(Calendar.YEAR));
        } else {
            arr = date.split(" ");
            if (arr[1].contains("фев")) {
                arr[1] = getMonths().get(1);
            } else if (arr[1].contains("янв")) {
                arr[1] = getMonths().get(0);
            } else {
                arr[1] = months.get(calendar.get(Calendar.MONTH));
                arr[2] = String.valueOf(calendar.get(Calendar.YEAR));
            }
        }
        return Integer.parseInt(arr[value]);
    }

    private Integer getTime(String parse, int value) {
        String date = splitString(parse, 1);
        String[] arr = date.split(":");
        return   Integer.parseInt(arr[value]);
    }

    private void removeCharT(LocalDateTime value) {
        String temp  = value.toString();
        LocalDateTime time = LocalDateTime.parse(temp, formatter);
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss").format(time));
    }
}
