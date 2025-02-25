package path_demo;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

import ch.qos.logback.classic.Logger;
import cn.hutool.core.compiler.JavaFileObjectUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.URLUtil;
import org.slf4j.LoggerFactory;

public class ClassPathDemo {
    private static Logger logger = (Logger) LoggerFactory.getLogger(ClassPathDemo.class);
    public static void main(String[] args) {
        testGetURLs();
        testGetJavaClassPaths();
    }

    private static void testGetURLs(){
        Set<String> filePaths = new HashSet<>();
        ClassLoader classLoader = ClassUtil.getClassLoader();
        URL[] urLs = ((URLClassLoader) classLoader).getURLs();
        logger.info("开始打印url值");
        for (URL url : urLs) {
            logger.info(url.toString());
            File file = new File(URLUtil.decode(url.getFile()));
            filePaths.add(file.getPath());
            if (file.isFile()) {
                continue;
            }
            FileUtil.walkFiles(file, subFile -> {
                if (JavaFileObjectUtil.isJarOrZipFile(subFile.getName())) {
                    filePaths.add(file.getPath());
                }
            });
        }
        logger.info("结束打印url值");
    }

    private static void testGetJavaClassPaths(){
        String[] javaClassPaths = ClassUtil.getJavaClassPaths();
        for (String javaClassPath : javaClassPaths) {
            logger.info("javaClassPath:{}", javaClassPath);
        }
    }
}
