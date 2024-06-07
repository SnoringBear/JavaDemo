public class Main2 {
    public static void main(String[] args) {
        String expression = "1002*3003";
        expression = expression.replaceAll("\\s", "");
        expression = appendSymbol(expression);
        T t = splitExpression(expression);
        System.out.println(t.toString());
    }

    public static T splitExpression(String expression) {
        T t = new T();
        int mark = 0;
        StringBuilder token = new StringBuilder();
        if (expression.charAt(0) == Bracket.SQUARE_BRACKETS_BEGIN.getSymbol()) {
            int begin = 0;
            int end = 0;
            for (int i = 0; i < expression.length(); i++) {
                char currentChar = expression.charAt(i);
                token.append(currentChar);
                if (currentChar == Bracket.SQUARE_BRACKETS_BEGIN.getSymbol()) {
                    begin++;
                }
                if (currentChar == Bracket.SQUARE_BRACKETS_END.getSymbol()) {
                    end++;
                }
                if (begin != 0 && begin == end) {
                    mark = i;
                    break;
                }
            }
            mark++;
        } else if (expression.charAt(0) == Bracket.ROUND_BRACKETS_BEGIN.getSymbol()) {
            int begin = 0;
            int end = 0;
            for (int i = 0; i < expression.length(); i++) {
                char currentChar = expression.charAt(i);
                token.append(currentChar);
                if (currentChar == Bracket.ROUND_BRACKETS_BEGIN.getSymbol()) {
                    begin++;
                }
                if (currentChar == Bracket.ROUND_BRACKETS_END.getSymbol()) {
                    end++;
                }
                if (begin != 0 && begin == end) {
                    mark = i;
                    break;
                }
            }
            mark++;
        } else {
            for (int i = 0; i < expression.length(); i++) {
                char currentChar = expression.charAt(i);
                if (!isOperator(currentChar)) {
                    token.append(currentChar);
                    continue;
                }
                mark = i;
                break;
            }
        }

        t.symbol = expression.charAt(mark);
        t.a = token.toString();
        t.b = expression.substring(mark + 1, expression.length());

        // 解析 t.a
        if (!NotOperatorOrBracket(t.a)) {
            t.a = removeSymbol(t.a);
            t.at = splitExpression(t.a);
            t.a = null;
        }
        // 解析 t.b
        if (!NotOperatorOrBracket(t.b)) {
            t.b = removeSymbol(t.b);
            t.bt = splitExpression(t.b);
            t.b = null;
        }
        return t;
    }

    private static boolean isOperator(char c) {
        return c == ArithmeticSymbol.ADD.getSymbol() || c == ArithmeticSymbol.SUB.getSymbol() || c == ArithmeticSymbol.MUL.getSymbol() || c == ArithmeticSymbol.DIV.getSymbol();
    }

    private static boolean isBracket(char c) {
        return c == Bracket.ROUND_BRACKETS_BEGIN.getSymbol() || c == Bracket.ROUND_BRACKETS_END.getSymbol() || c == Bracket.SQUARE_BRACKETS_BEGIN.getSymbol() || c == Bracket.SQUARE_BRACKETS_END.getSymbol();
    }

    public static boolean NotOperatorOrBracket(String str) {
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if (isOperator(currentChar) || isBracket(currentChar)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNum(char c) {
        return Character.isDigit(c);
    }

    public static String appendSymbol(String s) {
        if (isNum(s.charAt(0))) {
            return "$" + s;
        }
        return s;
    }

    public static String removeSymbol(String s) {
        if (s.charAt(0) == Bracket.SQUARE_BRACKETS_BEGIN.getSymbol()) {
            return s.substring(1, s.length() - 2);
        }
        if (s.charAt(0) == Bracket.ROUND_BRACKETS_BEGIN.getSymbol()) {
            return s.substring(1, s.length() - 2);
        }
        return s;
    }
}

class T {
    String a;
    String b;
    T at;
    T bt;
    char symbol;

    @Override
    public String toString() {
        return "T{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", at=" + at +
                ", bt=" + bt +
                ", symbol=" + symbol +
                '}';
    }
}

enum ArithmeticSymbol {
    ADD('+'), SUB('-'), MUL('*'), DIV('/'), MOD('%');
    private final char symbol;

    ArithmeticSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}

enum Bracket {
    ROUND_BRACKETS_BEGIN('('), ROUND_BRACKETS_END(')'), SQUARE_BRACKETS_BEGIN('['), SQUARE_BRACKETS_END(']');
    private final char symbol;

    Bracket(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}