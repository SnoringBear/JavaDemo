package jvm_demo;

import java.nio.charset.Charset;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class JVMCode {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(JVMCode.class);

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        String str = Charset.defaultCharset().displayName();
        logger.info("Default Charset: {}", str);

        Charset defaultCharset = Charset.defaultCharset();
        logger.info("Default Charset:{} ", defaultCharset);
    }
}
