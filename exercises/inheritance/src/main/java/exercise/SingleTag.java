package exercise;

import java.util.Map;

// BEGIN
// Описание простого тега: <тег атрибут1="значение1" атрибут2="значение2">
public class SingleTag extends Tag {

    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    @Override
    public String toString() {
        return String.format("<%s%s>", // Exp: <img class="v-10" id="wop">
                super.getName(),
                super.stringifyAttributes());
    }
}
// END
