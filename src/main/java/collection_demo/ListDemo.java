package collection_demo;

import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class ListDemo {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ListDemo.class);
    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        List<Integer> list = new ArrayList<>(5);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        logger.info("list:{}",list);
    }

    public static void test2(){

    }
}
