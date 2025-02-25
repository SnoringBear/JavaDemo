package thread_demo.async;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

import ch.qos.logback.classic.Logger;
import cn.hutool.core.thread.ThreadUtil;
import org.slf4j.LoggerFactory;

public class AsyncDemo {
    /** 日志 */
    private static final Logger logger = (Logger) LoggerFactory.getLogger(AsyncDemo.class);

    public static void main(String[] args) {
        test1();
    }

    /**
     * 公司任务:家族乱斗,游戏服战斗逻辑优化测试
     */
    public static void test1() {
        // Java异步方案可以为Future interface 以及其子类
        List<Future<Boolean>> futures = new ArrayList<>();
        // 异步提交代码
        for (int i = 0; i < 10; i++) {
            Future<Boolean> future = ThreadUtil.execAsync(() -> {
                // 异步执行的代码
                logger.info("异步任务执行中...");
                try {
                    Thread.sleep(2000);
                    int n = new Random().nextInt(2);
                    int m = 1 / n;
                    logger.info("m的值:[{}]", m);
                } catch (Exception e) {
                    return false;
                }
                return true;
            });
            futures.add(future);
            logger.info("提交完任务,任务编号[{}]", i);
        }
        // 遍历等待任务
        for (Future<Boolean> future : futures) {
            // 等待异步任务完成
            try {
                Boolean b = future.get();
                logger.info("任务执行结果:[{}],", b);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        logger.info("异步任务完成，主线程继续执行...");
    }
}
