package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AppTest {
    @Test
    void testGetAbbreviation1() throws Exception {
        String phrase = "Portable Network Graphics";
        String result = App.getAbbreviation(phrase);
        assertThat(result).isEqualTo("PNG");
    }

    @Test
    void testGetAbbreviation2() throws Exception {
        String phrase = "Ruby on Rails";
        String result = App.getAbbreviation(phrase);
        assertThat(result).isEqualTo("ROR");
    }

    @Test
    void testGetAbbreviation3() throws Exception {
        String phrase = "First In, First Out";
        String result = App.getAbbreviation(phrase);
        assertThat(result).isEqualTo("FIFO");
    }

    @Test
    void testGetAbbreviation4() throws Exception {
        String phrase = "Complementary metal oxide semiconductor";
        String result = App.getAbbreviation(phrase);
        assertThat(result).isEqualTo("CMOS");
    }
}
