package static_demo;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class StaticFuncDemo {
    private static final Logger log = (Logger) LoggerFactory.getLogger(StaticFuncDemo.class);
    public static void main(String[] args) {

    }

    /**
     *  静态方法（Static Methods）特点
     * 无需实例化对象：
     * 静态方法可以直接通过类名调用，而不需要创建类的实例。这意味着静态方法属于类本身，而不是类的实例。
     * 生命周期最长：
     * 静态方法随着类的加载而加载，生命周期最长。它在类定义时就被装载到内存中，并且只有在JVM退出时才会被回收。
     * 优先于对象存在：
     * 静态方法和静态变量一样，优先于对象存在。类文件创建时，静态方法已经被加载，而对象的创建则是后续的事情。
     * 共享性：
     * 静态方法被所有对象所共享，这意味着无论创建了多少个对象，都只有一份静态方法的拷贝。
     * 访问限制：
     * 在静态方法中不能使用this关键字，因为this指向的是当前对象的引用，而静态方法不依赖于任何对象。
     * 访问权限：
     * 静态方法只能访问类的静态成员变量和其他静态方法，不能访问非静态成员变量和非静态成员方法。
     * 常用于工具类：
     * 由于静态方法不依赖于对象实例，常常用于工具类中，如数学计算、日期处理等功能。
     * 适合作为入口方法：
     * 静态方法也常用于定义程序的入口点，如main方法，因为它不需要任何对象来调用。
     * 不能重写但可隐藏：
     * 静态方法可以被继承，但不能被重写（Override）。如果子类定义了与父类同名的静态方法，这被视为隐藏（Hide），而不是重写。
     * 不具有多态性：
     * 静态方法不支持运行时动态绑定，因此不具有多态性
     */
}
