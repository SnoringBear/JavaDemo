package collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortTest {
    public static void main(String[] args) {
        test2();
    }

    public static void test1(){
        long[] array = {5L, 1L, 4L, 2L, 8L};

//        Arrays.sort(array, (x, y) -> Long.compare(x, y));
//
//        System.out.println(Arrays.toString(array));
    }

    public static void test2(){
        List<Integer> list = Arrays.asList(8,2,5,4);
        Collections.sort(list);
        list.forEach(System.out::println);
    }
}
