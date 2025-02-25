package collection_demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class CollectionDemo {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(CollectionDemo.class);
    public static void main(String[] args) {
        max();
        max2();
        min();
        min2();
        sort();
        sort2();
        shuffle();
    }

    /**
     * 计算集合中元素最大值,默认比较器
     */
    public static void max() {
        List<Integer> list = Arrays.asList(1, 23, 31, 42, 5);
        Integer max = Collections.max(list);
        logger.info("最大值max:{}", max);
    }

    /**
     * 计算集合中元素最大值,自定义比较器
     */
    public static void max2() {
        List<Integer> list = Arrays.asList(1, 23, 31, 42, 5);
        Integer max = Collections.max(list, Comparator.comparingInt((Integer a) -> a));
        logger.info("最大值max2:{}",max);
    }

    /**
     * 计算集合中元素最小值,默认比较器
     */
    public static void min() {
        List<Integer> list = Arrays.asList(1, 23, 31, 42, 5);
        Integer min = Collections.min(list);
        logger.info("最小值min:{}",min);
    }

    /**
     * 计算集合中元素最小值,自定义比较器
     */
    public static void min2() {
        List<Integer> list = Arrays.asList(1, 23, 31, 42, 5);
        Integer min = Collections.min(list, Comparator.comparingInt((Integer a) -> a));
        logger.info("最小值min2:{}",min);
    }

    /**
     * 集合排序
     * 默认比较器
     */
    public static void sort() {
        List<Integer> list = Arrays.asList(1, 23, 31, 42, 5);
        Collections.sort(list);
        logger.info("排序后集合1:{}",list);
    }

    /**
     * 集合排序
     * 自定义比较器
     */
    public static void sort2() {
        List<Integer> list = Arrays.asList(1, 23, 31, 42, 5);
        // Collections.sort(list,Comparator.comparingInt((Integer a) -> a))
        list.sort(Comparator.comparingInt((Integer a) -> a));
        logger.info("排序后集合2:{}",list);
    }

    /**
     * 乱序
     */
    public static void shuffle() {
        List<Integer> list = Arrays.asList(1, 23, 31, 42, 5);
        Collections.shuffle(list);
    }
}
