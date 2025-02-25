package collection_demo;

import java.util.Arrays;
import java.util.List;

public class FilterDemo {
    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        List<Integer> list1 = Arrays.asList(1,2,3,4,5,6,7,8,9);
        List<Integer> list2= Arrays.asList(6,7,8,9);

        list1.stream().filter(x->!list2.contains(x)).forEach(System.out::println);
    }
}
