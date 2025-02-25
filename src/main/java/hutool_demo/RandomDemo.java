package hutool_demo;

import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.Logger;
import cn.hutool.core.util.RandomUtil;
import org.slf4j.LoggerFactory;

public class RandomDemo {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(RandomDemo.class);

    public static void main(String[] args) {
        random();
        random2();
    }

    /**
     * 随机数
     */
    public static void random() {
        int number = 100;
        for (int i = 0; i < 1000000000; i++) {
            int i1 = RandomUtil.randomInt(100);
            if (number <= i1) {
                logger.info("i1:{}", i1);
            }
        }
    }

    /**
     * 随机
     */
    public static void random2() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(RandomUtil.randomInt(100));
        }
        logger.info("list.get(RandomUtil.randomInt(list.size())) :{}", list.get(RandomUtil.randomInt(list.size())));
    }


}
