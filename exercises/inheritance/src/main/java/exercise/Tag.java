package exercise;

import java.util.stream.Collectors;
import java.util.Map;

class Tag {

    private String name; // тег
    private Map<String, String> attributes; // атрибут1="значение1"

    Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public String stringifyAttributes() {
        return attributes.keySet().stream()
            .map(key -> {
                String value = attributes.get(key);
                return String.format(" %s=\"%s\"", key, value);
            })
            .collect(Collectors.joining(""));
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
