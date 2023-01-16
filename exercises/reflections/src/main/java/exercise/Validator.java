package exercise;

import java.lang.reflect.Field;
import java.util.*;

// BEGIN
public class Validator {

    private static final String ERR_MINLENGTH_ANNOTATION = "length less than %s";
    private static final String ERR_NOTNULL_ANNOTATION = "can not be null";

    public static List<String> validate(Address address) {
        List<String> list = new ArrayList<>();
        for (Field field : address.getClass().getDeclaredFields()) {
            NotNull annotation = field.getAnnotation(NotNull.class);
            if (annotation != null) {
                field.setAccessible(true);
                try {
                    if (field.get(address) == null) {
                        list.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> notValidFields = new HashMap<>();
        Map<String, String> mapNullAttr = Validator.validate(address).stream().collect(HashMap::new, (x, y) -> {
            x.put(y, ERR_NOTNULL_ANNOTATION);
        }, HashMap::putAll);
        Map<String, String> mapMinLengthAttr = Validator.minLengthAnnotationValidator(address);
        addToMap(notValidFields, mapNullAttr);
        addToMap(notValidFields, mapMinLengthAttr);
        return notValidFields;
    }

    private static Map<String, String> minLengthAnnotationValidator(Address address) {
        Map<String, String> mapValidate = new HashMap<>();

        for (Field field : address.getClass().getDeclaredFields()) {
            MinLength annotation = field.getAnnotation(MinLength.class);
            if (annotation != null) {
                field.setAccessible(true);
                try {
                    if (field.get(address) == null ||
                            String.valueOf(field.get(address)).length() < annotation.minLength()) {
                        mapValidate.put(field.getName(),
                                String.format(ERR_MINLENGTH_ANNOTATION, annotation.minLength()));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return mapValidate;
    }

    // Добавить map в targetMap
    private static void addToMap(Map<String, List<String>> targetMap, Map<String, String> map) {
        for (Map.Entry<String, String> item : map.entrySet()) {
            if (targetMap.containsKey(item.getKey())) {
                targetMap.get(item.getKey()).add(item.getValue());
            } else {
                targetMap.put(item.getKey(), new ArrayList<>(List.of(item.getValue())));
            }
        }
    }
}
// END
