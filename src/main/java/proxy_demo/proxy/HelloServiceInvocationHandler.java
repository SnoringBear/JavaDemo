package proxy_demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class HelloServiceInvocationHandler implements InvocationHandler {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(HelloServiceInvocationHandler.class);
    private Object target;

    public HelloServiceInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在调用目标方法之前添加逻辑
        logger.info("Before method call: {}" , method.getName());

        // 调用目标方法
        Object result = method.invoke(target, args);

        // 在调用目标方法之后添加逻辑
        logger.info("After method call: {}", method.getName());

        return result;
    }
}
