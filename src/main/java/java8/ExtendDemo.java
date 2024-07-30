package java8;

public class ExtendDemo {
    public static void main(String[] args) {
        A a = new B();
        System.out.println(a.toString());
    }
}

class A {
    public int a;
}
class B extends A {
    public int b;
    public B() {
        a = 1;
        b = 2;
    }

    @Override
    public String toString() {
        return "B{" +
                "b=" + b +
                ", a=" + a +
                '}';
    }
}
