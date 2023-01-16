package exercise;

import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
public class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        if (image.length == 0) {
            return image;
        }
        return Arrays.stream(image)
                .flatMap(x -> Stream.of(x, x))
                .map(x -> Arrays.stream(x)
                        .flatMap(y -> Stream.of(y, y)))
                .map(x -> x.toArray(String[]::new))
                .toArray(String[][]::new);
    }
}
// END