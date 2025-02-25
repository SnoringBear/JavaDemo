package clone_demo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class CloneDemo {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(CloneDemo.class);
    public static void main(String[] args) {
        ComboBattleData data = new ComboBattleData();
        int num = 10000000;
        testClone1(data,num);
        testClone2(data,num);
        // 结果:
        // 18:17:14.088 [main] INFO clone_demo.CloneDemo -- testClone1 时间间隔（秒）: 5    通过手动创建对象来实现克隆
        // 18:18:06.716 [main] INFO clone_demo.CloneDemo -- testClone2 时间间隔（秒）: 52   使用序列化、反序列化实现克隆
    }

    public  static void testClone1( ComboBattleData data,int num){
        LocalDateTime startTime = LocalDateTime.now();
        List<ComboBattleData> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            ComboBattleData aThis = data.copyThis();
            list.add(aThis);
        }
        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);

        logger.info("testClone1 时间间隔（秒）: {}" , duration.getSeconds());
    }

    public  static void testClone2( ComboBattleData data,int num){
        LocalDateTime startTime = LocalDateTime.now();
        List<ComboBattleData> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Object copy = data.copy();
            list.add((ComboBattleData) copy);
        }
        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);

        logger.info("testClone2 时间间隔（秒）: {}" , duration.getSeconds());
    }
}
