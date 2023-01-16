package exercise;

class Example {

    public static void main(String[] args) {

        // Boolean logic

        System.out.println(10 < 5); //  => false
        // System.out.println(true && false); // => false
        // System.out.println(true || false); // => true
        // System.out.println(!false); // true

        // Predicates

        int number1 = -10;
        boolean isPositiveNumber = number1 > 0;
        System.out.println(isPositiveNumber); // => false

        // String word = "Java";
        // boolean isJava = word.equals("Java");
        // System.out.println(isJava); // => true

        // Logical operations

        int number2 = 8;
        boolean isPositiveEven = number2 > 0 && number2 % 2 == 0;
        System.out.println(isPositiveEven); // => true

        // int year = 2020;
        // boolean isLeapYear = year % 400 == 0
        //     || (year % 4 == 0 && year % 100 != 0);
        // System.out.println(isLeap); // => true

        // if else

        int number3 = -7;
        if (number3 < 0) {
        System.out.println("Negative");
        } else if (number3 > 0) {
        System.out.println("Positive");
        } else {
        System.out.println("Zero");
        }
        // => "Negative"

        // Ternary operator

        int number4 = -10;
        int abs = number4 >= 0 ? number4 : -number4;
        System.out.println(abs); // => 10;

        // String message = number4 % 2 == 0 ? "even" : "odd";
        // System.out.println(message); // => "even";
    }
}
