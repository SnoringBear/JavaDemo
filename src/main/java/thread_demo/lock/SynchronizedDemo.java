package thread_demo.lock;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class SynchronizedDemo {
    /** 日志 */
    private static final Logger logger = (Logger) LoggerFactory.getLogger(SynchronizedDemo.class);
    /** 锁 */
    private static final Object lock = new Object();

    public static void main(String[] args) {
        // 通过JVM实现
        for (int i = 0; i < 10; i++) {
            new Thread(SynchronizedDemo::correctWaitNotify).start();
        }
    }

    /**
     * wait notify
     */
    public static void correctWaitNotify() {
        synchronized (lock) {
            try {
                logger.info("111thread name is:[{}] ", Thread.currentThread().getName());
                lock.wait(1000L); // 在同步块中调用wait()
            } catch (InterruptedException e) {
                logger.error("correctWaitNotify异常:[{}]", e.getMessage());
            }
            logger.info("222thread name is:[{}] ", Thread.currentThread().getName());
        }
        synchronized (lock) {
            lock.notify(); // 在同步块中调用notify()
        }
    }
}
