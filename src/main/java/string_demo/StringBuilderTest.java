package string_demo;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class StringBuilderTest {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(StringBuilderTest.class);
    public static void main(String[] args) {
        // StringBuilder示例
        StringBuilder sbBuilder = new StringBuilder("Hello");
        StringBuilder sbBuilder1 = sbBuilder.append(" World"); // 在原有对象上修改，没有创建新对象
        logger.info("sbBuilder1: {}", sbBuilder1); // 输出：Hello World，注意sbBuilder和sbBuilder1实际上是同一个对象
    }
}



