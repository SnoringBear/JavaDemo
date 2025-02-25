package thread_demo.thread;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class ThreadDemo {
    /** 日志 */
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ThreadDemo.class);

    public static void main(String[] args) {
        daemon();
    }

    /**
     * 是否是守护线程
     */
    public static void daemon(){
        // 线程：用户线程，守护线程
        // 一般线程默认为用户线程，当用户线程数量为0时，守护线程会停止执行，JVM进程也会关闭
        // 与golang不一样的是，golang主携程结束后，整个golang进程都会结束，java主线程结束以后，如果还有其他用户线程在运行，jvm进程不会结束
        boolean daemon = Thread.currentThread().isDaemon();
        logger.info("当前线程是否是守护线程: [{}]",daemon);
    }


}
