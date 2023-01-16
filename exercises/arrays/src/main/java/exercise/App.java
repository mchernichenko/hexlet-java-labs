package exercise;

class App {
    // BEGIN
    public static int[] reverse(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }

        int[] result = new int[arr.length];
        for (int i = 0; i <= arr.length / 2; i++) {
            result[i] = arr[arr.length - i - 1];
            result[arr.length - i - 1] = arr[i];
        }
        return result;
    }

    public static int getIndexOfMaxNegative(int[] arr) {
        int maxNegativValue = Integer.MIN_VALUE;
        int index = -1;

        for (int i = 0; i <= arr.length - 1; i++) {
            if (arr[i] < 0 && arr[i] > maxNegativValue) {
                maxNegativValue = arr[i];
                index = i;
            }
        }
        return index;
    }
    // END
}
