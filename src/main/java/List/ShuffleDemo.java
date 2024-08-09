package List;

import java.util.*;

public class ShuffleDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            test1();
        }
    }
    public static void test1(){
        Set<Long> subListK = new TreeSet<>();
        if (subListK.size() != 5) {
            // 补充虚拟玩家
            List<Long> virtualPlayers = drawVirtualPlayers();
            Collections.shuffle(virtualPlayers);
            System.out.println(virtualPlayers);
            if (virtualPlayers.contains(100)) {
                virtualPlayers.remove(100);
            }
            subListK.addAll(virtualPlayers);

        }
        List<Long> list = new ArrayList<>(subListK);
        list = list.subList(0, Math.min(list.size(), 5));
        System.out.println(list);
    }

    public static List<Long> drawVirtualPlayers(){
        List<Long> list = new ArrayList<>();
        for (long i=0;i<20;i++){
            list.add(i);
        }
        return list;
    }
}
