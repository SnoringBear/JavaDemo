package thread_demo.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class ConditionLockDemo {
    /** 锁 */
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static boolean isConditionMet = false;
    /** 日志 */
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ConditionLockDemo.class);
    // Condition与Lock在Java并发编程中是一对紧密相关的概念，它们共同构成了Java并发包（java.util.concurrent）中用于线程间协调的重要机制
    public static void main(String[] args) {
        Thread waitingThread = new Thread(() -> {
            lock.lock();
            try {
                while (!isConditionMet) {
                    logger.info("Waiting for condition to be met...");
                    condition.await();
                }
                logger.info("Condition met! Proceeding...");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        });

        Thread signalingThread = new Thread(() -> {
            lock.lock();
            try {
                // Simulate some work that sets the condition
                Thread.sleep(2000);
                isConditionMet = true;
                logger.info("Condition is now met. Signaling...");
                condition.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        });

        waitingThread.start();
        signalingThread.start();
    }

}
