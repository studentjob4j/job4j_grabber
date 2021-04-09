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

    private Map<String, String> months = new HashMap<>();

    public SqlRuDateTimeParser() {
        createMonths();
    }

    public Map<String, String> getMonths() {
        return months;
    }

    public void createMonths() {
        this.months.put("янв", "01");
        this.months.put("фев", "02");
        this.months.put("мар", "03");
        this.months.put("апр", "04");
        this.months.put("май", "05");
        this.months.put("июн", "06");
        this.months.put("июл", "07");
        this.months.put("авг", "08");
        this.months.put("сен", "09");
        this.months.put("окт", "10");
        this.months.put("ноя", "11");
        this.months.put("дек", "12");
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
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(value);
    }
}
