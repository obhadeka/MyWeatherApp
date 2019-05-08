package com.exmaple.util;

public class CommonUtil {
    public static String getDayOfWeek(String day) {
        switch(day) {
            case "1":
                return "Sunday";
            case "2":
                return "Monday";
            case "3":
                return "Tuesday";
            case "4":
                return "Wednesday";
            case "5":
                return "Thursday";
            case "6":
                return "Friday";
            case "7":
                return "Saturday";
            default:
                return "";
        }
    }
}
