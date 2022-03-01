package lessons.lesson_1;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *  4. Рекурсивный метод с мемоизацией (как я понял задание заключалось в этом?)
 */

public class Fibonacci_memoization {

    private static long[] mem; // Создаем ссылку на массив под хранение вычисленных значений

    public static long getFibonacci(int n) {
        if (n <= 1){
            return n;
        }
        if (mem == null || mem.length < (n + 1)){   // Если массива нет или он короткий инициализируем его и заполняем значением -1
            mem = new long[n + 1];
            Arrays.fill(mem, -1);
        }
        if (mem[n] != -1){
            return mem[n];
        }

        long result = getFibonacci(n-1) + getFibonacci(n-2);
        mem[n] = result;
        return result;
    }
}
