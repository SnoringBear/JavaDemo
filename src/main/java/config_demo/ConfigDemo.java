package config_demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ch.qos.logback.classic.Logger;
import cn.hutool.core.io.resource.ResourceUtil;
import org.slf4j.LoggerFactory;

public class ConfigDemo {
    /** 日志 */
    private static Logger logger = (Logger) LoggerFactory.getLogger(ConfigDemo.class);
    public static void main(String[] args) {
        properties2();
    }

    /**
     * 属性
     */
    public static void properties(){
        String version = ResourceUtil.readUtf8Str("build_version.properties");
        logger.info("build_version : {}", version);
    }

    public static void properties2(){
        Properties gitProperties = new Properties();
        String propertiesFilePath = "target/classes/git.properties";; // 替换为实际的文件路径

        try (InputStream input = new FileInputStream(propertiesFilePath)) {
            // 加载属性文件
            gitProperties.load(input);

            // 读取Git信息
            String commitId = gitProperties.getProperty("git.commit.id");
            String branch = gitProperties.getProperty("git.branch");
            String describe = gitProperties.getProperty("git.describe");
            // ... 可以读取其他属性

            // 打印Git信息
            logger.info("Git Commit ID: {}" , commitId);
            logger.info("Git Branch: {}" , branch);
            logger.info("Git Describe: {}" , describe);
            // ... 打印其他信息

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
