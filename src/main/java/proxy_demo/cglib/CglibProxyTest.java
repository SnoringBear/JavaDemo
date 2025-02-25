package proxy_demo.cglib;
import net.sf.cglib.proxy.Enhancer;

/**
 * 测试类
 */
public class CglibProxyTest {
    // CGlib动态代理是一种强大的代码生成库，它能够在运行时动态地生成目标类的子类，从而实现对目标类的方法增强，即动态代理
    // Spring AOP面向切面编程的基础,方法调用前、后的一些额外处理，例如：调用日志
    public static void main(String[] args) {
        // 创建 Enhancer 对象
        Enhancer enhancer = new Enhancer();

        // 设置目标类
        enhancer.setSuperclass(TargetClass.class);

        // 设置拦截器
        enhancer.setCallback(new MyInterceptor());

        // 创建代理对象
        TargetClass proxy = (TargetClass) enhancer.create();

        // 调用代理对象的方法
        proxy.method1();
        proxy.method2();
    }
}