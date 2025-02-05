package com.example.demo.util;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Dushmantha
 * @email dushmantha.sse@gmail.com
 */
public class DateUtil {
    private static final DateTimeFormatter PRETTY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String toPrettyFormat(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        return dateTime.format(PRETTY_FORMATTER);
    }

    public static String toPrettyFormat(Date date) {
        if (date == null) return null;
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return toPrettyFormat(localDateTime);
    }
}
