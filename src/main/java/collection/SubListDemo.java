package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubListDemo {
    public static void main(String[] args) {
        test1();
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
        System.out.println(serverIdGroup);
    }
}


