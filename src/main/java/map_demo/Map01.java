package map_demo;

import java.util.concurrent.ConcurrentHashMap;

public class Map01 {
    public static void main(String[] args) {
        ConcurrentHashMap<String, A> map = new ConcurrentHashMap<>();
        map.put("1", new A(1));
        map.put("2", new A(2));
        A a = map.get("1");
        if(a.getA() == 1){
            a.setA(4);
        }
    }
}

class A{
    private int a;
    public A(int a){
        this.a = a;
    }
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
