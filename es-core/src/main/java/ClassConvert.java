import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ClassConvert<T, V> {
    public static <T, V> T convert(Class<T> target, V raw) {
        Field[] rawFields = raw.getClass().getDeclaredFields();
        Field[] targetFields = target.getDeclaredFields();

        try {
            Constructor<T> targetCons = target.getConstructor();
            T newInstance = targetCons.newInstance();
            for (Field r : rawFields) {
                for (Field t : targetFields) {
                    if (t.getName().equals(r.getName()) && t.getType() == r.getType()) {
                        t.setAccessible(true);
                        t.set(newInstance, raw.getClass().getMethod(getGetter(r.getName(), String.valueOf(r.getType()))).invoke(raw));
                        t.setAccessible(false);
                    }
                }
            }

            return newInstance;

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            System.out.println("Error: " + e);
        }

        return null;
    }

    private static String getGetter(String field, String type) {
        return type.equals("boolean")
                ? field
                : "get" + field.replace(field.charAt(0), (char) (field.charAt(0) - 32));
    }
}