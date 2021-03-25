package com.honeysense.magpie.utils.format;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MagpieTimeFormat {
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    public static String formatElapsed(long seconds) {
        long day, hour, minute, second;

        if (seconds > 60) {
            second = seconds % 60;
            minute = seconds / 60;

            if (minute > 60) {
                minute = (seconds / 60) % 60;
                hour = (seconds / 60) / 60;

                if (hour > 24) {
                    hour = ((seconds / 60) / 60) % 24;
                    day = (((seconds / 60) / 60) / 24);

                    return String.format("%d %02d:%02d:%02d", day, hour, minute, second);
                } else {
                    return String.format("%02d:%02d:%02d", hour, minute, second);
                }
            } else {
                return String.format("00:%02d:%02d", minute, second);
            }
        } else {
            second = seconds;
            return String.format("00:00:%02d", second);
        }
    }

    /**
     * 当天时间
     *
     * @return 当天时间
     */
    public static Long getToday() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return Long.valueOf(df.format(new Date()));
    }
}
