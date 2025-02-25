package java8_demo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class LambdaTest {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(LambdaTest.class);
    public static void main(String[] args) {
        Object o = test2(LambdaTest::test3, 1);
        logger.info("o:{}",o);
    }



    public void test1(){
        List<String> names = Arrays.asList("张三", "李四", "王五");
        // jdk定义的四种函数式接口
        // Consumer->void accept(T t)
        // Function->R apply(T t)
        // Predicate->boolean test(T t)
        // Supplier -> T get()
        names.forEach(System.out::println);
    }

    public static String test2(Function<Integer, String> function, Integer t){
        return function.apply(t);
    }
    public static String test3(int i){
        return "hello"+i;
    }
}
