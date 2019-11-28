package com.system.power.powerEarlyWarning.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ==================================================
 * fileName     : DateUtils
 *
 * @author : Wang Sen Rong
 * @description : 日期转换工具类
 * @create : 2019/11/26
 * ==================================================
 */
@Slf4j
public class UtilDate {

    public static final String DATE_TIME_FORMAT1 = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_TIME_FORMAT2 = "yyyy-MM-dd";
    /**
     *========================================
     * @Author: Wang Sen Rong
     * @Description: 字符串转日期
     * @create: 2019/11/26 18:47
     *========================================
    */
    public static Date parseDate1(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT1);
        try {
            return simpleDateFormat.parse(date);
        } catch (Exception var3) {
            log.info("日期转换异常");
        }
        return null;
    }

    /**
     *========================================
     * @Author: Wang Sen Rong
     * @Description: 字符串转日期
     * @create: 2019/11/26 18:47
     *========================================
     */
    public static Date parseDate2(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT2);
        try {
            return simpleDateFormat.parse(date);
        } catch (Exception var3) {
            log.info("日期转换异常");
        }
        return null;
    }


    /**
     *========================================
     * @Author: Wang Sen Rong
     * @Description: 日期转字符串
     * @create: 2019/11/26 18:47
     *========================================
     */
    public static String parseString(Date date) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(DATE_TIME_FORMAT2);
        return simpleDateFormat.format(date);
    }
}