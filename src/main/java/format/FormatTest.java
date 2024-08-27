package format;

import java.util.HashSet;
import java.util.Set;

public class FormatTest {
    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        Set<Integer> set = new HashSet<>();
        set.add(1);

        System.out.printf("set = %s \n",set);
    }
}
