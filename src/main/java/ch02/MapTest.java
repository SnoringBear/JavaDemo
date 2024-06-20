package ch02;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<Integer, Foo> map = new HashMap<>();
        Foo foo = map.computeIfAbsent(1, (k) -> {
            Foo f = new Foo(1);
            return f;
        });
        System.out.println(map.get(1));
    }
}

class Foo{
    private int i;
    public Foo(int i){
        this.i = i;
    }
    public int getI(){
        return i;
    }
    public void setI(int i){
        this.i = i;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "i=" + i +
                '}';
    }
}
