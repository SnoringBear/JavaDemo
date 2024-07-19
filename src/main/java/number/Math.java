package number;

public class Math {
    public static void main(String[] args) {
        // 1150 1050
        // 原积分 + K * (S - 1 / ( 1 + 10 ^ (( 对方积分 - 自身积分) / 400 )))
        // long s = 1000+5*(0-1/(1+10^(1300-1000/400)));
        long s = 1000+5*(0-1/(1+10^(1300-1000/400)));

        System.out.println(s);
    }
}
