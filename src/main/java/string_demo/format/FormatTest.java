package string_demo.format;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class FormatTest {
    // 日志
    public static final Logger logger = (Logger) LoggerFactory.getLogger(FormatTest.class);

    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        Set<Integer> set = new HashSet<>();
        set.add(1);
        logger.info("set = [{}]",set);
    }
}
