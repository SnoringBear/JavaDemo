package thread_demo.threadpool;

import java.util.concurrent.*;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class ThreadPoolDemo {
    /** 日志 */
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ThreadPoolDemo.class);

    static {
        // 类似于golang init方法
        logger.info("ThreadPoolDemo init");
    }

    public static void main(String[] args) {
        fixedThreadPool();
        cachedThreadPool();
        scheduledThreadPool();
        scheduledThreadPool2();
        singleThreadExecutor();
        customizeThreadPool();
        monitorThreadPool();
        newWorkStealingPool();
    }

    /**
     * 固定大小的线程池，可以缓存已创建的线程，适用于负载较大但任务执行时间较短的场景
     */
    public static void fixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 提交任务到线程池
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> logger.info(" [{}] is executing fixed task", Thread.currentThread().getName()));
        }
        executorService.shutdown();
    }

    /**
     * 根据需要创建新线程，如果空闲线程超过一定时间（默认为60秒）将会被终止并移除缓存，适用于执行大量短期异步任务。
     */
    public static void cachedThreadPool() {
        // 最大线程数为Integer.MAX_VALUE
        // 由于newCachedThreadPool允许创建大量的线程，并且使用无界任务队列，因此在处理大量任务时可能会导致内存溢出
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> logger.info(" [{}] is executing cached task", Thread.currentThread().getName()));
        }

    }

    /**
     * 支持定时或周期性任务执行的线程池。
     */
    public static void scheduledThreadPool() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        // 第一个参数：任务,第二个参数：延迟多少时间执行,第三个参数:单位
        scheduledExecutorService.schedule(() -> logger.info("[{}] is executing scheduled task", Thread.currentThread().getName()), 5, TimeUnit.SECONDS);
    }

    /**
     * 支持定时或周期性任务执行的线程池。
     */
    public static void scheduledThreadPool2() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleAtFixedRate(() -> logger.info("[{}] is executing scheduled222 task", Thread.currentThread().getName()), 5, 5, TimeUnit.SECONDS);
    }

    /**
     * 单线程执行器，用单个线程顺序执行任务，保证任务按顺序执行。
     */
    public static void singleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> logger.info(" [{}] is executing single thread task", Thread.currentThread().getName()));
        // XX class extend Thread,定义一个队列，还需要维护队列的代码
        // 可以在class里面定一个变量为Executors.newSingleThreadExecutor()来提交任务
        // 确定就是队列容量差不多无限大,容易内存溢出,还是自定义的线程池靠谱一点
        // 内存泄漏是行为,内存溢出是结果
    }

    /**
     * 自定义线程池
     */
    public static void customizeThreadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5, // 核心线程数
                10, // 最大线程数
                60L, TimeUnit.SECONDS, // 线程空闲时间
                new LinkedBlockingQueue<>(1000) // 任务队列
        );
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> logger.info(" [{}] is executing customized task", Thread.currentThread().getName()));
        }
    }

    /**
     * 监视线程池
     * 可以通过扩展 ThreadPoolExecutor 类或者实现 RejectedExecutionHandler 接口来监控线程池的状态和处理被拒绝的任务。
     */
    public static void monitorThreadPool() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        executor.setRejectedExecutionHandler((r, e) -> logger.info("Task:[{}], rejected from:[{}] ", r.toString(), e.toString()));
        // 当任务短时间内暴增的时候，保护措施,与微服务中的流量降级类似
    }

    /**
     * newWorkStealingPool，即任务窃取线程池，是Java 8中添加的一种基于ForkJoinPool实现的线程池。它采用了“工作窃取”（Work-Stealing）算法，
     * 特别适用于可并行化且计算密集型的任务，能够充分利用多核CPU资源，提高任务执行效率。
     * 与之前的线程池（多个线程共有一个阻塞队列）不同，newWorkStealingPool中每个线程都有一个自己的任务队列（双端队列Deque）
     * 当线程执行完自己队列中的任务后，它会尝试从其他线程的队列中“窃取”任务来执行，从而实现负载均衡。这种算法能够减少线程间的竞争，提高系统的整体性能
     */
    public static void newWorkStealingPool() {
        ExecutorService executorService = Executors.newWorkStealingPool(3);

        for (int i = 1; i <= 10; i++) {
            executorService.submit(new MyWorker(i));
        }

        // 注意：这里使用了一个无限循环来保持主线程不退出，以便观察线程池的执行情况。
        // 在实际应用中，应该使用更合适的同步机制或等待所有任务完成后再退出。
        while (true) {
            // Do nothing, just keep the main thread running.
        }
        // 大任务的拆分与聚合   当前计算机编程难点，单核上限遇到瓶颈，多核心、异构将成为新的方向
    }
}

