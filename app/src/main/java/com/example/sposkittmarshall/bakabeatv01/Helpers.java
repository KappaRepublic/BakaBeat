package com.example.sposkittmarshall.bakabeatv01;

import java.util.concurrent.TimeUnit;

/**
 * Created by sposk_000 on 2015/11/27.
 */
public class Helpers {
    public String convertToMinutes(int milliseconds)
    {
        // Create a new string
        String result = new String();
        // Convert the given value to minutes and seconds
        int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        int seconds = (int)TimeUnit.MILLISECONDS.toSeconds(milliseconds) - (int)TimeUnit.MINUTES.toSeconds(minutes);
        // Put minutes and seconds together then return the result
        if (seconds <= 9)
        {
            result = minutes + ":0" + seconds;
        }
        else
        {
            result = minutes + ":" + seconds;
        }
        return result;
    }
}
