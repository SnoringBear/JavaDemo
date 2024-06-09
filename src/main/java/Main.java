import a.MathExpUtil;

public class Main {
    public static void main(String[] args) {
        String express = "2*(3+5*2)+7/1-4";
        System.out.println(MathExpUtil.eval(express));
    }
}
