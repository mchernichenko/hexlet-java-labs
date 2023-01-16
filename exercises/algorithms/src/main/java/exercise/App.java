package exercise;

class App {
    // BEGIN
    /**
     * Сортировка методом пузырька.
     * @param numbers
     * @return - отсортированный массив по возрастанию
     */
    public static int[] sort(int[] numbers) {
        if ((numbers == null) || (numbers.length <= 1)) {
            return numbers;
        }

        boolean isFinish;
        do {
            isFinish = true;
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] > numbers[i + 1]) {
                    changeValue(numbers, i, i + 1);
                    isFinish = false;
                }
            }
        } while (!isFinish);
        return numbers;
    }

    /**
     * Сортировка массива методом выбора.
     * @param numbers массив данных
     * @return - отсортированный массив по возрастанию
     */
    public static int[] sort1(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            int minIndex = minIndex(numbers, i);
            changeValue(numbers, i, minIndex);
        }
        return numbers;
    }

    /**
     * Поменять местами значения arr[index1] и arr[index2].
     * @param arr - массив
     * @param index1 - индекс 1-го значения
     * @param index2 - индекс 2-го значения
     */
    public static void changeValue(int[] arr, int index1, int index2) {
        if (index1 != index2) {
            arr[index1] += arr[index2];
            arr[index2] = arr[index1] - arr[index2];
            arr[index1] -= arr[index2];
        }
    }

    /**
     * Поиск индекса минимального значения в массиве.
     * @param arr - массив для поиска
     * @param start - индекс, с которого начинается поиск
     * @return индекс минимального значения в массиве начиная с индекса start
     */
    public static int minIndex(int[] arr, int start) {
        if ((start < 0) || (start >= arr.length)) {
            return -1;
        } else if (start == arr.length - 1) {
            return start;
        }

        int minIndex = start++;
        int minValue = Integer.MAX_VALUE;
        for (int i = start; i < arr.length; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
    // END
}
