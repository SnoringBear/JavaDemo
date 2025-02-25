package java8_demo;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class ExtendDemo {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ExtendDemo.class);
    public static void main(String[] args) {
        A a = new B();
        logger.info("a:{}",a);
    }
}

class A {
    public int a;
}
class B extends A {
    public int b;
    public B() {
        a = 1;
        b = 2;
    }

    @Override
    public String toString() {
        return "B{" +
                "b=" + b +
                ", a=" + a +
                '}';
    }
}
