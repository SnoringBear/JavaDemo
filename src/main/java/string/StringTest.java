package string;

public class StringTest {
    public static void main(String[] args) {
        Person person = new Person("张三", 18);
        // 对象可以与字符串相加, 但是会调用对象的toString方法
        String s = person + "hello";
        System.out.println(s);
    }
}
class Person{
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
