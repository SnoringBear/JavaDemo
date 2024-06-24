package java8;

import java.util.Arrays;
import java.util.List;

public class LambdaTest {
    public static void main(String[] args) {

    }

    public void test1(){
        List<String> names = Arrays.asList("张三", "李四", "王五");
        // jdk定义的四种函数式接口
        // Consumer->void accept(T t)
        // Function->R apply(T t)
        // Predicate->boolean test(T t)
        // Supplier -> T get()
        names.forEach(name -> System.out.println(name));
    }
}
