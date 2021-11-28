package cn.bzu.hair.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date stringToDate(String stringDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = sdf.parse(stringDate);
        } catch (ParseException var4) {
            var4.printStackTrace();
        }

        return date;
    }
}
