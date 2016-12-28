/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author nazmul hasan
 */
public class Utils {
    public Utils()
    {
    
    }
    
    /**
     * This method will convert unix time into human date
     * @param unixSeconds, unix time in second
     * @return String, date
     */
    public String convertUnixToHuman(long unixSeconds)
    {
        Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+6")); // give a timezone reference for formating (see comment at the bottom
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
    
    /**
     * This method will return current time in unix
     * @return long, unix time
     */
    public long getCurrentUnixTime()
    {
        long unixTime = System.currentTimeMillis() / 1000L;
        return unixTime;
    }
}
