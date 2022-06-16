package lookup;

public class Student {
    private String name;
    private int age;
    private static int count = 0;

    public Student(){
        count++;
        this.name = "";
        this.age = 0;
    }

    public Student(String name, int age){
        count++;
        this.name = name;
        this.age = age;
    }

    public static void setCount(int cnt){
        Student.count = cnt;
    }

    public static int getCount(){
        return Student.count;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public String toString(){
        return "Name: " + this.name + " Age: " + this.age;
    }
}
