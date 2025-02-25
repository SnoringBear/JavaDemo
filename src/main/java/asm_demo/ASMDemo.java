package asm_demo;

import org.objectweb.asm.util.ASMifier;

import java.io.IOException;

public class ASMDemo {
    public static void main(String[] args) throws IOException {
        ASMifier.main(new String[] { ASMDemo.class.getName() });
    }
}
