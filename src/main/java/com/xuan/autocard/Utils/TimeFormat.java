package com.xuan.autocard.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormat {
    public static String now() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"  ";
    }
}
