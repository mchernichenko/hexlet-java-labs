package exercise;

import java.util.Arrays;
import java.util.logging.Logger;

// BEGIN
public class MaxThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger("MaxThread");

    private int[] array;
    private int max;

    public MaxThread(int[] array, String name) {
        super(name);
        this.array = array;
    }

    public int getMax() {
        return max;
    }

    @Override
    public void run() {
        LOGGER.info(String.format("Thread %s started", Thread.currentThread().getName()));
        max = Arrays.stream(array).max().getAsInt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info(String.format("Thread %s finish", Thread.currentThread().getName()));
    }
}
// END
