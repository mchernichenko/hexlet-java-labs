package exercise;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AppTest {
    @Test
    void testGetWordsCount() {
        String sentence1 = "word text dog apple word apple word";
        HashMap actual1 = App.getWordCount(sentence1);
        HashMap expected1 = new HashMap();
        expected1.put("word", 3);
        expected1.put("apple", 2);
        expected1.put("text", 1);
        expected1.put("dog", 1);
        assertThat(actual1).containsExactlyInAnyOrderEntriesOf(expected1);

        String sentence2 = "";
        HashMap actual2 = App.getWordCount(sentence2);
        assertThat(actual2).isEmpty();

    }

    @Test
    void testToString() {
        String sentence1 = "word text cat apple word map apple word";
        HashMap wordCount1 = App.getWordCount(sentence1);
        String actual1 = App.toString(wordCount1);
        String expected1 = "{\n  apple: 2\n  cat: 1\n  text: 1\n  word: 3\n  map: 1\n}";
        assertThat(actual1.trim()).isEqualTo(expected1);

        String sentence2 = "word text cat apple word apple word";
        HashMap wordCount2 = App.getWordCount(sentence2);
        String actual2 = App.toString(wordCount2);
        String expected2 = "{\n  apple: 2\n  cat: 1\n  text: 1\n  word: 3\n}";
        assertThat(actual2.trim()).isEqualTo(expected2);

        String sentence3 = "";
        HashMap wordCount3 = App.getWordCount(sentence3);
        String actual3 = App.toString(wordCount3);
        assertThat(actual3.trim()).isEqualTo("{}");
    }
}
