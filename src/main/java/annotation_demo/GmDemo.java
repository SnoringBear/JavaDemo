package annotation_demo;

import java.lang.reflect.Method;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

/**
 * annotation   标签、额外的描述信息
 *      Class   getAnnotation()
 *      Field
 *      Method
 */
public class GmDemo {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(GmDemo.class);

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        Class<GmCtrl> aClass = GmCtrl.class;
        for (Method method : aClass.getMethods()) {
            GM annotation = method.getAnnotation(GM.class);
            if (annotation != null) {
                logger.info("cmd: {}", annotation.cmd());
                logger.info("desc: {}", annotation.desc());
                logger.info("test: {}", annotation.test());
            }
        }
    }
}
