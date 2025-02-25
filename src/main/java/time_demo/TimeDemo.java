package time_demo;

import ch.qos.logback.classic.Logger;
import cn.hutool.core.date.SystemClock;
import org.slf4j.LoggerFactory;

public class TimeDemo {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(TimeDemo.class);
    public static void main(String[] args) {
        test1();
        test2();
    }

    /**
     * test1
     */
    public static void test1(){
        long dayTime = TimeUtil.DEFAULT.getDayTime(SystemClock.now(), 3);
        logger.info("SystemClock.now():{},dayTime:{}",SystemClock.now(),dayTime);
        // SystemClock.now():2025-01-20 16:58:58     dayTime:2025-01-22 00:00:00   dayTime:3
    }

    /**
     * 两个时间相隔跨多少天
     */
    public static void test2(){
        int dayPass = TimeUtil.DEFAULT.getDayPass(1737363813925L, 1737475200000L);
        logger.info("dayPass:{}",dayPass);
    }
}
