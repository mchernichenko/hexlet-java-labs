package exercise;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {
    private static String execCommand() throws Exception {
        String command = "build/install/building/bin/building"; // ! NB: Linux! На WIN тест упадёт
        // String command = "build\\install\\building\\bin\\building.bat"; // ! NB: For Win
        Process process = Runtime.getRuntime().exec(command);
        String output = IOUtils.toString(process.getInputStream());
        process.waitFor();
        return output;
    }

    @Test
    void testOutput() throws Exception {
        String output = execCommand().trim();
        assertThat(output).isEqualTo("Hello, World!");
    }

    // BEGIN
    @Test
    void testToGson() {
        String[] strings = {"apple", "pear", "lemon"};
        String expected = "[\"apple\",\"pear\",\"lemon\"]";
        String actual = App.toJson(strings);
        assertThat(actual).isEqualTo(expected);
    }
    // END
}
