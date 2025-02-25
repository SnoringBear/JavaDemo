package annotation_demo;

import java.lang.annotation.*;

/**
 * GM命令注解
 *
 * <pre>
 * 注解的方法参数可任意多个，第一个参数必须是Player p
 * 如果要使用数组则只能在最后一个参数使用，注解的方法参数可用类型参考
 * {@link cn.hutool.core.convert.ConverterRegistry#defaultConverter()}
 * 命令不允许重复，方法必须为public。
 * 示例public void method(Player p, int param1, String  param2, double param3)
 * </pre>
 */
@Documented
@Target(ElementType.METHOD) // 注解作用在方法上
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时有效
public @interface GM {
    /** 输入的命令，默认值为方法名，大小写不敏感，统一转为小写。 */
    String cmd() default "";

    /** GM说明 */
    String desc() default "";

    /** 测试环境有效 */
    boolean test() default false;
}
