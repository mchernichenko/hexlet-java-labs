package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {
    private static final String PREF = "X_FORWARDED_";
    private static final String ENV = "environment";
    public static String getForwardedVariables(String conf) {
        if (conf == null || conf.length() == 0) {
            return conf;
        }
        return Arrays.stream(conf.split("\n"))
                .filter(x -> x.startsWith(ENV))
                .flatMap(x -> Arrays.stream(x.split("\"")))
                .filter(x -> x.contains(PREF))
                .flatMap(x -> Arrays.stream(x.split(",")))
                .filter(x -> x.contains(PREF))
                .map(x -> x.replace(PREF, ""))
                .collect(Collectors.joining(","));
    }
}
//END
