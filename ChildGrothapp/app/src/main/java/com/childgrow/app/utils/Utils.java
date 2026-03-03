package com.childgrow.app.utils;

public class Utils {

    public static String dateFormatter(int year, int month, int day) {
        if (year > 0 && month > 0 && day > 0) {
            return year + "/" + month + "/" + day;
        } else{
            return "";
        }
    }
}
