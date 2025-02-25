package func_demo;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;


public class VoidFuncDemo {
    private static final Logger log = (Logger) LoggerFactory.getLogger(VoidFuncDemo.class);
    private static VoidFuncObject<String> func;
    static {
        init(s->log.info("传入的值为:{}",s));
    }

    public static void init(VoidFuncObject<String> f){
        func = f;
    }
    public static void main(String[] args) {
        func.call("你好");
    }
}
