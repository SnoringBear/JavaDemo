package a;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 数学表达式操作基础字段
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MathExpBase {
    /** 此表达式MAP下标 */
    protected int index;
    /** 表达式参数1,上层MAP下标 */
    protected Integer pe1;
    /** 表达式参数2，上层MAP下标 */
    protected Integer pe2;
    /** 运算符 */
    protected Character o;

    /**
     * 从指定对象复制
     */
    protected void copyFrom(MathExpBase obj) {
        index = obj.index;
        pe1 = obj.pe1;
        pe2 = obj.pe2;
        o = obj.o;
    }

}
