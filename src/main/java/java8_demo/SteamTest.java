package java8_demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ch.qos.logback.classic.Logger;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

@Getter
@Setter
class Person {
    private final String name;
    private final int age;

    // 构造器、getter和setter省略
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

@Slf4j
public class SteamTest {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(SteamTest.class);

    public static void main(String[] args) {
        // 它提供了一种对集合数据[实现Collection接口]进行高效、简洁和声明式处理的方式
        //1、声明式编程：您只需描述要做什么，而不是如何去做，使得代码更具可读性和可维护性。
        //2、链式操作：可以将多个操作链接在一起，形成一个处理流水线。  *****
        //3、惰性求值：在终端操作被调用之前，中间操作不会执行，这有助于优化性能

        // steam常见操作
        //filter();
        //map();
        //sorted();
        //aggregationOperations();
        //distinct();
        collectOne();
    }

    /**
     * 过滤
     */
    public static void filter() {
        logger.info("-----------------过滤-----------------");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        logger.info("evenNumbers = {}", evenNumbers);
    }

    /**
     * 映射
     */
    public static void map() {
        logger.info("-----------------映射-----------------");
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        List<Integer> nameLengths = names.stream()
                .map(String::length)
                .collect(Collectors.toList());
        logger.info("nameLengths:{}", nameLengths);
    }

    /**
     * 排序
     */
    public static void sorted() {
        logger.info("-----------------排序-----------------");
        List<Integer> unsortedNumbers = Arrays.asList(5, 1, 3, 2, 4);
        List<Integer> sortedNumbers = unsortedNumbers.stream()
                .sorted()
                .collect(Collectors.toList());
        logger.info("sortedNumbers:{}", sortedNumbers);
    }

    /**
     * 聚合操作
     */
    public static void aggregationOperations() {
        logger.info("-----------------聚合操作-----------------");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        logger.info("sum = {}", sum);

        long count = numbers.size();
        logger.info("count ={} ", count);

        double average = numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);

        logger.info("average = {}", average);

        int max = numbers.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
        logger.info("max = {}", max);

        int min = numbers.stream()
                .mapToInt(Integer::intValue)
                .min()
                .orElse(0);
        logger.info("min = {}", min);
    }

    /**
     * 去重
     */
    public static void distinct() {
        logger.info("-----------------去重-----------------");
        List<Integer> numbersWithDuplicates = Arrays.asList(1, 2, 2, 3, 3, 3);
        List<Integer> distinctNumbers = numbersWithDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());
        logger.info("distinctNumbers ={} ", distinctNumbers);
    }

    /**
     * 收集一个对象列表里面的某一个元素集合
     */
    public static void collectOne() {
        List<Person> people = Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 35)
        );

        List<Integer> ages = people.stream()
                .map(Person::getAge)
                .collect(Collectors.toList());
        logger.info("ages:{}",ages);// 输出: [30,
    }
}

