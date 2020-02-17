package com.ignathick.hotel2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String datePattern = "yyyy-MM-dd";
    private static final String nullStr  = "null";
    private static final SimpleDateFormat format = new SimpleDateFormat(datePattern);

    public static Date getDateFromString(String stringDate) {

        if (stringDate.equals(nullStr)) {
            return null;
        }
        //SimpleDateFormat format = new SimpleDateFormat(datePattern);
        String dateString = format.format(new Date());
        Date someDate = new Date();

        try {
            someDate = format.parse(stringDate);
        } catch (Exception ex) {
            System.out.println("Error in date convert");
        }

        return someDate;
    }

    public static String getDateAsString(Date date){
        //SimpleDateFormat format = new SimpleDateFormat(datePattern);
        if (date == null) {
            return "null";
        } else {
            String dateString = format.format(date);
            return dateString;
        }
    }

}
