package hutool_demo;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class JDKDemo {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(JDKDemo.class);
    public static void main(String[] args) {
        vmName();
    }

    /**
     * 获取虚拟机名称
     */
    public static void vmName() {
        logger.info("java.vm.name:{}",System.getProperty("java.vm.name"));
    }
}
