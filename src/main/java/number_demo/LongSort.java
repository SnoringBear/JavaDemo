package number_demo;

import java.util.Arrays;

public class LongSort {
    public static void main(String[] args) {
        long[] arr = {6,4,1,9};
        long[] array = Arrays.stream(arr).sorted().toArray();
        Arrays.stream(array).forEach(System.out::println);
    }
}
