package lookup;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;

public class MethodHandlesDemo{
    public static void main(String[] args) throws Throwable {
        
        Lookup lookup = MethodHandles.lookup();
        Class<?> clazz = lookup.findClass(Student.class.getName());
        
        MethodType methodType = MethodType.methodType(String.class);
        Student s1 = new Student();
        s1.setName("Vetal");
        MethodHandle getNameMethod = lookup.findVirtual(clazz, "getName", methodType);
        System.out.println(getNameMethod.invoke(s1));

        MethodType consTypeDefault = MethodType.methodType(void.class);
        MethodHandle consMethodDefault = lookup.findConstructor(clazz, consTypeDefault);
        Student s2 = (Student)consMethodDefault.invoke();
        s2.setName("Igor");
        s2.setAge(25);
        System.out.println(s2.toString());

        MethodType consType = MethodType.methodType(void.class, String.class, int.class);
        MethodHandle consMethod = lookup.findConstructor(clazz, consType);
        Student s3 = (Student)consMethod.invoke("Velila", 35);
        System.out.println(s3.toString());

        MethodType mt = MethodType.methodType(void.class, String.class);
        MethodHandle setNameMethod = lookup.findVirtual(clazz, "setName", mt);
        setNameMethod.invoke(s3, "Velila Jr.");
        System.out.println(s3.toString());

        MethodType staticMethod = MethodType.methodType(void.class, int.class);
        MethodHandle setCountMethod = lookup.findStatic(clazz, "setCount", staticMethod);
        setCountMethod.invoke(10);
        System.out.println(Student.getCount());

        //will receive exceptions, because of the private access
        // MethodHandle getterName = lookup.findGetter(clazz, "name", String.class);
        // getterName.invoke(s3);
        // MethodHandle setterName = lookup.findSetter(clazz, "name", String.class);
        // setterName.invoke(s3, "Velila 2");

        //should create private lookup object to access private fields
        Lookup privateLookup = MethodHandles.privateLookupIn(clazz, lookup);
        MethodHandle setterName = privateLookup.findSetter(clazz, "name", String.class);
        setterName.invoke(s3, "Velila 2nd");
        MethodHandle getterName = privateLookup.findGetter(clazz, "name", String.class);
        System.out.println(getterName.invoke(s3));


        //VarHandles
        VarHandle ageVH = privateLookup.findVarHandle(clazz, "age", int.class);
        ageVH.set(s3, 30);
        System.out.println(ageVH.get(s3));


    }
}