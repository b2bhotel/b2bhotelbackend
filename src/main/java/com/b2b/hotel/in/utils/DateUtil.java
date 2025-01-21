package com.b2b.hotel.in.utils;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass
public class DateUtil {
    private static final SimpleDateFormat INPUT_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
    private static final SimpleDateFormat OUTPUT_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static String convertToDate(String input) throws ParseException {
        Date date = INPUT_FORMAT.parse(input);
        return OUTPUT_FORMAT.format(date);
    }
}
