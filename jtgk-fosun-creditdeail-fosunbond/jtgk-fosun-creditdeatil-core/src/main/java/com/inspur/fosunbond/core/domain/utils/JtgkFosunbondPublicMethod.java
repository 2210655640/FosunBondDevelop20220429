package com.inspur.fosunbond.core.domain.utils;

import java.util.Calendar;
import java.util.Date;

public class JtgkFosunbondPublicMethod {
    public static Date getPreDay(Date date)
    {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        date=calendar.getTime();
        return  date;
    }
}
