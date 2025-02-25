package java8_demo;

import java.time.*;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class TimeTest {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(TimeTest.class);
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
        logger.info("epochMilli:{}",epochMilli);
    }

    public static void test2(){
        logger.info("LocalDateTime.now().getHour():{}",LocalDateTime.now().getHour());
    }

    public static void test3(){
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        logger.info("dayOfWeek.getValue():{}",dayOfWeek.getValue());
    }
}
