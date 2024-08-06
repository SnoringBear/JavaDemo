package List;

import java.util.Arrays;

public class SortTest {
    public static void main(String[] args) {
        long[] array = {5L, 1L, 4L, 2L, 8L};

        Arrays.sort(array, (x, y) -> Long.compare(x, y));

        System.out.println(Arrays.toString(array));
    }
}
