package string_demo;

import ch.qos.logback.classic.Logger;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.LoggerFactory;

public class StringTest {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(StringTest.class);
    public static void main(String[] args) {
        test1();
        test2(new String("test2"));
        test3(new String("test3"));
        test4(new String("test4"));
    }

    public static void test1() {
        Person person = new Person("张三", 18);
        // 对象可以与字符串相加, 但是会调用对象的toString方法
        String s = person + "hello";
        logger.info("test1 result:{}",s);
    }

    public static void test2(String b) {
        String s = "test2";
        boolean equals = s.equals(b);
        logger.info("test2 equals result:{}",equals);
    }

    public static void test3(String b) {
        String s = "test3";
        boolean equals = s.equalsIgnoreCase(b);
        logger.info("test3 equals result:{}",equals);
    }


    public static void test4(String b) {
        String s = "test4";
        boolean equals = s==b;
        logger.info("test4 equals result:{}",equals);
    }
}



@Setter
@Getter
class Person{
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
