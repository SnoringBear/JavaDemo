package number;

public class IntTest {
    public static void main(String[] args) {
       test2();

    }

    public static void test1() {
        int dayMaxDrop = 10;
        int dropAllNum = 50;
        int v = (int) ((double) dayMaxDrop / dropAllNum * 100);
        System.out.println(v);
    }

    public static void test2() {
        long dayMaxDrop = -0;
        System.out.println(dayMaxDrop);
    }
}
