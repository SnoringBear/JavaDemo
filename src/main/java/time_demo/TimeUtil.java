package time_demo;

import java.util.Date;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.SystemClock;
import cn.hutool.core.util.StrUtil;

/**
 * 时间工具
 *
 * <pre>
 * 此工具的1~7表示周一~周日。<br>
 * 固定的跨天时间点和周开始时间,可自己创建一个TimeCustom类的静态实例
 * </pre>
 */
public class TimeUtil {
    /** 默认跨天跨周封装 */
    public static final TimeLogic DEFAULT = TimeLogic.builder()
            .dayIsToday(1)
            .weekIsToday(1)
            .monthIsToday(1)
            .build();

    /*--------------------------------工具--------------------------------*/

    /**
     * 生成用于每个服务器定时的偏差,用于分散定时器触发循环任务的时间
     *
     * @param unit     时间单位的毫秒值
     * @param range    范围值
     * @param serverId 服务器id
     * @return 时间偏差值(毫秒)
     */
    public static long genDeviation(long unit, long range, int serverId) {
        long lastTime = SystemClock.now();
        lastTime = lastTime / unit / range;
        lastTime = (lastTime + 1) * unit * range;// 计算下次的整数
        long diff = serverId;/// 差值使用服务器id计算
        diff = diff % range * unit;
        return lastTime + diff;
    }

    /**
     * 获取【指定时间】距离【上次重置时间】以【指定周起点】和【指定跨天点】为界限是否可重置
     *
     * @param before   上次重置时间
     * @param after    指定时间
     * @param firstDay 哪天重置,0每日、1~7为星期一~星期天
     * @param t        跨天点(HH:mm:ss或者HH:mm)
     * @return 是否可重置
     */
    public static boolean isReset(long before, long after, int firstDay, String t) {
        if (before >= after) {// 前面时间大于后面时间,有异常
            throw new RuntimeException(StrUtil.format("比较的时间,前一个时间大于后一个时间,before:{},after:{}", before, after));
        }
        if (firstDay == 0) {
            return !isSameDay(before, after, t);
        } else {
            return !isSameWeek(before, after, firstDay, t);
        }
    }

    /*--------------------------------转换--------------------------------*/

    /**
     * 时间格式转换,转换为【[简单时间】表达式,无星期
     *
     * <pre>
     * timeStr可省略部分配置
     * 2T01:00:00,表示星期2的1点----2T01、2T01:00
     * 0T01:00:00,表示每天1点----01:00:00、01、01：00
     * </pre>
     *
     * @param timeStr 时间表达式
     * @return 简单时间表达式-HH:mm:ss
     */
    public static String convertSimpleTime(String timeStr) {
        String timeStrTmp = timeStr.trim();
        if (timeStrTmp.contains("T")) {
            String[] strs = timeStrTmp.split("T");
            timeStrTmp = strs[strs.length - 1];
        }

        String[] strs = timeStrTmp.split(":");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            if (i >= strs.length) {
                sb.append(":00");
            } else {
                StringBuilder str = new StringBuilder(strs[i]);
                for (int j = str.length(); j < 2; j++) {
                    str.insert(0, "0");
                }
                if (i > 0) {
                    sb.append(":");
                }
                sb.append(str);
            }
        }

        return sb.toString();
    }

    /**
     * 时间格式转换,转换为【完整时间】表达式,有星期
     *
     * <pre>
     * timeStr可省略部分配置
     * 2T01:00:00,表示星期2的1点----2T01、2T01:00
     * 0T01:00:00,表示每天1点----01:00:00、01、01：00
     * </pre>
     *
     * @param timeStr 时间表达式
     * @return 完整时间表达式-nTHH:mm:ss
     */
    public static String convertFullTime(String timeStr) {
        String timeStrTmp = timeStr.trim();
        String weekStr = null;
        if (timeStrTmp.contains("T")) {
            String[] strs = timeStrTmp.split("T");
            weekStr = strs[0];
        }
        if (StrUtil.isBlank(weekStr)) {
            weekStr = "0";
        }
        timeStrTmp = convertSimpleTime(timeStrTmp);

        return weekStr + "T" + timeStrTmp;
    }

    /**
     * 时间格式转换,转换为【cron】表达式,仅支持每天和每周几,不支持每小时
     *
     * <pre>
     * timeStr可省略部分配置
     * 2T01:00:00,表示星期2的1点----2T01、2T01:00
     * 0T01:00:00,表示每天1点----01:00:00、01、01：00
     * cronStr
     * 0 0 10 ? * 7 表示周6早上10点0分
     * 0 0 10 * * ? 表示每天早上10点0分
     * </pre>
     *
     * @param timeStr 时间表达式
     * @return cron表达式
     */
    public static String convertCronExpre(String timeStr) {
        String timeStrTmp = timeStr.trim();
        timeStrTmp = convertFullTime(timeStrTmp);
        String week = timeStrTmp.substring(0, 1);// 星期

        String hour = timeStrTmp.substring(2, 4);// 时
        if (hour.startsWith("0")) {
            hour = hour.substring(1, 2);
        }

        String minutes = timeStrTmp.substring(5, 7);// 分
        if (minutes.startsWith("0")) {
            minutes = minutes.substring(1, 2);
        }

        String seconds = timeStrTmp.substring(8, 10);// 秒
        if (seconds.startsWith("0")) {
            seconds = seconds.substring(1, 2);
        }

        String result;
        if (week.equals("0")) {
            result = StrUtil.format("{} {} {} * * ?", seconds, minutes, hour);
        } else {
            int v = Integer.parseInt(week);
            if (v == 7) {
                v = 1;
            } else {
                v++;
            }
            result = StrUtil.format("{} {} {} ? * {}", seconds, minutes, hour, v);
        }
        return result;
    }

    /*--------------------------------拼接--------------------------------*/

    /**
     * 拼接时间,使用【当前时间】的日期和【指定时间(HH:mm:ss)】的进行拼接
     *
     * @param t 时间(HH:mm:ss或者HH:mm)
     * @return 时间戳(毫秒),连接后时间
     */
    public static long connectTime(String t) {
        return connectTime(SystemClock.now(), t);
    }

    /**
     * 拼接时间,使用【指定时间戳日期】的日期和【指定时间(HH:mm:ss)】的进行拼接
     *
     * @param d 时间戳日期,用于取日期
     * @param t 时间(HH:mm:ss或者HH:mm)
     * @return 时间戳(毫秒),连接后时间
     */
    public static long connectTime(long d, String t) {
        String dateStr = DateUtil.formatDate(new Date(d));
        String timeStr = convertSimpleTime(t);
        String dateTimeStr = dateStr + " " + timeStr;
        return DateUtil.parseDateTime(dateTimeStr).getTime();
    }

    /*--------------------------------天--------------------------------*/

    /**
     * 获取【指定时间】以【指定跨天点】为界限的上次跨天时间
     *
     * @param time 需要计算跨天时间的时间戳
     * @param t    跨天点(HH:mm:ss或者HH:mm)
     * @return 跨天时间(毫秒)
     */
    public static long getLastDayTime(long time, String t) {
        TimeLogic logic = TimeLogic.builder().crossDayTime(t).build();
        return logic.getLastDayTime(time);
    }

    /**
     * 获取【当前时间】以【指定跨天点】为界限的上次跨天时间
     *
     * @param t 跨天点(HH:mm:ss或者HH:mm)
     * @return 跨天时间(毫秒)
     */
    public static long getLastDayTime(String t) {
        return getLastDayTime(SystemClock.now(), t);
    }

    /**
     * 获取【指定时间】以【指定跨天点】为界限的下次跨天时间
     *
     * @param time 需要计算跨天时间的时间戳
     * @param t    跨天点(HH:mm:ss或者HH:mm)
     * @return 跨天时间(毫秒)
     */
    public static long getNextDayTime(long time, String t) {
        TimeLogic logic = TimeLogic.builder().crossDayTime(t).build();
        return logic.getNextDayTime(time);
    }

    /**
     * 获取【当前时间】以【指定跨天点】为界限的下次跨天时间
     *
     * @param t 跨天点(HH:mm:ss或者HH:mm)
     * @return 跨天时间(毫秒)
     */
    public static long getNextDayTime(String t) {
        return getNextDayTime(SystemClock.now(), t);
    }

    /**
     * 判断【两个时间】以【指定跨天点】为界限是否在同一天
     *
     * @param time1 需要判定的时间戳(毫秒)
     * @param time2 需要判定的时间戳(毫秒)
     * @param t     跨天点(HH:mm:ss或者HH:mm)
     * @return 是否同一天
     */
    public static boolean isSameDay(long time1, long time2, String t) {
        TimeLogic logic = TimeLogic.builder().crossDayTime(t).build();
        return logic.isSameDay(time1, time2);
    }

    /**
     * 判断以【指定跨天点】为界限是否是今天
     *
     * @param time 需要判定的时间戳(毫秒)
     * @param t    跨天点(HH:mm:ss或者HH:mm)
     * @return 是否是今天
     */
    public boolean isNowDay(long time, String t) {
        return isSameDay(time, SystemClock.now(), t);
    }

    /*--------------------------------周--------------------------------*/

    /**
     * 获取【指定时间】以【指定周起点】和【指定跨天点】为界限的上次跨周时间
     *
     * @param time     需要计算跨周时间的时间戳
     * @param firstDay 周起点,1~7表示周一~周日
     * @param t        跨天点(HH:mm:ss或者HH:mm)
     * @return 跨周时间(毫秒)
     */
    public static long getLastWeekTime(long time, int firstDay, String t) {
        TimeLogic logic = TimeLogic.builder().crossDayTime(t).crossWeekDay(firstDay).build();
        return logic.getLastWeekTime(time);
    }

    /**
     * 获取【当前时间】以【指定周起点】和【指定跨天点】为界限的上次跨周时间
     *
     * @param firstDay 周起点,1~7表示周一~周日
     * @param t        跨天点(HH:mm:ss或者HH:mm)
     * @return 跨周时间(毫秒)
     */
    public static long getLastWeekTime(int firstDay, String t) {
        return getLastWeekTime(SystemClock.now(), firstDay, t);
    }

    /**
     * 获取【指定时间】以【指定周起点】和【指定跨天点】为界限的下次跨周时间
     *
     * @param time     需要计算跨周时间的时间戳
     * @param firstDay 周起点,1~7表示周一~周日
     * @param t        跨天点(HH:mm:ss或者HH:mm)
     * @return 跨周时间(毫秒)
     */
    public static long getNextWeekTime(long time, int firstDay, String t) {
        TimeLogic logic = TimeLogic.builder().crossDayTime(t).crossWeekDay(firstDay).build();
        return logic.getNextWeekTime(time);
    }

    /**
     * 获取【当前时间】以【指定周起点】和【指定跨天点】为界限的下次跨周时间
     *
     * @param firstDay 周起点,1~7表示周一~周日
     * @param t        跨天点(HH:mm:ss或者HH:mm)
     * @return 跨周时间(毫秒)
     */
    public static long getNextWeekTime(int firstDay, String t) {
        return getNextWeekTime(SystemClock.now(), firstDay, t);
    }

    /**
     * 判断【两个时间】以【指定周起点】和【指定跨天点】为界限是否在同一周
     *
     * @param time1    需要判定的时间戳(毫秒)
     * @param time2    需要判定的时间戳(毫秒)
     * @param firstDay 周起点,1~7表示周一~周日
     * @param t        跨天点(HH:mm:ss或者HH:mm)
     * @return 是否同一周
     */
    public static boolean isSameWeek(long time1, long time2, int firstDay, String t) {
        TimeLogic logic = TimeLogic.builder().crossDayTime(t).crossWeekDay(firstDay).build();
        return logic.isSameWeek(time1, time2);
    }

    /**
     * 判断以【指定周起点】和【指定跨天点】为界限是否是本周
     *
     * @param time     需要判定的时间戳(毫秒)
     * @param firstDay 周起点,1~7表示周一~周日
     * @param t        跨天点(HH:mm:ss或者HH:mm)
     * @return 是否是本周
     */
    public static boolean isNowWeek(long time, int firstDay, String t) {
        return isSameWeek(time, SystemClock.now(), firstDay, t);
    }

    /*--------------------------------月--------------------------------*/

    /**
     * 获取【指定时间】以【指定跨天点】为界限的上次跨月时间
     *
     * @param time 需要计算跨月时间的时间戳
     * @param t    跨天点(HH:mm:ss或者HH:mm)
     * @return 跨月时间(毫秒)
     */
    public static long getLastMonthTime(long time, String t) {
        TimeLogic logic = TimeLogic.builder().crossDayTime(t).build();
        return logic.getLastMonthTime(time);
    }

    /**
     * 获取【当前时间】以【指定跨天点】为界限的上次跨月时间
     *
     * @param t 跨天点(HH:mm:ss或者HH:mm)
     * @return 跨月时间(毫秒)
     */
    public static long getLastMonthTime(String t) {
        return getLastMonthTime(SystemClock.now(), t);
    }

    /**
     * 获取【指定时间】以【指定跨天点】为界限的下次跨月时间
     *
     * @param time 需要计算跨月时间的时间戳
     * @param t    跨天点(HH:mm:ss或者HH:mm)
     * @return 跨月时间(毫秒)
     */
    public static long getNextMonthTime(long time, String t) {
        TimeLogic logic = TimeLogic.builder().crossDayTime(t).build();
        return logic.getNextMonthTime(time);
    }

    /**
     * 获取【当前时间】以【指定跨天点】为界限的下次跨月时间
     *
     * @param t 跨天点(HH:mm:ss或者HH:mm)
     * @return 跨月时间(毫秒)
     */
    public static long getNextMonthTime(String t) {
        return getNextMonthTime(SystemClock.now(), t);
    }

    /**
     * 判断【两个时间】以【指定跨天点】为界限是否在同一月
     *
     * @param time1 需要判定的时间戳(毫秒)
     * @param time2 需要判定的时间戳(毫秒)
     * @param t     跨天点(HH:mm:ss或者HH:mm)
     * @return 是否同一月
     */
    public static boolean isSameMonth(long time1, long time2, String t) {
        TimeLogic logic = TimeLogic.builder().crossDayTime(t).build();
        return logic.isSameMonth(time1, time2);
    }

    /**
     * 判断以【指定跨天点】为界限是否是本月
     *
     * @param time 需要判定的时间戳(毫秒)
     * @param t    跨天点(HH:mm:ss或者HH:mm)
     * @return 是否是本月
     */
    public boolean isNowMonth(long time, String t) {
        return isSameMonth(time, SystemClock.now(), t);
    }

}
