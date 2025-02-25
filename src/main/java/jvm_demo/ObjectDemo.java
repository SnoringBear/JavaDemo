package jvm_demo;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class ObjectDemo {
    /**
     * 日志
     */
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ObjectDemo.class);

    // JDK源码中多处把对象设置为null,并注释help GC,真的有效果吗?
    private static final Object lock = new Object();

    public static void main(String[] args) {
        // new Thread(ObjectDemo::test1).start();
        new Thread(ObjectDemo::test2).start();
    }

    public static void test1() {
        try {
            new Object().wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void test2() {
        synchronized (lock) {
            try {
                lock.wait(); // 在同步块中调用wait()
            } catch (InterruptedException e) {
                logger.error("test2 interrupted:[{}]", e.getMessage());
            }
        }
        synchronized (lock) {
            lock.notify(); // 在同步块中调用notify()
        }
        // 在调用 wait()、notify() 或 notifyAll() 之前，确保线程已经通过 synchronized 语句或同步方法获取了对象的监视器
    }

}
