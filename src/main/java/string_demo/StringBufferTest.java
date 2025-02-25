package string_demo;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class StringBufferTest {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(StringBufferTest.class);

    public static void main(String[] args) {
        // StringBuffer示例
        StringBuffer sb = new StringBuffer("Hello");
        StringBuffer sb1 = sb.append(" World"); // 在原有对象上修改，没有创建新对象
        logger.info("sb1: {}", sb1);// 输出：Hello World，注意sb和sb1实际上是同一个对象
    }
}
