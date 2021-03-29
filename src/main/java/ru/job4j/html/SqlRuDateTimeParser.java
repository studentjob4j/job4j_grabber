package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;
import java.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 29.03.2021
 */

public class SqlRuDateTimeParser implements DateTimeParser {

    private static final Logger LOG = LoggerFactory.getLogger(SqlRuDateTimeParser.class.getName());
    private static List<String> list = new ArrayList<>();
    private final Map<String, String> months = new HashMap<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public Map<String, String> getMonths() {
        return months;
    }

    public static void main(String[] args) {
        SqlRuDateTimeParser parse = new SqlRuDateTimeParser();
        int count = 0;
        parse.recMonthInMap();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        } catch (IOException e) {
            LOG.error(e.getMessage());
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
}

    public void recMonthInMap() {
        months.put("янв", "01");
        months.put("фев", "02");
        months.put("мар", "03");
        months.put("апр", "04");
        months.put("май", "05");
        months.put("июн", "06");
        months.put("июл", "07");
        months.put("авг", "08");
        months.put("сен", "09");
        months.put("окт", "10");
        months.put("ноя", "11");
        months.put("дек", "12");
    }

    @Override
    public LocalDateTime parse(String parse) {
        String[] timer = getTime(parse, 1);
        LocalTime time = LocalTime.of(Integer.parseInt(timer[0]), Integer.parseInt(timer[1]));
        String[] data = getDate(parse, 0);
        LocalDate date = LocalDate.of(
                Integer.parseInt(data[2]),
                Integer.parseInt(data[1]), Integer.parseInt(data[0]));
        return LocalDateTime.of(date, time);
    }

    private String splitString(String temp, int value) {
        String[] array = temp.split(", ");
        return array[value];
    }

    private String[] getDate(String parse, int value) {
        StringBuilder builder = new StringBuilder();
        builder.append("20");
        String[] arr = new String[3];
        String date = splitString(parse, value);
        Calendar calendar = Calendar.getInstance();
        if (date.contains("сегодня")) {
            arr[0] = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            arr[1] = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            arr[2] = String.valueOf(calendar.get(Calendar.YEAR));
        } else if (date.contains("вчера")) {
            arr[0] = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH) - 1);
            arr[1] = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            arr[2] = String.valueOf(calendar.get(Calendar.YEAR));
        } else {
            arr = date.split(" ");
            arr[1] = months.get(arr[1]);
            if (!arr[2].equals("21")) {
               builder.append(arr[2]);
                arr[2] = builder.toString();
            } else {
                arr[2] = String.valueOf(calendar.get(Calendar.YEAR));
            }
        }
        return arr;
    }

    private String[] getTime(String parse, int value) {
        String date = splitString(parse, value);
        return date.split(":");
    }

    private void removeCharT(LocalDateTime value) {
        String temp  = value.toString();
        LocalDateTime time = LocalDateTime.parse(temp, formatter);
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss").format(time));
    }
}
