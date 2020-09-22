package com.thinkingcao.store.common.utils;

import com.thinkingcao.store.common.exception.DateFormatException;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @desc:  时间格式化处理工具类
 * @author: cao_wencao
 * @date: 2020-09-22 17:55
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils{

    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDD = "yyyyMMdd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static String getCommonDate() {
        return dateTimeNow(YYYYMMDD);
    }

    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date) {
        if (date == null) {
            return null;
        }
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 计算两个时间戳差
     */
    public static boolean getDateComparison(String endDate, long sec) {
        long ns = 1000;
        Date nowDate = new Date();
        // 获得两个时间的毫秒时间差异
        long diff = nowDate.getTime() / ns - Long.valueOf(endDate);
        if (diff > 180) {
            return true;
        }
        return false;
    }

    /**
     * 功能描述: <br>
     * 〈获取当前时间〉
     *
     * @Param: []
     * @Return: java.util.Date
     * @Author: songxinzhang
     * @Date: 2020/5/14 9:42
     */
    public static Date getDateFormat() throws ParseException {
        return DateUtils.dateTime(YYYY_MM_DD, getDate());
    }

    /*
     * 功能描述: <br>
     * 〈获取日期的前多少天〉
     * @Param: [beginDate, beforDate]
     * @Return: java.lang.String
     * @Author: songxinzhang
     * @Date: 2020/5/14 9:44
     */
    public static Date getBeforeMonths(Date beginDate, int beforAmout) {
        return addMonths(beginDate, beforAmout);
    }

    /**
     * 功能描述: <br>
     * 〈获取一个月前的日期字符串〉
     *
     * @Param: []
     * @Return: java.lang.String
     * @Author: songxinzhang
     * @Date: 2020/5/14 9:54
     */
    public static String getBeforeOneMouthDay() {
        return parseDateToStr(YYYY_MM_DD, getBeforeMonths(getNowDate(), 1));
    }

    /**
     * 功能描述: <br>
     * 〈获取三个月前的日期字符串〉
     *
     * @Param: []
     * @Return: java.lang.String
     * @Author: songxinzhang
     * @Date: 2020/5/14 9:54
     */
    public static String getBeforeThreeMouthDay() {
        return parseDateToStr(YYYY_MM_DD, getBeforeMonths(getNowDate(), 3));
    }

    /**
     * 功能描述: <br>
     * 〈获取半年前的日期字符串〉
     *
     * @Param: []
     * @Return: java.lang.String
     * @Author: songxinzhang
     * @Date: 2020/5/14 9:54
     */
    public static String getHalfYear() {
        return parseDateToStr(YYYY_MM_DD, getBeforeMonths(getNowDate(), 6));
    }

    /**
     * 功能描述: <br>
     * 〈获取该月"2020-06"的开始日期"2020-06-01"〉
     *
     * @Param: [mouthDate]
     * @Return: java.lang.String
     * @Author: songxinzhang
     * @Date: 2020/5/15 14:22
     */
    public static String getMouthStartTime(String mouthDate, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseDate(mouthDate));
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        mouthDate = sdf.format(cal.getTime());
        return mouthDate;
    }

    /**
     * 功能描述: <br>
     * 〈获取该月"2020-06"的开始日期"2020-06-01"〉
     *
     * @Param: [mouthDate]
     * @Return: java.lang.String
     * @Author: songxinzhang
     * @Date: 2020/5/15 14:22
     */
    public static String getMouthEndTime(String mouthDate, String pattern) {
        try{
            Calendar cal = Calendar.getInstance();
            cal.setTime(parseDate(mouthDate));
            //获取某月最小天数
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            //设置日历中月份的最大天数
            cal.set(Calendar.DAY_OF_MONTH, lastDay);
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            mouthDate = sdf.format(cal.getTime());
            return mouthDate;
        }catch (Exception e){
            throw new DateFormatException("时间格式异常");
        }
    }

    /**
     * 功能描述: <br>
     * 〈获取几个月前的日期〉
     *
     * @Param: []
     * @Return: java.lang.String
     * @Author: songxinzhang
     * @Date: 2020/5/25 16:06
     */
    public static String getBeforeMouth(int beforeMouth, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - beforeMouth);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(cal.getTime());
    }

    public static String getLastDayOfMonth(String yearMonth) {
        int year = Integer.parseInt(yearMonth.split("-")[0]);  //年
        int month = Integer.parseInt(yearMonth.split("-")[1]); //月
        Calendar cal = Calendar.getInstance();
        // 设置年份
        cal.set(Calendar.YEAR, year);
        // 设置月份
        // cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.MONTH, month); //设置当前月的上一个月
        // 获取某月最大天数
        //int lastDay = cal.getActualMaximum(Calendar.DATE);
        int lastDay = cal.getMinimum(Calendar.DATE); //获取月份中的最小值，即第一天
        // 设置日历中月份的最大天数
        //cal.set(Calendar.DAY_OF_MONTH, lastDay);
        cal.set(Calendar.DAY_OF_MONTH, lastDay - 1); //上月的第一天减去1就是当月的最后一天
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

}
