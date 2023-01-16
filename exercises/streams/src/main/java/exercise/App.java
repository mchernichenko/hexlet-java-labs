package exercise;

import java.util.List;

// BEGIN
public class App {
    public static final List<String> FREE_DOMAINS = List.of("gmail.com", "yandex.ru", "hotmail.com");

    public static long getCountOfFreeEmails(List<String> list) {
        return list.stream()
                .filter(App::isFreeDomain)
                .count();
    }

    public static boolean isFreeDomain(String email) {
        for (String freeDomain : FREE_DOMAINS) {
            if (email.endsWith(freeDomain)) {
                return true;
            }
        }
        return false;
    }
}
// END
