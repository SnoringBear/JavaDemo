package proxy_demo.instrumentation;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class ReaderDemo {
    // private int num = 0;  不能添加、删除字段，否则不能热更
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ReaderDemo.class);
     public void test(){
         int num = 0;
         logger.info("hello world222");
         for (int i = 0; i < 10; i++) {
             new Thread(()-> logger.info("num:{}",num)).start();
         }
         logger.info("num:{}" ,num);
         // removeFunction();  会抛出class redefinition failed: attempted to delete a method 错误
     }
     public void removeFunction(){
         logger.info("测试热更删除方法");
     }
}
