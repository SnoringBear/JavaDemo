package a;

import lombok.Getter;

/**
 * 数学表达式操作
 */
@Getter
public class MathExp extends MathExpBase {
    /** 字符串参数 */
    private String p1;

    /**
     * 数学表达式操作
     */
    public MathExp(int index, String p1) {
        this.index = index;
        this.p1 = p1;
    }

    /**
     * 数学表达式操作
     */
    public MathExp(int index, Integer p1, Integer p2, char o) {
        super(index, p1, p2, o);
    }
}
