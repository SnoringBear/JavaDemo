package thread_demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class LockDemo {
    /** 日志 */
    private static final Logger logger = (Logger) LoggerFactory.getLogger(LockDemo.class);
    /** 可重入锁 */
    private static final Lock lock = new ReentrantLock();

    private static final ReadWriteLock lock2 = new ReentrantReadWriteLock();

    /** 计数 */
    private static int count = 0;

    public static void main(String[] args) {
        // AQS原理
        // first in  first out
        // array []  0 1 2 3 4
        // list  add  底层:数组
        // queue  链表或者数组

        // first in last out
        // Stack 底层：数组  前面加入    后面取


        // Lock{ AQS[ReentrantLock、ReentrantReadWriteLock] }  Condition
        for (int i = 0; i < 100; i++) {
            new Thread(LockDemo::lock2).start();
        }
    }

    /**
     * 测试可重入锁
     */
    public static void lock1() {
        // 可重入锁的意义:同一个线程可以多次获得同一个锁而不会发生死锁。
        // 重复调用lock 【If the current thread already holds the lock then the hold count is incremented by one and the method returns immediately】
        // 重复调用unlock 【If the current thread is the holder of this lock then the hold count is decremented.
        // If the hold count is now zero then the lock is released. If the current thread is not the holder of this lock then IllegalMonitorStateException is thrown.】
        lock.lock();
        try {
            count++;
        } catch (Exception e) {
            logger.warn("发生异常:[{}]", e.getMessage());
        } finally {
            lock.unlock();
        }
        logger.info("count = :[{}]", count);
    }

    /**
     * 测试可重入锁
     */
    public static void lock2() {
        // 不可重复调用lock unlock
        logger.info("第一次开始调用锁");
        lock2.writeLock().lock(); // 每次lock都会记数
        logger.info("第一次调用锁完毕");
        lock2.writeLock().lock();
        logger.info("第二次调用锁完毕");

        lock2.writeLock().unlock();
        lock2.writeLock().unlock();

        // ReentrantReadWriteLock

        // WriteLock
            // lock
                // Acquires the read lock.
                // Acquires the read lock if the write lock is not held by another thread and returns immediately.
                // If the write lock is held by another thread then the current thread becomes disabled for thread scheduling
                // purposes and lies dormant until the read lock has been acquired.
            // unlock
                // Attempts to release this lock.
                // If the number of readers is now zero then the lock is made available for write lock attempts.

        // ReadLock
    }
}
