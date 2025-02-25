package clone_demo;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ComboBattleData implements Serializable {
    /**
     * 日志
     */
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ComboBattleData.class);
    /**
     * 通过难度
     */
    private int passLevel;
    /**
     * 达到难度
     */
    private int reachLevel;
    /**
     * 等待完美的关卡
     */
    private final Set<Integer> waitPerfectLevels = new HashSet<>();
    /**
     * 已提示过同伴品质
     */
    private int companionQualityTip;

    /**
     * 克隆
     */
    public ComboBattleData copyThis() {
        ComboBattleData other = new ComboBattleData();
        other.passLevel = passLevel;
        other.reachLevel = reachLevel;
        other.waitPerfectLevels.addAll(waitPerfectLevels);
        other.companionQualityTip = companionQualityTip;
        return other;
    }

    public Object copy() {
        try {
            // 写入对象到字节流
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            oos.flush();

            // 从字节流中读取对象
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            logger.error("字节流输入输出异常:{}", e.getMessage());
            return null;
        }
    }
}
