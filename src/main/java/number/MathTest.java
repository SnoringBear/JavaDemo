package number;

public class MathTest {
    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        // 1020 1005
        // 原积分 + K * (S - 1 / ( 1 + 10 ^ (( 对方积分 - 自身积分) / 400 )))
        // long s = 1000+5*(0-1/(1+10^(1300-1000/400)));
        // long s = 1955+10*(1-1/(1+10^(1000-1955/400)));


        long s = (long) (1000+5*(1-1/(1+Math.pow(10,(1000-1000)/400))));
        // long s = 1005+5*(1-1/(1+10^);
        System.out.println(s);
    }

    public static void test2(){
        double s = Math.pow(10,(1005-1020)/400);
        System.out.println(s);
    }
}
