package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class App {
    public static List<Integer> take(List<Integer> elements, int count) {
        String implementation = System.getenv("IMPLEMENTATION") != null
            ? System.getenv("IMPLEMENTATION")
            : "right";

        switch (implementation) {
            case "wrong1":
                return Implementations.wrong1(elements, count);
            case "wrong2":
                return Implementations.wrong2(elements, count);
            case "wrong3":
                return Implementations.wrong3(elements, count);
            default:
                return Implementations.right(elements, count);
        }
    }

    public static void main(String[] args) {
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        System.out.println(App.take(numbers1, 2)); // => [1, 2]

        List<Integer> numbers2 = new ArrayList<>(Arrays.asList(7, 3, 10));
        System.out.println(App.take(numbers2, 8)); // => [7, 3, 10]
    }
}
