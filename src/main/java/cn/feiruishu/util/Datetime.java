/*
 * Copyright (c) 2003 ZOTN.com  All rights reserved.
 * $Header: /BranchPICC-SX/public-html/WEB-INF/src/com/zotn/util/Datetime.java 1     06-11-15 18:58 Pengkun $
 */
package cn.feiruishu.util;

import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * DOCUMENT ME!
 *
 * @author Copyright (c) 2001 by zotn. All Rights Reserved.
 * @author lvjin
 * @version Datetime.java v1.0 2001-6-1 16:05 2001-6-25 14:59
 *
 * @since JDK 1.2
 */
public class Datetime {
    /** DOCUMENT ME! */
    public static final String datetimeFormat = "yyyy-MM-dd HH:mm:ss";

    /** DOCUMENT ME! */
    private int year = 0;

    /** DOCUMENT ME! */
    private int month = 0;

    /** DOCUMENT ME! */
    private int day = 0;

    /** DOCUMENT ME! */
    private int hour = 0;

    /** DOCUMENT ME! */
    private int minute = 0;

    /** DOCUMENT ME! */
    private int second =  0;

    /**
     * Creates a new Datetime object.
     *
     * @param cale DOCUMENT ME!
     */
    public Datetime(Calendar cale) {
        setDatetime(cale);
    }

    /**
     * Creates a new Datetime object.
     */
    public Datetime() {
        Calendar now = Calendar.getInstance();
        setDatetime(now);
    }

    /**
     * Creates a new Datetime object.
     *
     * @param s DOCUMENT ME!
     *
     * @throws NumberFormatException DOCUMENT ME!
     */
    public Datetime(String s) throws NumberFormatException,Exception {
        this();

        try {
            if (s.length() > 1) {
                this.year = Integer.parseInt(s.substring(0, 4));
                this.month = Integer.parseInt(s.substring(5, 7));
                this.day = Integer.parseInt(s.substring(8, 10));
            }

            if (s.length() > 10) {
                this.hour = Integer.parseInt(s.substring(11, 13));
                this.minute = Integer.parseInt(s.substring(14, 16));
                this.second = Integer.parseInt(s.substring(17, 19));
            } else {
                this.hour = 0;
                this.minute = 0;
                this.second = 0;
            }
        } catch (Exception e) {
            throw  e;
        }

        check();
    }

    /**
     * Creates a new Datetime object.
     *
     * @param d DOCUMENT ME!
     */
    public Datetime(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        setDatetime(calendar);
    }

    /**
     * DOCUMENT ME!
     *
     * @param calendar DOCUMENT ME!
     */
    public void setDatetime(Calendar calendar) {
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     */
    public String toString() {
        StringBuffer sb = new StringBuffer(19);
        try {
            check();
        } catch (Exception e) {
            return "";
        }
        sb.append(convert(this.year));
        sb.append("-");
        sb.append(convert(this.month));
        sb.append("-");
        sb.append(convert(this.day));
        sb.append(" ");
        sb.append(convert(this.hour));
        sb.append(":");
        sb.append(convert(this.minute));
        sb.append(":");
        sb.append(convert(this.second));

        return sb.toString();
    }

    private void check() throws Exception {
        if ((this.year < 1) || (this.year > 2500)) {
            throw new Exception("year is wrong");
        }

        if ((this.month < 1) || (this.month > 12)) {
            throw new Exception("month is wrong");
        }

        int flag = 0;

        if ((this.year % 4) == 0) {
            flag = 1;

            if (((this.year % 100) == 0) && ((this.year % 400) != 0)) {
                flag = 0;
            } else {
                flag = 1;
            }
        }

        if (this.day < 1) {
            throw new Exception("day is wrong");
        }

        if (this.month == 2) {
            if ((flag == 0) && (this.day > 28)) {
                throw new Exception("day is wrong");
            } else if ((flag == 1) && (this.day > 29)) {
                throw new Exception("day is wrong");
            }
        }

        if ((this.hour < 0) || (this.hour > 23)) {
            throw new Exception("hour is wrong");
        }

        if ((this.minute < 0) || (this.minute > 59)) {
            throw new Exception("minute is wrong");
        }

        if ((this.second < 0) || (this.second > 59)) {
            throw new Exception("second is wrong");
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     */
    public Date toDate() throws Exception {
        String s = toString();

        return getDateFromString(s);
    }

    private String convert(int i) {
        if (i < 10) {
            return "0" + String.valueOf(i);
        } else {
            return String.valueOf(i);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param i DOCUMENT ME!
     */
    public void setYear(int i) {
        this.year = i;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getYear() {
        return this.year;
    }

    /**
     * DOCUMENT ME!
     *
     * @param i DOCUMENT ME!
     */
    public void setMonth(int i) {
        this.month = i;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * DOCUMENT ME!
     *
     * @param i DOCUMENT ME!
     */
    public void setDay(int i) {
        this.day = i;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getDay() {
        return this.day;
    }

    /**
     * DOCUMENT ME!
     *
     * @param i DOCUMENT ME!
     */
    public void setHour(int i) {
        this.hour = i;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getHour() {
        return this.hour;
    }

    /**
     * DOCUMENT ME!
     *
     * @param i DOCUMENT ME!
     */
    public void setMinute(int i) {
        this.minute = i;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getMinute() {
        return this.minute;
    }

    /**
     * DOCUMENT ME!
     *
     * @param i DOCUMENT ME!
     */
    public void setSecond(int i) {
        this.second = i;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getSecond() {
        return this.second;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String getCurrentDatetimeByString() {
        String datetime = new Timestamp(System.currentTimeMillis()).toString();

        return datetime.substring(0, 19);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String getCurrentDateByString() {
        String datetime = new Timestamp(System.currentTimeMillis()).toString();

        return datetime.substring(0, 10);
    }

    /**
     * get current date of week type : such as '星期一',  '星期二'等
     *
     * @return current date of week type
     */
    public static String getChineseCurrentDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(calendar.DAY_OF_WEEK);
        String strWeek = "";

        switch (week) {
            case Calendar.SUNDAY:
                strWeek = "星期日";

                break;

            case Calendar.MONDAY:
                strWeek = "星期一";

                break;

            case Calendar.TUESDAY:
                strWeek = "星期二";

                break;

            case Calendar.WEDNESDAY:
                strWeek = "星期三";

                break;

            case Calendar.THURSDAY:
                strWeek = "星期四";

                break;

            case Calendar.FRIDAY:
                strWeek = "星期五";

                break;

            case Calendar.SATURDAY:
                strWeek = "星期六";

                break;
        }

        return strWeek;
    }

    /**
     * get date of week type : such as '星期一',  '星期二'等
     *
     * @return String
     */
    public String getChineseDayofWeek() throws Exception{
        int week = this.getDayOfWeek();
        String strWeek = "";

        switch (week) {
            case Calendar.SUNDAY:
                strWeek = "星期日";

                break;

            case Calendar.MONDAY:
                strWeek = "星期一";

                break;

            case Calendar.TUESDAY:
                strWeek = "星期二";

                break;

            case Calendar.WEDNESDAY:
                strWeek = "星期三";

                break;

            case Calendar.THURSDAY:
                strWeek = "星期四";

                break;

            case Calendar.FRIDAY:
                strWeek = "星期五";

                break;

            case Calendar.SATURDAY:
                strWeek = "星期六";

                break;
        }

        return strWeek;
    }

    /**
     * compare two time String with format : 12:00 , 08:00; or 12:00:00, 08:00:00
     *
     * @param firstTime the first time string
     * @param secondTime the second time string
     *
     * @return 0 -- same 1 -- first bigger than second -1 -- first smaller than second -2 --
     *         invalid time format
     */
    public static int CompareOnlyByTime(String firstTime, String secondTime) {
        try {
            String timeDelm = ":";

            //calculate the first time to integer
            int pos = firstTime.indexOf(timeDelm);
            int iFirst = Integer.parseInt(firstTime.substring(0, pos)) * 10000;
            firstTime = firstTime.substring(pos + 1);
            pos = firstTime.indexOf(timeDelm);

            if (pos > 0) {
                iFirst = iFirst + (Integer.parseInt(firstTime.substring(0, pos)) * 100)
                        + Integer.parseInt(firstTime.substring(pos + 1));
            } else {
                iFirst = iFirst + (Integer.parseInt(firstTime) * 100);
            }

            //calculate the second time string to integer
            pos = secondTime.indexOf(timeDelm);

            int iSecond = Integer.parseInt(secondTime.substring(0, pos)) * 10000;
            secondTime = secondTime.substring(pos + 1);
            pos = secondTime.indexOf(timeDelm);

            if (pos > 0) {
                iSecond = iSecond + (Integer.parseInt(secondTime.substring(0, pos)) * 100)
                        + Integer.parseInt(secondTime.substring(pos + 1));
            } else {
                iSecond = iSecond + (Integer.parseInt(secondTime) * 100);
            }

            //compare two
            if (iFirst == iSecond) {
                return 0;
            } else if (iFirst > iSecond) {
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            return -2;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param current DOCUMENT ME!
     * @param days DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String getCertainDateByString(Datetime current, int days) throws Exception{
        Calendar calendar = current.toCalendar();
        calendar.add(Calendar.DATE, days);

        String strTime = getStringFromDate(calendar.getTime());

        return strTime.substring(0, 10);
    }

    /**
     * DOCUMENT ME!
     *
     * @param days DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String getCertainDateByString(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);

        String strTime = getStringFromDate(calendar.getTime());

        return strTime.substring(0, 10);
    }

    /**
     * get the first date string of current week, format: 2002-07-01 it's monday not sunday
     *
     * @return the first date string of current week, it's monday not sunday
     */
    public static String getFirstDateStringOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(calendar.MONDAY);

        int iCount = calendar.getFirstDayOfWeek() - calendar.get(calendar.DAY_OF_WEEK);

        return getCertainDateByString(iCount);
    }

    /**
     * DOCUMENT ME!
     *
     * @param current DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String getFirstDateStringOfWeek(Datetime current) throws Exception{
        Calendar calendar = current.toCalendar();
        calendar.setFirstDayOfWeek(calendar.MONDAY);

        int iCount = calendar.getFirstDayOfWeek() - calendar.get(calendar.DAY_OF_WEEK);

        return getCertainDateByString(current, iCount);
    }

    //Last day of the week;
    public static String getLastDateStringOfWeek() throws Exception{
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(calendar.MONDAY);

        int iCount = (calendar.getFirstDayOfWeek() + 6) - calendar.get(calendar.DAY_OF_WEEK);

        return getCertainDateByString(iCount);
    }

    /**
     * DOCUMENT ME!
     *
     * @param current DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String getLastDateStringOfWeek(Datetime current) throws Exception{
        Calendar calendar = current.toCalendar();
        calendar.setFirstDayOfWeek(calendar.MONDAY);

        int iCount = (calendar.getFirstDayOfWeek() + 6) - calendar.get(calendar.DAY_OF_WEEK);

        return getCertainDateByString(current, iCount);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Date getCurrentDateByDate() {
        Calendar calendar = Calendar.getInstance();

        return calendar.getTime();
    }

    /**
     * DOCUMENT ME!
     *
     * @param s DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     */
    public static Date getDateFromString(String s)  throws Exception{
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(datetimeFormat);

            return sdf.parse(s);
        } catch (ParseException e) {
            throw e;
        }

    }

    /**
     * DOCUMENT ME!
     *
     * @param d DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String getStringFromDate(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat(datetimeFormat);

        return sdf.format(d);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getNoDeliCurrentDate() {
        StringBuffer sb = new StringBuffer();
        sb.append(convert(this.year));
        sb.append(convert(this.month));
        sb.append(convert(this.day));

        return sb.toString();
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getNoDeliCurrentTime() {
        StringBuffer sb = new StringBuffer();
        sb.append(convert(this.year));
        sb.append(convert(this.month));
        sb.append(convert(this.day));
        sb.append(convert(this.hour));
        sb.append(convert(this.minute));
        sb.append(convert(this.second));

        return sb.toString();
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDate() {
        StringBuffer sb = new StringBuffer();
        sb.append(convert(this.year));
        sb.append("-");
        sb.append(convert(this.month));
        sb.append("-");
        sb.append(convert(this.day));

        return sb.toString();
    }

    /**
     * DOCUMENT ME!
     *
     * @param days the added days to this datetime, it could be positive number or minus number
     *
     * @return new datetime
     *
     * @throws Exception DOCUMENT ME!
     */
    public Datetime addDays(int days) throws Exception {
        return manipulate(Calendar.DATE, days);
    }

    /**
     * DOCUMENT ME!
     *
     * @param months the added months to this datetime, it could be positive number or minus number
     *
     * @return new datetime
     *
     * @throws Exception DOCUMENT ME!
     */
    public Datetime addMonths(int months) throws Exception {
        return manipulate(Calendar.MONTH, months);
    }

    /**
     * DOCUMENT ME!
     *
     * @param years the added years to this datetime, it could be positive number or minus number
     *
     * @return new datetime
     *
     * @throws Exception DOCUMENT ME!
     */
    public Datetime addYears(int years) throws Exception {
        return manipulate(Calendar.YEAR, years);
    }

    private Datetime manipulate(int type, int interval)
            throws Exception {
        check();

        Calendar cale = Calendar.getInstance();
        cale.set(this.year, this.month - 1, this.day, this.hour, this.minute);
        cale.add(type, interval);

        return new Datetime(cale);
    }

    /**
     * DOCUMENT ME!
     *
     * @param hours the added hours to this datetime, it could be positive number or minus number
     *
     * @return new datetime
     *
     * @throws Exception DOCUMENT ME!
     */
    public Datetime addHours(int hours) throws Exception {
        return manipulate(Calendar.HOUR_OF_DAY, hours);
    }

    /**
     * DOCUMENT ME!
     *
     * @param minutes the added minutes to this datetime, it could be positive number or minus
     *        number
     *
     * @return new datetime
     *
     * @throws Exception DOCUMENT ME!
     */
    public Datetime addMinutes(int minutes) throws Exception {
        return manipulate(Calendar.MINUTE, minutes);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getSeasonOfYear() {
        if ((this.month > 0) && (this.month < 4)) {
            return 1;
        }

        if ((this.month > 3) && (this.month < 7)) {
            return 2;
        }

        if ((this.month > 6) && (this.month < 10)) {
            return 3;
        }

        if ((this.month > 9) && (this.month <= 12)) {
            return 4;
        }

        return 0;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    public Calendar toCalendar() throws Exception {
        check();

        Calendar cale = Calendar.getInstance();
        cale.set(this.year, this.month - 1, this.day, this.hour, this.minute);

        return cale;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    public int getWeekOfYear() throws Exception {
        Calendar cale = this.toCalendar();

        return cale.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    public int getWeekOfMonth() throws Exception {
        Calendar cale = this.toCalendar();

        return cale.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    public int getDayOfWeek() throws Exception {
        Calendar cale = this.toCalendar();

        return cale.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    public int getFirstDayOfWeek() throws Exception {
        Calendar cale = this.toCalendar();

        return cale.getFirstDayOfWeek();
    }

    /**
     * DOCUMENT ME!
     *
     * @param y DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static boolean isLeap(int y) {
        return ((((y % 4) == 0) && ((y % 100) != 0)) || ((y % 400) == 0)) ? true : false;
    }

    /**
     * DOCUMENT ME!
     *
     * @param month DOCUMENT ME!
     * @param year DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static int getNoOfDaysInMth(int month, int year) {
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;

            case 2:
                return (isLeap(year) == true) ? 29 : 28;

            default:
                return 31;
        }
    }

    public static String ChineseYearMonthDay(String date){
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        if(month.substring(0,1).equals("0")){
            month = month.substring(1,2);
        }
        String day = date.substring(8,10);
        if(day.substring(0,1).equals("0")){
            day = day.substring(1,2);
        }
        String chineseday = year +"年"+ month+"月" + day+"日";

        return chineseday;
    }
    /**
     * DOCUMENT ME!
     *
     * @param date DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String YearMonthDay(String date) {
        String year = date.substring(0, 4); // 年
        String month = date.substring(5, 7); // 月
        String day = date.substring(8, 10); // 日

        String y1 = year.substring(0, 1); //年 字符1
        String y2 = year.substring(1, 2); //年 字符1
        String y3 = year.substring(2, 3); //年 字符3
        String y4 = year.substring(3, 4); //年 字符4

        String m1 = month.substring(0, 1); // 月 字符1
        String m2 = month.substring(1, 2); // 月 字符2

        String d1 = day.substring(0, 1); // 日 1
        String d2 = day.substring(1, 2); // 日 2

        String Y1 = Chinese(y1);
        String Y2 = Chinese(y2);
        String Y3 = Chinese(y3);
        String Y4 = Chinese(y4);

        String M1 = Chinese(m1);
        String M2 = "";

        if (!m2.equals("0")) {
            M2 = Chinese(m2);
        }

        String D1 = Chinese(d1);
        String D2 = "";

        if (!D2.equals("0")) {
            D2 = Chinese(d2);
        }

        String YEAR = Y1 + Y2 + Y3 + Y4 + "年";
        String MONTH = "月";

        if (Integer.parseInt(month) > 9) {
            MONTH = "十" + M2 + MONTH;
        } else {
            MONTH = M2 + MONTH;
        }

        String DAY = "日";

        if (Integer.parseInt(day) > 10 && (Integer.parseInt(day) < 20)) {
            DAY = "十" + D2 + DAY;
        } else if(Integer.parseInt(day) > 20 && (Integer.parseInt(day) != 30)){
            DAY = D1 + "十" +D2 + DAY ;
        } else if(Integer.parseInt(day) == 10){
            DAY = "十" + DAY;
        } else if(Integer.parseInt(day) == 20){
            DAY = "二十" + DAY ;
        } else if(Integer.parseInt(day) == 30){
            DAY = "三十" + DAY ;
        } else {
            DAY = D2 + DAY;
        }

        String chineseday = YEAR + MONTH + DAY;

        return chineseday;
    }

    /**
     * 判断是否是指定日期所在月份的最后一天,如果是，返回true ,否则返回false
     *
     * @return
     */
    public static boolean isLastDayOfMonth(Date date) throws Exception{
        String result =getLastDayOfMonth(date);
        String dat2="";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dat2 = formater.format(date);
        } catch (Exception e) {
        }
        return  result.equals(dat2);
    }

    /**
     * 得到月末最后一天的日期
     * @param date
     * @return
     */
    public static String getLastDayOfMonth(Date date){
        String result = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        try {
            result = formater.format(cal.getTime());
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 取得指定日期过 day 天后的日期 (当 day 为负数表示指日期之前);
     *            日期 为null时表示当天
     *            相加(相减)的月数
     */

    public static String nextDay(int day) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, day);
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        try {
            result = formater.format(cal.getTime());
        } catch (Exception e) {

        }
        return result;
    }

    /**
     * 判断是否是年末最后一天。
     * @param date
     * @return
     */
    public static boolean isLastDayOfYear(Date date){
        String result = getLastDayOfMonth(date);
        return  result.endsWith("12-31");
    }

    public static String Chinese1(String str){
        String chineseStr = "";
    	/*int i = str.length() ;
    	if(i==1){
    		chineseStr = ChineseDa(str);
    	}else if(i == 2){
    		chineseStr = ChineseDa(str.substring(0,1))+"拾"+ChineseDa(str.substring(1,2));
    	}else if(i==3){
    		if(str.substring(1,2).equals("0")&&str.substring(2,3).equals("0")){
    			chineseStr = ChineseDa(str.substring(0,1))+"佰";
    		}else if(str.substring(1,2).equals("0")&&!str.substring(2,3).equals("0")){
    			chineseStr = ChineseDa(str.substring(0,1))+"佰零"+ChineseDa(str.substring(2,3));
    		}else{
    			chineseStr = ChineseDa(str.substring(0,1))+"佰"+ChineseDa(str.substring(1,2))+"拾"+ChineseDa(str.substring(2,3));
    		}
    	}*/
        int i = Integer.parseInt(str);
        if(i>=0&&i<=9){
            chineseStr = ChineseDa(str);
        }else if(i==10){
            chineseStr = "拾";
        }else if(i>10 && i<=99 ){
            if(i%10 == 0){
                chineseStr = ChineseDa(str.substring(0,1))+"拾";
            }else{
                chineseStr = ChineseDa(str.substring(0,1))+"拾"+ChineseDa(str.substring(1,2));
            }
        }else if(i>=100&&i<=999){
            if(str.substring(1,2).equals("0")&&str.substring(2,3).equals("0")){
                chineseStr = ChineseDa(str.substring(0,1))+"佰";
            }else if(str.substring(1,2).equals("0")&&!str.substring(2,3).equals("0")){
                chineseStr = ChineseDa(str.substring(0,1))+"佰零"+ChineseDa(str.substring(2,3));
            }else if(!str.substring(1,2).equals("0") && str.substring(2,3).equals("0")){
                chineseStr = ChineseDa(str.substring(0,1))+"佰"+ChineseDa(str.substring(1,2))+"拾";
            }else {
                chineseStr = ChineseDa(str.substring(0,1))+"佰"+ChineseDa(str.substring(1,2))+"拾"+ChineseDa(str.substring(2,3));
            }
        }
        return chineseStr;
    }
    /**
     * DOCUMENT ME!
     *
     * @param y DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String Chinese(String y) {
        String chinese = "";
        int x = Integer.parseInt(y);
        switch (x) {
            case 0:
                chinese = "○";

                break;

            case 1:
                chinese = "一";

                break;

            case 2:
                chinese = "二";

                break;

            case 3:
                chinese = "三";

                break;

            case 4:
                chinese = "四";

                break;

            case 5:
                chinese = "五";

                break;

            case 6:
                chinese = "六";

                break;

            case 7:
                chinese = "七";

                break;

            case 8:
                chinese = "八";

                break;

            case 9:
                chinese = "九";

                break;

            default:}

        return chinese;
    }


    public static String ChineseDa(String y) {
        String chinese = "";
        int x = Integer.parseInt(y);

        switch (x) {
            case 0:
                chinese = "零";

                break;

            case 1:
                chinese = "壹";

                break;

            case 2:
                chinese = "贰";

                break;

            case 3:
                chinese = "叁";

                break;

            case 4:
                chinese = "肆";

                break;

            case 5:
                chinese = "伍";

                break;

            case 6:
                chinese = "陆";

                break;

            case 7:
                chinese = "柒";

                break;

            case 8:
                chinese = "捌";

                break;

            case 9:
                chinese = "玖";

                break;
            case 10:
                chinese = "拾";

                break;
            default:}

        return chinese;
    }

    /*
     * 该方法用于比较两个用字符串表示的时间
     * param strTime1,strTime2 要求的格式为'HH:MM:SS'
     * 返回值为两个时间相差的小时数
     */
    public static int compareTime(String strTime1, String strTime2)
            throws Exception {
        int returnVal = 0;

        try {
            Date date1 = getDateFromString("0000-00-00 " + strTime1);
            Date date2 = getDateFromString("0000-00-00 " + strTime2);
            returnVal = (int) ((date2.getTime() - date1.getTime()) / (1000 * 60 ));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return returnVal;
    }

    /*
     * 该方法用于比较两个用字符串表示的日期
     * param strDate1,strDate2 要求的格式为'yyyy-mm-dd'
     * 返回值为两个日期相差的天数
     */
    public static int compareDate(String strDate1, String strDate2)
            throws Exception {
        int returnVal = 0;

        try {
            Date date1 = getDateFromString(strDate1 + " 00:00:00");
            Date date2 = getDateFromString(strDate2 + " 00:00:00");
            returnVal = (int) ((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return returnVal;
    }

    /*
     * 该方法用于比较两个用字符串表示的时间
     * param strDate1,strDate2 要求的格式为'yyyy-mm-dd hh:mm:ss'
     * 返回值为两个日期相差的数
     */
    public static int compareDatetime(String strDate1, String strDate2)
            throws Exception {
        int returnVal = 0;

        try {
            Date date1 = getDateFromString(strDate1);
            Date date2 = getDateFromString(strDate2);
            returnVal = (int) ((date2.getTime() - date1.getTime()) );
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return returnVal;
    }
    /**
     * DOCUMENT ME!
     *
     * @param strDate DOCUMENT ME!
     * @param days DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String addDate(String strDate, int days) throws Exception{
        Datetime dateTime = new Datetime(strDate);

        if (days != 0) {
            dateTime = dateTime.addDays(days);
        }

        return dateTime.toString().substring(0, 10);
    }

    /**
     *  通过 年 月 取当月 天数
     * @param month
     * @param year
     * @return
     */
    public static int getDayOfMonth(int month,int year)
    {
        int  days = 0;
        if(month==2)
        {
            if(year%4!=0)
            {
                days = 28;
            }
            else
            {
                if(year%100==0 && year%400 != 0)
                {
                    days = 28;
                }
                else
                {
                    days = 29;
                }
            }
        }
        else
        {
            switch(month)
            {
                case 1:
                    days = 31;
                    break;
                case 3:
                    days = 31;
                    break;
                case 4:
                    days = 30;
                    break;
                case 5:
                    days = 31;
                    break;
                case 6:
                    days = 30;
                    break;
                case 7:
                    days = 31;
                    break;
                case 8:
                    days = 31;
                    break;
                case 9:
                    days = 30;
                    break;
                case 10:
                    days = 31;
                    break;
                case 11:
                    days = 30;
                    break;
                case 12:
                    days = 31;
                    break;
            }
        }
        return days;
    }

    /*
     * 该方法用于给指定日期增加月份
     * param strDate要求的格式为'yyyy-mm-dd'
     * 返回值为增加月份后的日期
     */
    public static Datetime addMonth(String strDate,int val)
            throws Exception{
        Datetime date = null;
        try {
            date = new Datetime(strDate).addMonths(val);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return date;
    }

    /*
     * 该方法用于比较两个用字符串表示的日期
     * param strDate1,strDate2 要求的格式为'yyyy-mm-dd'
     * 返回值为两个日期相差的月份数
     */
    public static int compareMonth(String strDate1, String strDate2)
            throws Exception{
        int returnVal = 0;
        Datetime date1 = new Datetime(strDate1);
        Datetime date2 = new Datetime(strDate2);
        try {
            int yearVal = date2.getYear() - date1.getYear();
            if(yearVal < 0){
                throw new Exception("后边的日期必须大于前边的日期！");
            }
            //同一年
            if(yearVal == 0){
                returnVal = date2.getMonth() - date1.getMonth()+1;
                if(returnVal <= 0){
                    throw new Exception("后边的日期必须大于前边的日期！");
                }
            }else if(yearVal == 1){//相隔一年
                returnVal = 12-date1.getMonth()+1+date2.getMonth();
            }else{//相隔两年以上
                returnVal = (yearVal-1)*12+12-date1.getMonth()+1+date2.getMonth();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return returnVal;
    }

    /*
     * 该方法用于比较两个用字符串表示的日期 param strDate1,strDate2 要求的格式为'yyyy-mm-dd'
     * 返回值为两个日期相差的周数
     * 思路：1、计算开始时间是周几
     * 2、计算总共的天数
     * 3、如果开始时间是周一，则如果天数正好是7的整数倍，直接除以7否则再加1
     * 4、如果开始时间不是周一，则总天数减去第一周的天数，然后进行3操作
     */
    public static int compareWeek(String strDate1, String strDate2)
            throws Exception {
        int returnVal = 0;
        Datetime date1 = new Datetime(strDate1);
        Datetime date2 = new Datetime(strDate2);
        int dayOfWeek = 0;
        int totalDays = compareDate(strDate1,strDate2)+1;
        try {
            dayOfWeek = date1.getDayOfWeek();
            //如果总天数小于0

            if(totalDays<0) return 0;
            if(totalDays==0 ) return 1;
            //如果是星期日,并且总天数是7的整数倍，直接除以7
            if(dayOfWeek==1){
                if(totalDays%7==0){
                    returnVal =totalDays/7;
                }else{
                    returnVal =totalDays/7 +1;
                }
            }else {

                //第一周的天数
                int fisrtWeekDay = 0;
                if(dayOfWeek==1) {
                    fisrtWeekDay=1;
                }
                else{
                    fisrtWeekDay = 9-dayOfWeek;
                }
                //就一周，直接返回1
                if((totalDays-fisrtWeekDay) < 0){return 1;}
                if((totalDays-fisrtWeekDay)%7 == 0){
                    returnVal =(totalDays-fisrtWeekDay)/7 +1;
                }else{
                    returnVal =(totalDays-fisrtWeekDay)/7 +2;
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return returnVal;
    }

}
