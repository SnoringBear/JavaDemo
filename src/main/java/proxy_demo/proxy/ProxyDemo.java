package proxy_demo.proxy;

import java.lang.reflect.Proxy;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class ProxyDemo {
    private static final Logger log = (Logger) LoggerFactory.getLogger(ProxyDemo.class);

    public static void main(String[] args) {
        test2();
    }

    public static void test1() {
        // 创建原始对象
        HelloService helloService = new HelloServiceImpl();

        // 创建 InvocationHandler 对象
        HelloServiceInvocationHandler handler = new HelloServiceInvocationHandler(helloService);

        // 创建代理对象
        HelloService proxyInstance = (HelloService) Proxy.newProxyInstance(
                helloService.getClass().getClassLoader(),
                helloService.getClass().getInterfaces(),
                handler
        );

        // 使用代理对象调用方法
        proxyInstance.sayHello("World");
    }

    public static void test2() {
        // 创建 InvocationHandler 对象
        HelloServiceInvocationHandler2 handler = new HelloServiceInvocationHandler2();

        // 创建代理对象
        HelloService proxyInstance = (HelloService) Proxy.newProxyInstance(
                HelloService.class.getClassLoader(),
                new Class[] { HelloService.class },
                handler
        );

        // 使用代理对象调用方法
        Object world = proxyInstance.sayHello("World");
        log.info("test2执行结果:[{}]", world);
    }
}
