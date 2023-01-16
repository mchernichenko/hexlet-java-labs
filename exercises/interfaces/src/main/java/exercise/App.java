package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static List<String> buildAppartmentsList(List<Home> appartments, int top) {
        return appartments.stream()
                .sorted()
                .limit(top)
                .map(Home::toString)
                .collect(Collectors.toList());
    }
}
// END
