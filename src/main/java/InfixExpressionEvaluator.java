import java.util.Stack;

public class InfixExpressionEvaluator {

    public static void main(String[] args) {
        String expression = "3 + 5 * (2 - 8)";
        try {
            double result = evaluate(expression);
            System.out.println("The result of the expression is: " + result);
        } catch (Exception e) {
            System.out.println("Error evaluating expression: " + e.getMessage());
        }
    }

    public static double evaluate(String expression) throws Exception {
        return parseExpression(expression.replaceAll(" ", ""));
    }

    private static double parseExpression(String expression) throws Exception {
        // 存储操作数的栈
        Stack<Double> values = new Stack<>();
        // 存储操作符的栈
        Stack<Character> operators = new Stack<>();
        // 表达式字符串的索引
        int i = 0;

        // 遍历表达式中的每个字符
        while (i < expression.length()) {
            char c = expression.charAt(i);

            // 如果字符是数字，提取连续的数字和小数点
            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i++));
                }
                // 将数字字符串转换为双精度浮点数并压入操作数栈
                values.push(Double.parseDouble(sb.toString()));
                // 回退索引以处理当前字符之后的可能操作符
                i--;
            } else if (c == '(') {
                // 遇到左括号，压入操作符栈
                operators.push(c);
            } else if (c == ')') {
                // 遇到右括号，弹出操作符栈中的操作符，并应用到操作数栈中的操作数，直到遇到左括号
                while (operators.peek() != '(') {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                // 弹出左括号，不需计算
                operators.pop();
            } else if (isOperator(c)) {
                // 如果字符是操作符，与栈顶的操作符比较优先级，并适当应用操作符
                while (!operators.isEmpty() && hasPrecedence(c, operators.peek())) {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                // 将当前操作符压入栈
                operators.push(c);
            }
            // 移动到表达式的下一个字符
            i++;
        }

        // 处理剩余的操作符，直到栈为空
        while (!operators.isEmpty()) {
            values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
        }

        // 返回计算结果
        return values.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    private static double applyOperation(char op, double b, double a) throws Exception {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new Exception("Cannot divide by zero");
                }
                return a / b;
            default:
                throw new Exception("Unsupported operator: " + op);
        }
    }
}
