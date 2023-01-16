package exercise;

import java.util.Arrays;
import java.util.logging.Logger;

// BEGIN
public class MinThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger("MinThread");

    private int[] array;
    private int min;

    public MinThread(int[] array, String name) {
        super(name);
        this.array = array;
    }

    public int getMin() {
        return min;
    }

    @Override
    public void run() {
        LOGGER.info(String.format("Thread %s started", Thread.currentThread().getName()));
        min = Arrays.stream(array).min().getAsInt();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info(String.format("Thread %s finished", Thread.currentThread().getName()));
    }
}
// END
