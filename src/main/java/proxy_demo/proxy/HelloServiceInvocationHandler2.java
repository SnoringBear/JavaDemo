package proxy_demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class HelloServiceInvocationHandler2 implements InvocationHandler {
    private static final Logger log = (Logger) LoggerFactory.getLogger(HelloServiceInvocationHandler2.class);
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("HelloServiceInvocationHandler2 invoked,method=[{}],args=[{}]", method, args);
        log.info("通过动态代理,可以在这里做统一的处理");
        return new String("统一处理成功");
    }
}
