package java8_demo;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class FunctionalInterfaceTest {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(FunctionalInterfaceTest.class);
    public static void main(String[] args) {
        // 只有一个抽象方法的接口被称为函数式接口,默认方法不算，无论是否加上@FunctionalInterface,都是函数式接口
        test1(()-> logger.info("我正在读书"));
    }

    public static void test1(Book book){
        book.read();
    }
}

@FunctionalInterface
interface Book {
    void read();
}
