package java8_demo;

import java.util.HashMap;
import java.util.Map;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class MethodReferences {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(MethodReferences.class);

    public static void main(String[] args) {
        // 方法引用提供了一种更简洁的方式来引用已有的方法, Java8 引入了方法引用，可以理解为方法引用是Lambda 表达式的另外一种表现形式。
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.values().stream().forEach(MethodReferences::print);
    }

    private static void print(String str) {
        logger.info("值:{}", str);
    }
}
