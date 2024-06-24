package collection;

import java.util.Arrays;
import java.util.Comparator;

public class ArraysTest {
    public static void main(String[] args) {
        sort2();
    }

    public static void sort1() {
        // Java 类型  变量
        // Golang 变量  类型
        int[] a = {1, 6, 3, 9, 5};
        // 会改变原数组
        Arrays.parallelSort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort2() {
        S[] XX = {new S(1), new S(6), new S(3), new S(9), new S(5)};
        Arrays.parallelSort(XX,Comparator.comparingInt(S::getA));
        System.out.println(Arrays.toString(XX));
    }
}

class S {
    int a;

    public S(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "S{" +
                "a=" + a +
                '}';
    }
}
