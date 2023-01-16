package exercise;

// BEGIN
public class App {
    public static void printSquare(Circle circle) {
        try {
            System.out.println(Math.round(circle.getSquare()));
        } catch (NegativeRadiusException e) {
            if (e.getErrorCode().equals("NegativeRadius")) {
                System.out.println("Не удалось посчитать площадь");
            }
        }
        System.out.println("Вычисление окончено");
    }
}
// END
