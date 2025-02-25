package thread_demo.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class ForkJoinPoolDemo {
    /** 日志 */
    private static final Logger logger;
    /** ForkJoinPool */
    private static ForkJoinPool forkJoinPool;

    static {
        logger = (Logger) LoggerFactory.getLogger(ForkJoinPoolDemo.class);
        forkJoinPool = new ForkJoinPool();
    }

    public static void main(String[] args) {
        ForkJoinTask<String> submit = forkJoinPool.submit(() -> Thread.currentThread().getName());
        try {
            String s = submit.get();
            // java 这个日志库使用{}来充当占位符,感觉真的很另类
            logger.info("submit result: {}", s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
