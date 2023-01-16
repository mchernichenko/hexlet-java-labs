package exercise;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
// Описание парного тега: <тег атрибут1="значение1" атрибут2="значение2">Тело тега<SingleTag1><SingleTag2>..</тег>
// Например:  "<div class="y5">Тело тега<br id="s"><hr class="a5"></div>"

public class PairedTag extends Tag {

    private final String bodyTag;
    private final List<Tag> tagList; // вложенные теги, представляемые в формате SingleTag

    public PairedTag(String name, Map<String, String> attributes, String bodyTag, List<Tag> tagList) {
        super(name, attributes);
        this.bodyTag = bodyTag;
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return String.format("<%s%s>%s%s</%s>", //  Exp: "<div class="y5">Тело тега<br id="s"><hr class="a5"></div>"
                super.getName(),
                super.stringifyAttributes(),
                bodyTag,
                stringifyAttributes(),
                super.getName());
    }

    @Override
    public String stringifyAttributes() {
        return tagList.stream()
                .map(item -> {
                    return String.format("<%s%s>", item.getName(), item.stringifyAttributes());
                })
                .collect(Collectors.joining(""));
    }
}
// END
