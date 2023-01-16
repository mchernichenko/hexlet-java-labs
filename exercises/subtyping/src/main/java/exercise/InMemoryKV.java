package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class InMemoryKV implements KeyValueStorage {

    private final Map<String, String> stringMap = new HashMap<>(); // база хранит инфу в мапе

    public InMemoryKV(Map<String, String> stringMap) {
        this.stringMap.putAll(stringMap);
    }

    @Override
    public void set(String key, String value) {
        this.stringMap.put(key, value);
    }

    @Override
    public void unset(String key) {
        this.stringMap.remove(key);

    }

    @Override
    public String get(String key, String defaultValue) {
        String result = this.stringMap.get(key);
        return result == null ? defaultValue : result;
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(this.stringMap);
    }
}
// END
