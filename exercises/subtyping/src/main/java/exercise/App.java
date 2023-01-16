package exercise;

import java.util.Map;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> stringMap = storage.toMap();
        for (Map.Entry<String, String> key : stringMap.entrySet()) {
            storage.unset(key.getKey());
            storage.set(key.getValue(), key.getKey());
        }
    }
}
// END
