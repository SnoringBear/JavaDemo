package java8;

import java.sql.SQLOutput;
import java.time.*;

public class TimeTest {
    public static void main(String[] args) {
        test3();
    }
    public static void test1(){
        String openTimeStr="T0:00";
        String[] timeStr = openTimeStr.split(":");
        String[] ts = timeStr[0].split("T");
        int hour = Integer.parseInt(ts[1]);
        int minute = Integer.parseInt(timeStr[1]);
        // 获取当前日期
        LocalDateTime now = LocalDateTime.now();
        now = now.plusDays(0);
        // 设置指定的小时和分钟
        LocalDateTime specificTime = now.withHour(hour).withMinute(minute).withSecond(0).withNano(0);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = specificTime.atZone(zoneId);
        long epochMilli = zonedDateTime.toInstant().toEpochMilli();
        System.out.println(epochMilli);
    }

    public static void test2(){
        System.out.println(LocalDateTime.now().getHour());
    }

    public static void test3(){
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        System.out.println(dayOfWeek.getValue());
    }
}
