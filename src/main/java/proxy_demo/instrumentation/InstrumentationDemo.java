package proxy_demo.instrumentation;

import ch.qos.logback.classic.Logger;
import cn.hutool.core.io.FileUtil;
import net.bytebuddy.agent.ByteBuddyAgent;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;

/**
 * Java Agent Java探针技术
 * Java 中的 Instrumentation API 是一个强大的工具，它允许开发者在 Java 应用程序运行时修改其行为。
 * 这个 API 通常用于监控、分析、诊断和代理（Agent）开发中。
 * 通过 Instrumentation，可以在不修改原始代码的情况下，动态地向应用程序添加功能或修改其行为
 * 主要用途
 *      性能监控：可以监控应用程序的性能，包括 CPU 使用率、内存使用情况等。
 *      故障排查：在运行时诊断问题，如内存泄漏、线程死锁等。
 *      动态修改：在不重启应用程序的情况下，动态地修改类和方法的行为。
 *      代理（Agent）开发：开发 Java Agent，这些 Agent 可以在 JVM 启动时或运行时附加到 JVM 上，执行特定的任务。
 */
public class InstrumentationDemo {
    /** 日志 */
    private static final Logger logger = (Logger) LoggerFactory.getLogger(InstrumentationDemo.class);
    public static void main(String[] args) throws Exception {
        redefineClasses();
    }

    /**k
     * 可以通过Instrumentation.redefineClasses()方法重新定义已加载的类，但有一定的限制条件，
     * 如新类和老类的父类必须相同、实现的接口数也要相同等
     */
    public static void redefineClasses() throws Exception{
        ReaderDemo readerDemo1 = new ReaderDemo();
        logger.info("加载ReadDemo前执行结果:");
        readerDemo1.test();
        // String currentWorkingDir = "/home/xg/文档/java-demo/src/main/java/proxy_demo/instrumentation/ReaderDemo.java";
        String currentWorkingDir =  System.getProperty("user.dir")+"/src/main/hot/ReaderDemo.class";
        File targetDir = new File(currentWorkingDir);
        byte[] bytes = FileUtil.readBytes(targetDir);
        Instrumentation instrumentation = ByteBuddyAgent.install();
        Class<?> readerDemo = Class.forName("proxy_demo.instrumentation.ReaderDemo");
        // 加载的是class文件,而不是.java文件
        instrumentation.redefineClasses(new ClassDefinition(readerDemo, bytes));
        logger.info("加载ReadDemo后执行结果:");
        readerDemo1.test();
    }

    /**
     * 可以通过Instrumentation.retransformClasses()方法重新触发Transformer的拦截，
     * 修改已加载类的字节码。
     */
    public static void retransformClasses(){

    }

}

