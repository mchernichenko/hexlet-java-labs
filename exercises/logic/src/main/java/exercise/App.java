package exercise;

class App {

    public static boolean isBigOdd(int number) {
        // BEGIN
        boolean result = number % 2 == 1 && number >= 1001;
        return result;
        // END
    }

    public static void sayEvenOrNot(int number) {
        // BEGIN
        String result = (number % 2 == 0) ? "yes" : "no";
        System.out.println(result);
        // END
    }

    public static void printPartOfHour(int minutes) {
        // BEGIN
        switch (minutes / 15) {
            case 0:
                System.out.println("First");
                break;
            case 1:
                System.out.println("Second");
                break;
            case 2:
                System.out.println("Third");
                break;
            case 3:
                System.out.println("Fourth");
                break;
            default:
                System.out.println("Count of minutes out of range (0-59)");
                break;
        }
        // END
    }
}
