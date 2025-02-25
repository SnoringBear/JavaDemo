package number_demo;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class IntTest {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(IntTest.class);
    public static void main(String[] args) {
       test2();

    }

    public static void test1() {
        int dayMaxDrop = 10;
        int dropAllNum = 50;
        int v = (int) ((double) dayMaxDrop / dropAllNum * 100);
        logger.info("test1 v:{}",v);
    }

    public static void test2() {
        long dayMaxDrop = -0;
        logger.info("test2 dayMaxDrop:{}",dayMaxDrop);
    }
}
