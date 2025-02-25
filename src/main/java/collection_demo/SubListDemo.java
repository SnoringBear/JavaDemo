package collection_demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class SubListDemo {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(SubListDemo.class);
    public static void main(String[] args) {
        test2();
    }
    public static void test1(){
        List<Long> serverList = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L);
        int m = 4;
        List<List<Long>> serverIdGroup = new ArrayList<>();
        for (int i = 0; ; i++) {
            int end = (i + 1) * m;
            if (end >= serverList.size()) {
                serverIdGroup.add(serverList.subList(i * m, serverList.size()));
                break;
            }
            serverIdGroup.add(serverList.subList(i * m, end));
        }
        logger.info("test1 serverIdGroup : [{}]", serverIdGroup);
    }

    public static void test2(){
        ArrayList<Long> serverList = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L));
        // 11   0
        // result  [1,2, 3, 4, 5, 6, 7, 8, 9, 10, 11]  11
        List<Long> longs = serverList.subList(0, 11);
        logger.info("test2 serverList.subList(0, 11) : [{}]", longs);
    }
}


