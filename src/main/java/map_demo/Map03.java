package map_demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class Map03 {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Map03.class);

    public static void main(String[] args) {
        test2();
        test3();
    }

    public static void test1() {
        Map<Integer, Long> map = new HashMap<>();
        logger.info("test1 map.get(1):{}", map.get(1));
    }

    public static void test2() {
        Map<Integer, String> map = new HashMap<>();
        map.computeIfAbsent(1, k -> "hello");
        logger.info("test2 map :{}", map);
    }

    public static void test3() {
        ConcurrentHashMap<Long, Long> roleMap = new ConcurrentHashMap<>();
        roleMap.remove(1230);
    }
}
