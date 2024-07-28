package number;

public class RangeTest {
    public static void main(String[] args) {
        System.out.println(test(880));
        System.out.println(test(1100));
        System.out.println(test(1111));
        System.out.println(test(1200));
        System.out.println(test(1201));
    }

    public static int test(int serverId) {
        int[] intA1 = {1100, 1200};
        if (serverId <= intA1[0]) {
            return 1;
        }
        if (serverId > intA1[intA1.length - 1]) {
            return intA1.length + 1;
        }
        for (int i = 1; i < intA1.length; i++) {
            if (intA1[i - 1] < serverId && serverId <= intA1[i]) {
                return i + 1;
            }
        }
        return 1;
    }
}
