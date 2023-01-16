package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// BEGIN
public class App {

    // вариант с List<Character>
    public static boolean scrabble(String arrSymbol, String word) {
        if (word.trim().equals("")) {
            return true;
        }

        List<Character> characterList = new ArrayList<>();
        char[] chars = arrSymbol.toLowerCase().toCharArray();
        for (char ch : chars) {
            characterList.add(ch);
        }

        chars = word.toLowerCase().toCharArray();
        for (char ch : chars) {
            if (!characterList.remove(Character.valueOf(ch))) {
                return false;
            }
        }
        return true;
    }

    // вариант с List<String> покороче будет
    public static boolean scrabble1(String arrSymbol, String word)  {
        if (word.trim().equals("")) {
            return true;
        }
        List<String> stringList = new ArrayList<>();
        Collections.addAll(stringList, arrSymbol.trim().toLowerCase().split(""));
        String[] strings = word.trim().toLowerCase().split("");
        for (String s : strings) {
            if (!stringList.remove(s)) {
                return false;
            }
        }
        return true;
    }
}
//END
