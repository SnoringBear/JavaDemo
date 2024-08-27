package List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args) {
        test3();
    }

    public static void test1() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.subList(list.size() - 2, list.size()).stream().forEach(System.out::println);
        System.out.println("原始数据 = " + list);
    }

    public static void test2() {
        List<List<Long>> groups = new ArrayList<>(8);
        System.out.println("groups = " + groups);
    }

    public static void test3() {
        List<Long> roles = new ArrayList<>();
        for (int i = 0; i < 511; i++) {
            roles.add((long) i);
        }
        int n = 50;
        int p = 10;
        int m = roles.size();
        // 若M<（N+P），将该段位所有人分为一组
        if (m < (n + p)) {
            // 分一组
            return;
        }

        // 根据战力排序
        // 按照公司分为3段
        List<List<Long>> result = new ArrayList<>();
        int n1 = 3000 * m / 10000;
        int n3 = 1000 * m / 10000;
        int n2 = m - n1 - n3;
        // 上段
        result.add(roles.subList(0, n1));
        // 中段
        result.add(roles.subList(n1, n1 + n2));
        // 下段
        result.add(roles.subList(n1 + n2, m));


        // 下面即为 M >= (P + N)
        if (m % n >= p) {
            // M/N的余数>= P，则分组数量 = int(M/N)+1，前int(M/N)组每组N个人，最后一组为剩余的人数
            int groupNum = (m / n) + 1;
            // 每个组人数
            List<Integer> groupSize = groupSize(groupNum, n, m);
            List<List<Long>> groups = initList(groupNum);
            for (List<Long> list : result) {
                int index = -1;
                for (Long l : list) {
                    // 分配到分组
                    index = getNextIndex(index,groupNum,groups,groupSize);
                    if (groups.get(index).size() < groupSize.get(index)) {
                        groups.get(index).add(l);
                    }
                }
            }
            for (List<Long> group : groups) {
                System.out.println(group.size());
            }

            return;
        }
        // 若M/N的余数 < P,则分组数量 = int(M/N)，前（int(M/N)-1）组每组N个人，最后一组为剩余的人数
        int groupNum = m / n;
        List<Integer> groupSize = groupSize(groupNum, n, m);
        List<List<Long>> groups = initList(groupNum);
        for (List<Long> list : result) {
            // 每段进行乱序处理
            int index = -1;
            for (Long l : list) {
                // 分配到分组
                index = getNextIndex(index,groupNum,groups,groupSize);
                if (groups.get(index).size() < groupSize.get(index)) {
                    groups.get(index).add(l);
                }
            }
        }
        for (List<Long> group : groups) {
            System.out.println(group.size());
        }
    }

    private static List<List<Long>> initList(int size) {
        List<List<Long>> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new ArrayList<>());
        }
        return list;
    }

    private static List<Integer> groupSize(int groupNum, int size, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= groupNum; i++) {
            if (i == groupNum) {
                list.add(m - size * (groupNum - 1));
                continue;
            }
            list.add(size);
        }
        return list;
    }

    private static int getNextIndex(int index, int groupNum, List<List<Long>> groups, List<Integer> groupSize) {
        index = index + 1 >= groupNum ? 0 : index + 1;
        // 如果当前分组已满，则跳到下一个分组
        if (groups.get(index).size() >= groupSize.get(index)) {
            return getNextIndex(index, groupNum, groups, groupSize);
        }
        return index;
    }
}


