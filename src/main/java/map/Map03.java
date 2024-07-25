package map;

import java.util.HashMap;
import java.util.Map;

public class Map03 {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Map<Integer, Long> map = new HashMap<>();
        System.out.println(map.get(1));
    }
}
