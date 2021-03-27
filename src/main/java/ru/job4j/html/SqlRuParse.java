package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;
import java.time.LocalTime;

public class SqlRuParse implements DateTimeParser {

    private static List<String> list = new ArrayList<>();
    private final List<String> months = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public List<String> getMonths() {
        return months;
    }

    public static void main(String[] args) throws Exception {
        SqlRuParse parse = new SqlRuParse();
        int count = 0;
        parse.recMonthInList();
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
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
            int a = calendar.get(Calendar.MONTH);
            arr[0] = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            arr[1] = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            arr[2] = String.valueOf(calendar.get(Calendar.YEAR));
        } else if (date.contains("вчера")) {
            arr[0] = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH) - 1);
            arr[1] = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            arr[2] = String.valueOf(calendar.get(Calendar.YEAR));
        } else {
            arr = date.split(" ");
            arr[1] = months.get(calendar.get(Calendar.MONTH) + 1);
            arr[2] = String.valueOf(calendar.get(Calendar.YEAR));
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
