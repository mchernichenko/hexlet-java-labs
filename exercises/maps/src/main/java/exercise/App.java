package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static HashMap<String, Integer> getWordCount(String strSentence) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        String inStr = strSentence.trim();
        if (inStr.equals("")) {
            return hashMap;
        }
        String[] arr = inStr.split(" ");
        for (String s : arr) {
            if (hashMap.containsKey(s)) {
                hashMap.put(s, hashMap.get(s) + 1);
            } else {
                hashMap.put(s, 1);
            }
        }
        return hashMap;
    }
    public static String toString(HashMap<String, Integer> hashMap) {
        if (hashMap.size() == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{\n");
        for (Map.Entry<String, Integer> key : hashMap.entrySet()) {
            sb.append("  ")
              .append(key.getKey())
              .append(": ")
              .append(key.getValue())
              .append("\n");
        }
        return sb.append("}").toString();
    }
    public static void main(String[] args) {
        String sentence = "java is the best programming language java";
        HashMap<String, Integer> wordsCount = App.getWordCount(sentence);
        System.out.println(wordsCount);
    }
}
//END
