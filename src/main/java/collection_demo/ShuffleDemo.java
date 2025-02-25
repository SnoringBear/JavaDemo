package collection_demo;

import java.util.*;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class ShuffleDemo {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ShuffleDemo.class);
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
            logger.info("virtualPlayers:{}",virtualPlayers);
            if (virtualPlayers.contains(100L)) {
                virtualPlayers.remove(100L);
            }
            subListK.addAll(virtualPlayers);

        }
        List<Long> list = new ArrayList<>(subListK);
        list = list.subList(0, Math.min(list.size(), 5));
        logger.info("list:{}",list);
    }

    public static List<Long> drawVirtualPlayers(){
        List<Long> list = new ArrayList<>();
        for (long i=0;i<20;i++){
            list.add(i);
        }
        return list;
    }
}
