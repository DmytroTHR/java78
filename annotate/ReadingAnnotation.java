package annotate;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReadingAnnotation {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Utility.class;
        Constructor<?> conz = clazz.getConstructor();
        Utility util = (Utility) conz.newInstance();

        Method[] methods = clazz.getDeclaredMethods();
        for (Method mtd : methods) {
            if (!mtd.isAnnotationPresent(MostUsed.class)) {
                continue;
            }
            MostUsed mtdAnno = mtd.getAnnotation(MostUsed.class);
            String valAnno = mtdAnno.value();
            mtd.invoke(util, valAnno);
        }
    }
}