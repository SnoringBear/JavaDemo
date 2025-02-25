package number_demo;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

@Slf4j
public class RangeTest {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(RangeTest.class);
    public static void main(String[] args) {
        logger.info("test(880):{}",test(880));
        logger.info("test(1100):{}",test(1100));
        logger.info("test(1111):{}",test(1111));
        logger.info("test(1200):{}",test(1200));
        logger.info("test(1201):{}",test(1201));
    }

    public static int test(int serverId) {
        int[] intA1 = {1100, 1200};
        if (serverId <= intA1[0]) {
            return 1;
        }
        if (serverId > intA1[intA1.length - 1]) {
            return intA1.length + 1;
        }
        for (int i = 1; i < intA1.length; i++) {
            if (intA1[i - 1] < serverId && serverId <= intA1[i]) {
                return i + 1;
            }
        }
        return 1;
    }
}
