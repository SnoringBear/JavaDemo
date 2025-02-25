package proxy_demo.cglib;

import ch.qos.logback.classic.Logger;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

// 拦截器类
public class MyInterceptor implements MethodInterceptor {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(MyInterceptor.class);
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        logger.info("在方法执行前添加的逻辑");
        Object result = proxy.invokeSuper(obj, args);
        logger.info("在方法执行后添加的逻辑");
        return result;
    }
}
