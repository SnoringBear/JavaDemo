package string_demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class SplitDemo {
    /** 日志 */
    private static Logger logger = (Logger) LoggerFactory.getLogger(SplitDemo.class);

    public static void main(String[] args) {
        split1();
    }

    public static void split1() {
        String originalString = "苹果香蕉橙子";
        // 将字符串转换为字符列表
        List<Character> charList = new ArrayList<>();
        for (char c : originalString.toCharArray()) {
            charList.add(c);
        }

        // 打乱字符顺序
        Collections.shuffle(charList);

        // 将打乱后的字符列表转换回字符串
        StringBuilder shuffledString = new StringBuilder();
        for (char c : charList) {
            shuffledString.append(c);
        }

        // 打印打乱后的字符串
        logger.info("shuffledString String:{} ", shuffledString);
        logger.info("Original String: {}", originalString);
    }

    public static void split2() {
        String originalString = "苹果香蕉橙子2";
        // 将字符串转换为字符列表
        List<Character> charList = new ArrayList<>();
        for (char c : originalString.toCharArray()) {
            charList.add(c);
        }

        // 打乱字符顺序
        Collections.shuffle(charList);

        // 将打乱后的字符列表转换回字符串
        StringBuilder shuffledString = new StringBuilder();
        for (char c : charList) {
            shuffledString.append(c);
        }
        logger.info("Original String2:  {}", originalString);
        logger.info("shuffledString String2: {}", shuffledString);
    }
}
