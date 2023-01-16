package exercise;

class App {
    // BEGIN
    public static final String NO_TRIANGLE = "Треугольник не существует";
    public static final String VERSATILE = "Разносторонний";
    public static final String EQUILATARAL = "Равносторонний";
    public static final String ISOSCELES = "Равнобедренный";

    public static String getTypeOfTriangle(int a, int b, int c) {
        String result;
        boolean isTriangle = a + b > c && b + c > a && a + c > b;

        if (!isTriangle) {
            result = NO_TRIANGLE;
        } else if (a != b && b != c && c != a) {
            result = VERSATILE;
        } else if (a == b && b == c) {
            result = EQUILATARAL;
        } else {
            result = ISOSCELES;
        }
        return result;
    }
    // END
}
