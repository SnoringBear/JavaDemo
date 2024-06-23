package infixExpression.math;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 数学表达式操作内部BigDecimal执行
 */

class MathExpInner extends MathExpBase {
    /** 操作数参数 */
    private BigDecimal p1;

    /**
     * 数学表达式操作内部BigDecimal执行
     */
    public MathExpInner(MathExp exp) {
        copyFrom(exp);
        if (exp.getP1() != null) {
            p1 = new BigDecimal(exp.getP1());
        }
    }

    /**
     * 执行表达式
     */
    public void eval(Map<Integer, MathExpInner> mathExps, Map<Integer, BigDecimal> mathResults) {
        if (o == null) {
            return;
        }
        BigDecimal value;
        switch (o) {
            case '+': {
                BigDecimal v1 = v(pe1, mathExps, mathResults);
                BigDecimal v2 = v(pe2, mathExps, mathResults);
                value = v1.add(v2);
                break;
            }
            case '-': {
                BigDecimal v1 = v(pe1, mathExps, mathResults);
                BigDecimal v2 = v(pe2, mathExps, mathResults);
                value = v1.subtract(v2);
                break;
            }
            case '*': {
                BigDecimal v1 = v(pe1, mathExps, mathResults);
                BigDecimal v2 = v(pe2, mathExps, mathResults);
                value = v1.multiply(v2);
                break;
            }
            case '/': {
                BigDecimal v1 = v(pe1, mathExps, mathResults);
                BigDecimal v2 = v(pe2, mathExps, mathResults);
                value = v1.divide(v2);
                break;
            }
            default: {
                throw new RuntimeException("不支持的运算符:" + o);
            }
        }
        mathResults.put(index, value);
    }

    /**
     * 获取表达式操作的值
     */
    protected static BigDecimal v(int peIndex, Map<Integer, MathExpInner> mathExps, Map<Integer, BigDecimal> mathResults) {
        MathExpInner exp = mathExps.get(peIndex);
        if (exp.o == null) {
            return exp.p1;
        } else {
            return mathResults.get(exp.index);
        }
    }
}
