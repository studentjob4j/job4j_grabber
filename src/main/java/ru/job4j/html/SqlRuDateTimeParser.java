package ru.job4j.html;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;
import java.time.LocalTime;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 29.03.2021
 */

public class SqlRuDateTimeParser implements DateTimeParser {

    private final Map<String, String> months = new HashMap<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public SqlRuDateTimeParser() {
        recMonthInMap();
    }

    public Map<String, String> getMonths() {
        return months;
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
        LocalDate date = getDate(parse, 0);
        return LocalDateTime.of(date, time);
    }

    private String splitString(String temp, int value) {
        String[] array = temp.split(", ");
        return array[value];
    }

    private LocalDate getDate(String parse, int value) {
        StringBuilder builder = new StringBuilder();
        builder.append("20");
        String date = splitString(parse, value);
        LocalDate result;
        if (date.contains("сегодня")) {
            result = LocalDate.now();
        } else if (date.contains("вчера")) {
            result = LocalDate.now().minusDays(1);
        } else {
            String[] arr = date.split(" ");
            arr[1] = months.get(arr[1]);
            if (!arr[2].equals("21")) {
               builder.append(arr[2]);
                arr[2] = builder.toString();
            } else {
                Calendar calendar = Calendar.getInstance();
                arr[2] = String.valueOf(calendar.get(Calendar.YEAR));
            }
            result = LocalDate.of(Integer.parseInt(arr[2]),
                    Integer.parseInt(arr[1]), Integer.parseInt(arr[0]));
        }
        return result;
    }

    private String[] getTime(String parse, int value) {
        String date = splitString(parse, value);
        return date.split(":");
    }

    public String removeCharT(LocalDateTime value) {
        String temp  = value.toString();
        LocalDateTime time = LocalDateTime.parse(temp, formatter);
        temp = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss").
                format(time);
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss").
                format(time));
        return temp;
    }
}
