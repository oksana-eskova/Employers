package com.oieskova.employers.utils;

import java.util.Calendar;

public class DataUtils {
    public static int getAge(String birthday){
        if((birthday!=null)&&(!birthday.isEmpty())&&!birthday.equals("null")) {
            String[] dataElements = birthday.split("-");
            int yearBorn;
            if (dataElements[0].length() == 4) {
                yearBorn = Integer.parseInt(dataElements[0]);
            } else {
                yearBorn = Integer.parseInt(dataElements[2]);
            }
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            return currentYear - yearBorn;
        }else{
            return 0;
        }
    }
}
