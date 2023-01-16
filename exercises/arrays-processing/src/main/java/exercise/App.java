package exercise;

import java.util.Arrays;

class App {
    // BEGIN
    public static int[] getElementsLessAverage(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        double avg = average(arr);
        int[] resultArr = new int[arr.length];
        int i = 0;
        for (int item : arr) {
            if (item <= avg) {
                resultArr[i++] = item;
            }
        }
        return Arrays.copyOf(resultArr, i);
    }

    private static double average(int[] arr) {
        double sum = 0;
        for (int item : arr) {
            sum += item;
        }
        return sum / arr.length;
    }
    // END
}
