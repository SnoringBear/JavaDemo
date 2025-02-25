package map_demo;

import java.util.HashMap;
import java.util.Map;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

@Slf4j
public class Map02 {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Map02.class);
    public static void main(String[] args) {
        absent();
        present();
        merge();
        forEach();
    }


    public static void absent() {
        logger.info("-----------------absent-----------------");
        // 如果指定的键尚未与值关联（或映射值为 null），则使用给定的函数计算其值并进行关联。
        Map<String, Integer> map = new HashMap<>();
        map.computeIfAbsent("k1", (k) -> 10);
        logger.info("absent map:{}",map);
    }


    public static void present() {
        logger.info("-----------------present-----------------");
        // 如果指定的键存在且其对应的值不为 null，则使用给定的函数重新计算其值。
        Map<String, Integer> map = new HashMap<>();
        map.put("k1", 1);
        map.computeIfPresent("k1", (k, v) -> v + 1);
        logger.info("present map:{}",map);
    }


    public static void merge() {
        logger.info("-----------------merge-----------------");
        // 如果指定的键不存在，则将键值对添加到 Map 中。如果键已经存在，则使用给定的函数合并旧值和新值
        Map<String, Integer> map = new HashMap<>();
        map.put("k2", 1);
        map.merge("k1", 1, Integer::sum);
        map.merge("k2", 1, Integer::sum);
        logger.info("merge map:{}",map);
    }

    public static void forEach() {
        logger.info("-----------------forEach-----------------");
        // 对 Map 中的每个键值对执行给定的操作。
        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 5);
        map.put("key2", 10);
        map.forEach((k, v) -> logger.info("k:{},v:{}",k,v));
    }
}
