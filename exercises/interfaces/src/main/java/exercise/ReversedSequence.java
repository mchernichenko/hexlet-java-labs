package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// BEGIN
public class ReversedSequence implements CharSequence {
    private String str;
    public ReversedSequence(String str) {
        this.str = str;
    }

    @Override
    public int length() {
        return str.length();
    }

    @Override
    public char charAt(int index) {
        return str.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return str.substring(start, end);
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, str.split(""));
        Collections.reverse(list);
        return String.join("", list);
    }
}
// END
