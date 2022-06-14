
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Objects;

import annotation.Autowired;
import annotation.Component;
import annotation.ComponentScan;
import annotation.Configuration;

public class ApplicationContext {
    private static HashMap<Class<?>, Object> map = new HashMap<>();

    public ApplicationContext(Class<AppConfig> clss) {
        Spring.initializeSpringContext(clss);
    }

    private static class Spring {
        private static void initializeSpringContext(Class<?> clss) {
            if (!clss.isAnnotationPresent(Configuration.class)) {
                throw new RuntimeException("The file is not a Configuration file!");
            } else {
                ComponentScan annotation = clss.getAnnotation(ComponentScan.class);
                String value = annotation.value();

                String packageStructure = "project/bin/" + value.replace(".", "/");
                File[] files = findClasses(new File(packageStructure));

                for (File file : files) {
                    String name = value + (value.length() == 0 ? "" : ".") + file.getName().replace(".class", "");
                    try {
                        Class<?> loadingClass = Class.forName(name);
                        if (loadingClass.isAnnotationPresent(Component.class)) {
                            Constructor<?> constructor = loadingClass.getConstructor();
                            Object object = constructor.newInstance();
                            map.put(loadingClass, object);
                        }
                    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                            | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }

        }

        private static File[] findClasses(File file) {
            if (!file.exists()) {
                throw new RuntimeException("Package not found: " + file);
            } else {
                File[] listFiles = file.listFiles(e -> e.getName().endsWith(".class"));
                return listFiles;
            }

        }
    }

    public <T> T getBean(Class<T> clss) {
        T obj = (T) Objects.requireNonNull(map.get(clss), "Bean not found in the map!");

        Field[] declaredFields = clss.getDeclaredFields();
        injectBean(obj, declaredFields);

        return obj;
    }

    private <T> void injectBean(T object, Field[] declaredFields) {
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                Object innerObject = map.get(type);
                try {
                    field.set(object, innerObject);
                    Field[] declaredFieldsInner = type.getDeclaredFields();
                    injectBean(innerObject, declaredFieldsInner);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
