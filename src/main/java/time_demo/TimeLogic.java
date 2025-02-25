package time_demo;

import java.util.Calendar;
import java.util.Date;

import cn.hutool.core.date.*;
import lombok.*;

/**
 * 时间工具-逻辑
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeLogic {
    /** 跨天点时间(HH:mm:ss或者HH:mm) */
    @Builder.Default
    private String crossDayTime = "00:00:00";// 默认0点跨天
    /**
     * 跨周起点,计算跨周时的开始天
     *
     * <pre>
     * 1~7表示周一~周日
     * </pre>
     */
    @Builder.Default
    private int crossWeekDay = 1;// 默认周一
    /**
     * 今天是第几天
     *
     * <pre>
     * 0表示今天是第0天,明天与今天比较,相差1天
     * 1表示今天是第1天,明天与今天比较,相差2天
     * </pre>
     */
    private int dayIsToday;
    /**
     * 本周是第几周
     *
     * <pre>
     * 0表示今天是第0周,下周与本周比较,相差1周
     * 1表示今天是第1周,下周与本周比较,相差2周
     * </pre>
     */
    private int weekIsToday;
    /**
     * 本月是第几月
     *
     * <pre>
     * 0表示今天是第0月,下月与本月比较,相差1月
     * 1表示今天是第1月,下月与本月比较,相差2月
     * </pre>
     */
    private int monthIsToday;
    /**
     * 提前跨天算日期
     *
     * <pre>
     * 设跨天时间=22点,当前时间=2001-03-31 23:00:00(周五)
     *
     * 值为true
     *  getWeekDay(当前时间)=6--周六
     *  getMonthDay(当前时间)=1--1号
     *  getMonthOfYear(当前时间)=4--4月
     * </pre>
     */
    private boolean crossEarly;

    /*--------------------------------天--------------------------------*/

    /**
     * 获取【指定时间】跨天【指定目标天数】的跨天时间(快进N天)
     *
     * @param time 需要计算跨天时间的时间戳
     * @param day  目标天数,结果为上次跨天时间的参数值为{@link TimeLogic#dayIsToday}
     * @return 跨天时间(毫秒)
     */
    public long getDayTime(long time, int day) {
        long tmpTime = TimeUtil.connectTime(time, crossDayTime);
        if (tmpTime > time) {
            tmpTime -= DateUnit.DAY.getMillis();
        }
        if (day - dayIsToday != 0) {
            tmpTime += DateUnit.DAY.getMillis() * (day - dayIsToday);
        }
        return tmpTime;
    }

    /**
     * 获取【指定时间】的上次跨天时间
     *
     * @param time 需要计算跨天时间的时间戳
     * @return 跨天时间(毫秒)
     */
    public long getLastDayTime(long time) {
        return getDayTime(time, dayIsToday);
    }

    /**
     * 获取【当前时间】的上次跨天时间
     *
     * @return 跨天时间(毫秒)
     */
    public long getLastDayTime() {
        return getLastDayTime(SystemClock.now());
    }

    /**
     * 获取【指定时间】的下次跨天时间
     *
     * @param time 需要计算跨天时间的时间戳
     * @return 跨天时间(毫秒)
     */
    public long getNextDayTime(long time) {
        return getDayTime(time, dayIsToday + 1);
    }

    /**
     * 获取【当前时间】的下次跨天时间
     *
     * @return 跨天时间(毫秒)
     */
    public long getNextDayTime() {
        return getNextDayTime(SystemClock.now());
    }

    /**
     * 获取【指定基础时间】与【指定目标时间】的跨天次数
     *
     * @param baseTime   基础时间(时间戳)
     * @param targetTime 目标时间(时间戳)
     * @return 跨天次数,{@link TimeLogic#dayIsToday}为同一天
     */
    public int getDayPass(long baseTime, long targetTime) {
        if (baseTime == targetTime) {
            return dayIsToday;
        }
        baseTime = getLastDayTime(baseTime);
        targetTime = getLastDayTime(targetTime);
        if (baseTime == targetTime) {
            return dayIsToday;
        }
        return (int) ((targetTime - baseTime) / DateUnit.DAY.getMillis()) + dayIsToday;
    }

    /**
     * 判断【两个时间】是否在同一天
     *
     * @param time1 需要判定的时间戳(毫秒)
     * @param time2 需要判定的时间戳(毫秒)
     * @return 是否同一天
     */
    public boolean isSameDay(long time1, long time2) {
        time1 = getLastDayTime(time1);
        time2 = getLastDayTime(time2);
        return time1 == time2;
    }

    /**
     * 判断是否是今天
     *
     * @param time 需要判定的时间戳(毫秒)
     * @return 是否是今天
     */
    public boolean isNowDay(long time) {
        return isSameDay(time, SystemClock.now());
    }

    /*--------------------------------周--------------------------------*/

    /**
     * 获取【指定时间】跨周【指定目标周数】的跨周时间(快进N周)
     *
     * @param time 需要计算跨周时间的时间戳
     * @param week 目标周数,结果为上次跨周时间的参数值为{@link TimeLogic#weekIsToday}
     * @return 跨周时间(毫秒)
     */
    public long getWeekTime(long time, int week) {
        int firstDay = crossWeekDay;
        if (firstDay <= 0 || firstDay > 7) {
            throw new RuntimeException("星期的值错误,1~7表示周一~周日,当前值:" + firstDay);
        }
        long tmpTime = getLastDayTime(time);// 上次跨天时间

        // 1~7=周一~周日 ==> 周日~周六为1~7
        firstDay = firstDay == 7 ? 1 : firstDay + 1;

        // 获取给定日期当前周的开始时间
        Calendar c = CalendarUtil.calendar(tmpTime);
        c.setFirstDayOfWeek(firstDay);
        c = CalendarUtil.truncate(c, DateField.WEEK_OF_MONTH);
        tmpTime = c.getTimeInMillis();// 时间是周起点0点

        // 计算N周后
        if (week - weekIsToday != 0) {
            tmpTime += DateUnit.WEEK.getMillis() * (week - weekIsToday);// 时间为N周后的周起点0点
        }

        return TimeUtil.connectTime(tmpTime, crossDayTime);// 时间为N周后的周起点跨天时间;
    }

    /**
     * 获取【指定时间】的上次跨周时间
     *
     * @param time 需要计算跨周时间的时间戳
     * @return 跨周时间(毫秒)
     */
    public long getLastWeekTime(long time) {
        return getWeekTime(time, weekIsToday);
    }

    /**
     * 获取【当前时间】的上次跨周时间
     *
     * @return 跨周时间(毫秒)
     */
    public long getLastWeekTime() {
        return getLastWeekTime(SystemClock.now());
    }

    /**
     * 获取【指定时间】的下次跨周时间
     *
     * @param time 需要计算跨周时间的时间戳
     * @return 跨周时间(毫秒)
     */
    public long getNextWeekTime(long time) {
        return getWeekTime(time, weekIsToday + 1);
    }

    /**
     * 获取【当前时间】的下次跨周时间
     *
     * @return 跨周时间(毫秒)
     */
    public long getNextWeekTime() {
        return getNextWeekTime(SystemClock.now());
    }

    /**
     * 获取【指定基础时间】与【指定目标时间】的跨周次数
     *
     * @param baseTime   基础时间(时间戳)
     * @param targetTime 目标时间(时间戳)
     * @return 跨周次数,{@link TimeLogic#weekIsToday}为同一周
     */
    public int getWeekPass(long baseTime, long targetTime) {
        if (baseTime == targetTime) {
            return weekIsToday;
        }
        baseTime = getLastWeekTime(baseTime);
        targetTime = getLastWeekTime(targetTime);
        if (baseTime == targetTime) {
            return weekIsToday;
        }
        return (int) ((targetTime - baseTime) / DateUnit.WEEK.getMillis()) + weekIsToday;
    }

    /**
     * 判断【两个时间】是否在同一周
     *
     * @param time1 需要判定的时间戳(毫秒)
     * @param time2 需要判定的时间戳(毫秒)
     * @return 是否同一周
     */
    public boolean isSameWeek(long time1, long time2) {
        time1 = getLastWeekTime(time1);
        time2 = getLastWeekTime(time2);
        return time1 == time2;
    }

    /**
     * 判断是否是本周
     *
     * @param time 需要判定的时间戳(毫秒)
     * @return 是否是本周
     */
    public boolean isNowWeek(long time) {
        return isSameWeek(time, SystemClock.now());
    }

    /**
     * 获取【指定时间】是星期几
     *
     * @param time 需要计算星期几的时间戳
     * @return 1~7为星期一~星期天
     */
    public int getWeekDay(long time) {
        int week = getCrossCalValue(time, Calendar.DAY_OF_WEEK);
        // 周日~周六为1~7 ==> 1~7=周一~周日
        week = week == 1 ? 7 : week - 1;
        return week;
    }

    /*--------------------------------月--------------------------------*/

    /**
     * 获取【指定时间】跨月【指定目标月数】的跨月时间(快进N月)
     *
     * @param time  需要计算跨月时间的时间戳
     * @param month 目标月数,结果为上次跨月时间的参数值为{@link TimeLogic#monthIsToday}
     * @return 跨月时间(毫秒)
     */
    public long getMonthTime(long time, int month) {
        long tmpTime = getLastDayTime(time);
        tmpTime = DateUtil.beginOfMonth(new Date(tmpTime)).getTime();
        if (month - monthIsToday != 0) {
            tmpTime = DateUtil.offsetMonth(new Date(tmpTime), month - monthIsToday).getTime();
        }
        return TimeUtil.connectTime(tmpTime, crossDayTime);
    }

    /**
     * 获取【指定时间】的上次跨月时间
     *
     * @param time 需要计算跨月时间的时间戳
     * @return 跨月时间(毫秒)
     */
    public long getLastMonthTime(long time) {
        return getMonthTime(time, monthIsToday);
    }

    /**
     * 获取【当前时间】的上次跨月时间
     *
     * @return 跨月时间(毫秒)
     */
    public long getLastMonthTime() {
        return getLastMonthTime(SystemClock.now());
    }

    /**
     * 获取【指定时间】的下次跨月时间
     *
     * @param time 需要计算跨月时间的时间戳
     * @return 跨月时间(毫秒)
     */
    public long getNextMonthTime(long time) {
        return getMonthTime(time, monthIsToday + 1);
    }

    /**
     * 获取【当前时间】的下次跨月时间
     *
     * @return 跨月时间(毫秒)
     */
    public long getNextMonthTime() {
        return getNextMonthTime(SystemClock.now());
    }

    /**
     * 获取【指定基础时间】与【指定目标时间】的跨月次数
     *
     * @param baseTime   基础时间(时间戳)
     * @param targetTime 目标时间(时间戳)
     * @return 跨月次数,{@link TimeLogic#monthIsToday}为同一月
     */
    public int getMonthPass(long baseTime, long targetTime) {
        if (baseTime == targetTime) {
            return monthIsToday;
        }
        baseTime = getLastMonthTime(baseTime);
        targetTime = getLastMonthTime(targetTime);
        if (baseTime == targetTime) {
            return monthIsToday;
        }
        return (int) DateUtil.betweenMonth(new Date(baseTime), new Date(targetTime), true) + monthIsToday;
    }

    /**
     * 判断【两个时间】是否在同一月
     *
     * @param time1 需要判定的时间戳(毫秒)
     * @param time2 需要判定的时间戳(毫秒)
     * @return 是否同一月
     */
    public boolean isSameMonth(long time1, long time2) {
        time1 = getLastMonthTime(time1);
        time2 = getLastMonthTime(time2);
        return time1 == time2;
    }

    /**
     * 判断是否是本月
     *
     * @param time 需要判定的时间戳(毫秒)
     * @return 是否是本月
     */
    public boolean isNowMonth(long time) {
        return isSameMonth(time, SystemClock.now());
    }

    /**
     * 获取【指定时间】是月的第几天
     *
     * @param time 需要计算月的第几天的时间戳
     * @return 月的第几天
     */
    public int getMonthDay(long time) {
        return getCrossCalValue(time, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取【指定时间】是第几月
     *
     * @param time 需要计算第几月的时间戳
     * @return 第几月,1~12月=1~12
     */
    public int getMonthOfYear(long time) {
        return getCrossCalValue(time, Calendar.MONTH) + 1;
    }

    /**
     * 获取【指定时间】是第几年
     *
     * @param time 需要计算第几年的时间戳
     * @return 第几年
     */
    public int getYear(long time) {
        return getCrossCalValue(time, Calendar.YEAR);
    }

    /*--------------------------------通用方法--------------------------------*/

    /**
     * 获取【指定时间】的上次跨天时间的【指定日历字段】值
     *
     * @param time  时间戳
     * @param field 日历字段
     * @return 日历字段值
     */
    private int getCrossCalValue(long time, int field) {
        long tmpTime = getLastDayTime(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(tmpTime));
        // 提前跨天算日期
        if (crossEarly && DateUtil.isSameDay(new Date(time), new Date(tmpTime))) {
            cal.add(Calendar.DAY_OF_YEAR, 1);
        }
        return cal.get(field);
    }

}
