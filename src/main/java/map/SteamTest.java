package map;

import java.util.HashMap;
import java.util.Map;

public class SteamTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        long count = map.values().stream()
                .filter(item -> item != null && item.equals("1"))
                .count();
        System.out.println(count);
    }
}
