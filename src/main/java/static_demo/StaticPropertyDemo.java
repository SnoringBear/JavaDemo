package static_demo;

import ch.qos.logback.classic.Logger;
import cn.hutool.http.HttpGlobalConfig;
import org.slf4j.LoggerFactory;

public class StaticPropertyDemo {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(StaticPropertyDemo.class);
    public static void main(String[] args) {
        HttpGlobalConfig.setTimeout(5000);
        logger.info("HttpGlobalConfig.getTimeout() = {}" , HttpGlobalConfig.getTimeout());
    }

    /**
     * 静态属性（Static Variables）特点
     * 属于类而非实例：
     * 静态属性属于类本身，而不是类的实例。这意味着静态属性在类加载时就已经存在，而不是在创建实例时才存在。
     * 共享性：
     * 所有类的实例共享同一个静态属性。对静态属性的修改会影响到所有使用该属性的实例。
     * 内存分配：
     * 静态属性存储在方法区（或永久代）中，而不是堆内存中。这使得静态属性在程序运行期间一直存在，直到JVM退出。
     * 初始化时机：
     * 静态属性在类加载时进行初始化。如果静态属性被赋予了初值，那么该初值将在类加载时生效。
     * 访问权限：
     * 静态属性可以通过类名直接访问，也可以通过实例名访问（但通常不推荐这样做，因为这可能会引发混淆）。然而，静态属性不能通过this关键字访问。
     * 不能用于描述对象状态：
     * 静态属性通常用于描述与类相关的全局状态或配置信息，而不是用于描述对象的状态
     */
}
