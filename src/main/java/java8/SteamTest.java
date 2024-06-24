package java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SteamTest {
    public static void main(String[] args) {
        // 它提供了一种对集合数据[实现Collection接口]进行高效、简洁和声明式处理的方式
        //1、声明式编程：您只需描述要做什么，而不是如何去做，使得代码更具可读性和可维护性。
        //2、链式操作：可以将多个操作链接在一起，形成一个处理流水线。  *****
        //3、惰性求值：在终端操作被调用之前，中间操作不会执行，这有助于优化性能

        // steam常见操作
        filter();
        map();
        sorted();
        aggregationOperations();
        distinct();
    }

    /**
     * 过滤
     */
    public static void filter() {
        System.out.println("-----------------过滤-----------------");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(evenNumbers);
    }

    /**
     * 映射
     */
    public static void map() {
        System.out.println("-----------------映射-----------------");
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        List<Integer> nameLengths = names.stream()
                .map(name -> name.length())
                .collect(Collectors.toList());
        System.out.println(nameLengths.toString());
    }

    /**
     * 排序
     */
    public static void sorted() {
        System.out.println("-----------------排序-----------------");
        List<Integer> unsortedNumbers = Arrays.asList(5, 1, 3, 2, 4);
        List<Integer> sortedNumbers = unsortedNumbers.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sortedNumbers);
    }

    /**
     * 聚合操作
     */
    public static void aggregationOperations() {
        System.out.println("-----------------聚合操作-----------------");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("sum = " + sum);

        long count = numbers.stream()
                .count();
        System.out.println("count = " + count);

        double average = numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);

        System.out.println("average = " + average);

        int max = numbers.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
        System.out.println("max = " + max);

        int min = numbers.stream()
                .mapToInt(Integer::intValue)
                .min()
                .orElse(0);
        System.out.println("min = " + min);
    }

    public static void distinct() {
        System.out.println("-----------------去重-----------------");
        List<Integer> numbersWithDuplicates = Arrays.asList(1, 2, 2, 3, 3, 3);
        List<Integer> distinctNumbers = numbersWithDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("distinctNumbers = " + distinctNumbers);
    }
}
