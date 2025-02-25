package thread_demo.actor;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class ActorDemo {
    /** 日志 */
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ActorDemo.class);

    public static void main(String[] args) {
        // Java原生不支持actor模式，使用需要第三方库
        // 一般来说有两种策略用来在并发线程中进行通信：共享数据和消息传递。
        // 使用共享数据方式的并发编程面临的最大的一个问题就是数据条件竞争。处理各种锁的问题是让人十分头痛的一件事。
        // 常见抛线程  提交到特定线程里面任务队列  Executors.newSingleThreadExecutor,持有该线程池的对象设置为单例
        // 所有相关的任务都提交到相应实例线程池任务队列里
//        akka();
//        actor();
        testEnumSingleton();
        throwThread();
    }

    /**
     * 测试通过枚举来创建单例
     */
    public static void testEnumSingleton(){
        for (int i = 0; i < 10; i++) {
            CrossRankService instance = CrossRankService.Singleton.INSTANCE.getInstance();
            logger.info(instance.toString());
        }
    }

    /**
     * 提交任务到特定的线程池
     */
    public static void throwThread(){
        for (int i = 0; i < 10; i++) {
            CrossRankService singleton = CrossRankService.Singleton.INSTANCE.getInstance();
            int finalI = i;
            singleton.submit(()->logger.info("执行任务id:[{}]", finalI));
        }

    }

    /**
     * akka框架
     * Akka是一个强大的工具包，提供了Actor模型的实现。
     */
    public static void akka() {
        logger.info("akka框架");
    }

    /**
     * actr框架
     * Actr是一个轻量级的Java Actor模型实现，旨在为开发者提供一个简单、高效且类型安全的并发编程框架
     */
    public static void actor() {
        logger.info("Java Actor框架");
    }
}
