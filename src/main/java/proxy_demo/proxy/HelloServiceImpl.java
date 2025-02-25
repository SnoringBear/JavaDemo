package proxy_demo.proxy;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class HelloServiceImpl implements HelloService {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(HelloServiceImpl.class);
    @Override
    public Object sayHello(String name) {
        logger.info("hello,{}",name);
        return null;
    }
}
