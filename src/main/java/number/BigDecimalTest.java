package number;

import java.math.BigDecimal;

public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("2");
        System.out.println(a.compareTo(b));
        System.out.println(b.compareTo(a));
    }
}
