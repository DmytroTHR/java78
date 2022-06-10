package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Vector;

class MyClass {
    private int somePrivateNum;

    private MyClass() {
        System.out.println("MyClass constructor");
        somePrivateNum = 1;
    }

    public int getSomePrivateNum() {
        somePrivateMethod("a");
        return somePrivateNum;
    }

    private String somePrivateMethod(String s) {
        return "Given access to " + s;
    }
}

public class ReflectionDemo {
    public static void main(String[] args) throws Exception {
        // MyClass obj = new MyClass(); //unaccessible
        Class<?> cls = Class.forName("reflection.MyClass");
        Constructor<?> con = cls.getDeclaredConstructor();
        con.setAccessible(true);
        MyClass obj = (MyClass) con.newInstance();

        Field fld = cls.getDeclaredField("somePrivateNum");
        fld.setAccessible(true);
        fld.set(obj, 111);
        System.out.println("obj.getSomePrivateNum() = " + obj.getSomePrivateNum());

        Method met = cls.getDeclaredMethod("somePrivateMethod", String.class);
        met.setAccessible(true);
        String res = (String) met.invoke(obj, "ROOT account");
        System.out.println(res);

        // forName
        Class<?> cls1 = Class.forName("java.lang.String");
        Class<?> cls2 = Class.forName("java.lang.String");
        System.out.println(cls1 == cls2);

        // ClassName.class
        Class<?> cls3 = String.class;
        System.out.println(cls3);

        // object.getClass()
        Vector<Boolean> tst = new Vector<>();
        Class<? extends Vector> cls4 = tst.getClass();
        System.out.println(cls4);
        Class<?> cls5 = cls4.getSuperclass();
        System.out.println(cls5);

        System.out.println(">>>>>>> Interfaces of: " + cls4.getName());
        Class<?>[] interf = cls4.getInterfaces();
        for (Class<?> c : interf) {
            System.out.println(c.getName());
        }

        System.out.println(">>>>>> Constructors of: " + cls4.getName());
        // Constructor<?>[] cons = cls4.getDeclaredConstructors(); //all
        Constructor<?>[] cons = cls4.getConstructors(); // public & public from superclass
        for (Constructor<?> c : cons) {
            System.out.print(Class.forName(c.getName()).getSimpleName() + " (");
            for (Parameter p : c.getParameters()) {
                System.out.print(p.getType().getSimpleName() + " " + p.getName() + ", ");
            }
            System.out.println(")");
        }

        System.out.println(">>>>>> Methods of: " + cls4.getName());
        for (Method m : cls4.getDeclaredMethods()) {
            int modifiers = m.getModifiers();
            System.out.print(Modifier.toString(modifiers) + " ");

            System.out.print(m.getReturnType().getSimpleName());
            System.out.print("\t" + m.getName() + "(");
            for (Parameter param : m.getParameters()) {
                System.out.print(param.getType().getSimpleName() + " " + param.getName() + ", ");
            }
            System.out.println(")");
        }

        System.out.println(">>>>>> Fields of: " + cls4.getName());
        for (Field f : cls4.getDeclaredFields()) {
            System.out.println(Modifier.toString(f.getModifiers()) + " " + f.getType().getName() + " " + f.getName());
        }

    }
}