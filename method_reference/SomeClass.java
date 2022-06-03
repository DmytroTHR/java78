package method_reference;

public class SomeClass {
    private int someParam;
    private String someString;

    public SomeClass(int param, String str) {
        this.someParam = param;
        this.someString = str;
    }

    public int getSomeParam() {
        return someParam;
    }

    public String getSomeString() {
        return someString;
    }

    @Override
    public String toString() {
        return "SomeClass{" +
                "someParam=" + someParam +
                ", someString='" + someString + '\'' +
                '}';
    }
}
