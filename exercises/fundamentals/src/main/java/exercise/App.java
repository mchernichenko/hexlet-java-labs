package exercise;

class App {

    @SuppressWarnings("checkstyle:MagicNumber")
    public static void numbers() {
        // BEGIN
        int i = 8 / 2;
        int j = 100 % 3;
        System.out.println(i + j);
        // END
    }

    public static void strings() {
        String language = "Java";
        // BEGIN
        System.out.println(language + " works on JVM");
        // END
    }

    public static void converting() {
        Number soldiersCount = 300;
        String name = "spartans";
        // BEGIN
        System.out.println(soldiersCount + " " + name);
        // END
    }
}
