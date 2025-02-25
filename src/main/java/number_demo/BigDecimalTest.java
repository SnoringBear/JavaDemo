package number_demo;

import java.math.BigDecimal;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class BigDecimalTest {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(BigDecimalTest.class);
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("2");
        logger.info("a.compareTo(b):{}",a.compareTo(b));
        logger.info("b.compareTo(a):{}",b.compareTo(a));
    }
}
