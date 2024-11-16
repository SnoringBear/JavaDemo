package jvm;

import java.nio.charset.Charset;

public class JVMCode {
    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        String str = System.getProperty("file.encoding");
        System.out.println("Default Charset: " + str);

        Charset defaultCharset = Charset.defaultCharset();
        System.out.println("Default Charset: " + defaultCharset);
    }
}
