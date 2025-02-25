package thread_demo.threadpool;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

/**
 * 工作
 */
public class MyWorker implements Runnable {
    /** 日志 */
    private static final Logger logger = (Logger) LoggerFactory.getLogger(MyWorker.class);
    private final int i;

    public MyWorker(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        logger.info("[{}] 正在执行,数值: [{}]", Thread.currentThread().getName(), i);
    }
}
