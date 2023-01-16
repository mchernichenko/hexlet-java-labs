package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static LinkedHashMap<String, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> intersectKeys = data1.keySet().stream().filter(data2::containsKey).collect(Collectors.toSet());
        Map<String, String> addMap = data2.keySet().stream()
                .filter(x -> !intersectKeys.contains(x))
                .collect(Collectors.toMap(x -> x, y -> "added"));

        Map<String, String> delMap = data1.keySet().stream()
                .filter(x -> !intersectKeys.contains(x))
                .collect(Collectors.toMap(x -> x, y -> "deleted"));

        Map<String, String> resultMap = new TreeMap<>(addMap);
        resultMap.putAll(delMap);

        for (String key : intersectKeys) {
            if (data1.get(key).equals(data2.get(key))) {
                resultMap.put(key, "unchanged");
            } else {
                resultMap.put(key, "changed");
            }
        }
        return new LinkedHashMap<>(resultMap);
    }
}
//END
