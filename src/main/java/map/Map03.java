package map;

import java.util.HashMap;
import java.util.Map;

public class Map03 {
    public static void main(String[] args) {
        test2();
    }

    public static void test1() {
        Map<Integer, Long> map = new HashMap<>();
        System.out.println(map.get(1));
    }

    public static void test2() {
        Map<Integer,String> map = new HashMap<>();
        map.computeIfAbsent(1, k -> "hello");
        System.out.println(map);
    }
}
