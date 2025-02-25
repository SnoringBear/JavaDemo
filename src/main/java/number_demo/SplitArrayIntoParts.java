package number_demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class SplitArrayIntoParts {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(SplitArrayIntoParts.class);

    public static void main(String[] args) {
        test1();
    }

    public void test2() {
        long[] data = {1, 2}; // 示例数组
        int parts = 3; // 分成三段

        List<List<Long>> result = splitArrayIntoParts(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L, 21L, 22L, 23L, 24L, 25L, 26L, 27L, 28L, 29L, 30L, 31L, 32L, 33L, 34L), parts);

        // 输出结果
        for (int i = 0; i < result.size(); i++) {
            logger.info("Part {}:{}",i+1,result.get(i));
        }
    }

    public static List<List<Long>> splitArrayIntoParts(List<Long> data, int parts) {
        List<List<Long>> result = new ArrayList<>();
        // 计算每个部分应该有多少个元素
        int base = data.size() / parts;
        if (base == 0) {
            result.add(data);
            return result;
        }

        for (int i = 0; i < parts; i++) {
            if (i == parts - 1) {
                result.add(data.subList(i * base, data.size()));
                break;
            }
            List<Long> part = data.subList(i * base, (i + 1) * base);
            result.add(part);
        }
        return result;
    }

    public static void test1() {
        List<Long> roles = new ArrayList<>();
        roles.add(1L);
        roles.add(2L);
        roles.add(3L);

        List<List<Long>> groups = initList(4);


        // 平分为上、中、下3段
        List<List<Long>> lists = splitArrayIntoParts(roles, 3);
        int index = -1;
        for (List<Long> list : lists) {
            // 每段进行乱序处理
            for (Long l : list) {
                // 分配到分组
                index = index + 1 >= 4 ? 0 : index + 1;
                groups.get(index).add(l);
            }
        }
        logger.info("groups :{}",groups);
    }

    private static List<List<Long>> initList(int size) {
        List<List<Long>> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new ArrayList<>());
        }
        return list;
    }
}