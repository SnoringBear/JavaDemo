package collection_demo;

import java.util.ArrayList;

import ch.qos.logback.classic.Logger;
import cn.hutool.core.collection.CollUtil;
import org.slf4j.LoggerFactory;

public class CollUtilDemo {
    private static Logger logger = (Logger) LoggerFactory.getLogger(CollUtilDemo.class);
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1(){
        ArrayList<Integer> arrayList = CollUtil.newArrayList(1, 2, 3, 4, 5);
        logger.info("arrayList:{}", arrayList);
    }

    public static void test2(){
        ArrayList<Integer> list = CollUtil.toList(1, 2, 3, 4, 5);
        logger.info("list:{}", list);
    }
}
