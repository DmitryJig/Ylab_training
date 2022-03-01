package lessons.lesson_1;

public class Fibonacci1 {

    /**
     * 1. рекурсивная реализация, самая медленная
     */
    public static long getFibonacci1(int n) {
        if (n <= 1) {
            return n;
        }
        return getFibonacci1(n - 1) + getFibonacci1(n - 2);
    }

    /**
     * 2. решение с массивом в который ложим все значения элементов последовательности
     *
     * @param n номер элемента последовательности (начинается с нуля)
     * @return значение элемента последовательности с номером n
     */
    public static long getFibonacci2(int n) {

        long[] arr = new long[n + 1];

        for (int i = 0; i <= n; i++) {
            if (i < 2) {
                arr[i] = i;
            } else {
                arr[i] = arr[i - 1] + arr[i - 2];
            }
        }
        return arr[n];
    }

    /**
     * 3. Решение без массива сохраняя только 2 ранее вычисленных значения
     *
     * @param n номер элемента последовательности (начинается с нуля)
     * @return значение элемента последовательности с номером n
     */
    public static long getFibonacci3(int n) {

        long result = 0L;
        long resultMinus1 = 0L;
        long resultMinus2 = 0L;

        for (int i = 0; i <= n; i++) {
            if (i < 2) {
                result = i;
                resultMinus1 = result;
            } else {
                result = resultMinus1 + resultMinus2;
                resultMinus2 = resultMinus1;
                resultMinus1 = result;
            }
        }
        return result;
    }
}

