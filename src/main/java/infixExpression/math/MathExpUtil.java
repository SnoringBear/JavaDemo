package infixExpression.math;


import java.math.BigDecimal;
import java.util.*;


/**
 * 数学表达式工具
 */
public class MathExpUtil {

    /**
     * 计算表达式值
     */
    public static BigDecimal eval(String express) {
        Map<Integer, MathExp> baseMathExps = genPostfixExp(express);

        Map<Integer, MathExpInner> mathExps = new LinkedHashMap<>();
        for (Map.Entry<Integer, MathExp> entry : baseMathExps.entrySet()) {
            mathExps.put(entry.getKey(), new MathExpInner(entry.getValue()));
        }

        Map<Integer, BigDecimal> mathResults = new LinkedHashMap<>();
        BigDecimal result = null;
        for (MathExpInner exp : mathExps.values()) {
            exp.eval(mathExps, mathResults);
            result = mathResults.get(exp.getIndex());
        }

        return result;
    }

    /**
     * 中缀表达式转后缀表达式
     *
     * @param express 中缀表达式,需要提前验证表达式中字符是否正确
     * @return 后缀表达式
     */
    public static LinkedHashMap<Integer, MathExp> genPostfixExp(String express) {
//        if (StrUtil.isBlank(express)) {
//            throw new RuntimeException("表达式为空");
//        }
        if (express == null || express.isEmpty()) {
            throw new RuntimeException("表达式为空");
        }

        // 分解字符串,移除空白符,替换中括号、大括号,处理负号,验证括号数量
        List<Character> chars = new ArrayList<>(express.length());
        express = express.replaceAll("\\s+", "");// 移除空格
        int leftBracketCount = 0;// 左括号数量
        int rightBracketCount = 0;// 右括号数量
        Stack<Character> temp = new Stack<>();
        for (int i = 0; i < express.toCharArray().length; i++) {
            char ch = express.charAt(i);
            // 替换中括号和大括号
            if (ch == '[' || ch == '{') {
                ch = '(';
            } else if (ch == ']' || ch == '}') {
                ch = ')';
            }


            if (getOperatorPriority(ch) == 0) {
                // 操作数
                chars.add(ch);
            } else if (ch == '(') {
                // 当前字符是左括号，压入栈
                temp.push(ch);
            } else if (ch == ')') {
                // 当前字符是右括号，弹出栈顶操作符直到遇到左括号
                while (!temp.isEmpty() && temp.peek() != '(') {
                    chars.add(temp.pop());
                }
                temp.pop();
            } else {
                // 当前字符是操作符，弹出栈顶操作符直到遇到比当前操作符优先级高的操作符,'C'例外
                while (!temp.isEmpty() && temp.peek() != '(' && getOperatorPriority(temp.peek()) >= getOperatorPriority(ch)) {
                    chars.add(temp.pop());
                }
                temp.push(ch);
            }

            // 计算括号数量
            if (ch == '(') {
                leftBracketCount++;
            } else if (ch == ')') {
                rightBracketCount++;
            }

        }
        while (!temp.isEmpty()) {
            chars.add(temp.pop());
        }
        if (leftBracketCount != rightBracketCount) {
            throw new RuntimeException("表达式为空");
        }

        LinkedHashMap<Integer, MathExp> result = new LinkedHashMap<>();// 结果
        Stack<MathExp> operandStack = new Stack<>();// 操作数栈
        int expNum = 0;
        for (char ch : chars) {
            int priority = getOperatorPriority(ch);// 读取运算符优先级

            // 优先级为0表示操作数
            if (priority == 0) {
                // 处理操作数
                MathExp operand = new MathExp(expNum++, String.valueOf(ch));
                result.put(operand.getIndex(), operand);
                operandStack.push(operand);
            } else {
                MathExp operand2 = operandStack.pop();
                MathExp operand1 = operandStack.pop();
                MathExp value = new MathExp(expNum++, operand1.getIndex(), operand2.getIndex(), ch);
                operandStack.push(value);
                result.put(value.getIndex(), value);
            }
        }
        return result;
    }

    /**
     * 获取运算符优先级
     */
    private static Integer getOperatorPriority(char ch) {
        switch (ch) {
            case '+':
            case '-': {
                return 1;
            }
            case '*':
            case '/': {
                return 2;
            }
            case '(':
            case ')':
            case '[':
            case ']': {
                return 3;
            }
            default: {
                return 0;
            }
        }
    }
}
