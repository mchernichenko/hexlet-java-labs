package exercise;

class Triangle {
    // BEGIN
    public static double getSquare(int a, int b, double angle) {
        return a * b * Math.sin(Math.PI / 180 * angle) / 2;
    }

    public static void main() {
        System.out.println(getSquare(4, 5, 45));
    }
    // END
}
