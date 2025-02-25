package infixExpression;

import ch.qos.logback.classic.Logger;
import infixExpression.math.MathExpUtil;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        String express = "2*(3+5*2)+7/1-4";
        logger.info("MathExpUtil.eval(express):{}",MathExpUtil.eval(express));
    }
}
