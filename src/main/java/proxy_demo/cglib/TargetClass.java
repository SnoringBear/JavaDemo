package proxy_demo.cglib;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

// 目标类
public class TargetClass {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(TargetClass.class);
    public void method1() {
        logger.info("执行目标方法 method1");
    }

    public void method2() {
        logger.info("执行目标方法 method2");
    }
}




