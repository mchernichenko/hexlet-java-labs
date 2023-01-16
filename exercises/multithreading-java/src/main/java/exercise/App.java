package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static void main(String[] args) throws InterruptedException {
        int[] numbers = {10, -4, 67, 100, -100, 8};
        Map<String, Integer> result = App.getMinMax(numbers);
        System.out.println(result.toString());
    }

    public static Map<String, Integer> getMinMax(int[] arr) {
        MaxThread maxThread = new MaxThread(arr,"MaxThread" );
        MinThread minThread = new MinThread(arr, "MinThread");

        LOGGER.info(String.format("Threads are starting"));
        maxThread.start();
        minThread.start();
        try {
            minThread.join(); // в основном потоке ждём завершение потока minThread
            maxThread.join(); // в основном потоке ждём завершение потока maxThread
            LOGGER.info(String.format("All Threads finished"));
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return Map.of("min", minThread.getMin(), "max", maxThread.getMax());
    }
    // END
}

