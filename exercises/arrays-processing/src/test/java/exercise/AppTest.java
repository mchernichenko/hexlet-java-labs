package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AppTest {
    @Test
    void testGetElements1() throws Exception {
        int[] numbers = {};
        int[] result = App.getElementsLessAverage(numbers);
        assertThat(result).isEmpty();
    }

    @Test
    void testGetElements2() throws Exception {
        int[] numbers = {0, 1, 2, 3, 4, 5, 10, 12};
        int[] result = App.getElementsLessAverage(numbers);
        int[] expected = {0, 1, 2, 3, 4};
        assertThat(result).containsExactly(expected);
    }
}
