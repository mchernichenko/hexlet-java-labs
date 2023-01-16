package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private final String path; // путь до базы, которая хранит инфу в файле

    public FileKV(String path, Map<String, String> stringMap) {
        this.path = path;
        Utils.writeFile(path, Utils.serialize(stringMap));
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> stringMap = Utils.unserialize(Utils.readFile(this.path));
        stringMap.put(key, value);
        Utils.writeFile(path, Utils.serialize(stringMap));
    }

    @Override
    public void unset(String key) {
        Map<String, String> stringMap = Utils.unserialize(Utils.readFile(this.path));
        stringMap.remove(key);
        Utils.writeFile(path, Utils.serialize(stringMap));
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> stringMap = Utils.unserialize(Utils.readFile(this.path));
        String result = stringMap.get(key);
        return result == null ? defaultValue : result;
    }

    @Override
    public Map<String, String> toMap() {
        return Utils.unserialize(Utils.readFile(this.path));
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(".").toRealPath();
        System.out.println(path.toString());
    }
}
// END
