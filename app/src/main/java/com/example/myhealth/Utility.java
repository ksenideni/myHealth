package com.example.myhealth;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
    public static String getCurrentTimestamp() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
            String currentDateTime = dateFormat.format(new Date());

            return currentDateTime;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMonthFromNumber(String monthNumber){
        switch(monthNumber){
            case "01":{
                return "Январь";
            }
            case "02":{
                return "Февраль";
            }
            case "03":{
                return "Март";
            }
            case "04":{
                return "Апрель";
            }
            case "05":{
                return "Май";
            }
            case "06":{
                return "Июнь";
            }
            case "07":{
                return "Июль";
            }
            case "08":{
                return "Август";
            }
            case "09":{
                return "Сентябрь";
            }
            case "10":{
                return "Октябрь";
            }
            case "11":{
                return "Ноябрь";
            }
            case "12":{
                return "Декабрь";
            }
            default:{
                return "Error";
            }
        }
    }

}
