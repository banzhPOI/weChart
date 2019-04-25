package com.poison.wechart.common.utils;


import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getNowStr() {
        Date now = new Date();
        SimpleDateFormat format0 = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowStr = format0.format(now);//这个就是把时间戳经过处理得到期望格式的时间
        return nowStr;
    }

}

