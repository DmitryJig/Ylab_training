package lessons.lesson_1;

/**
 * В этом классе вызываем все созданные методы
 */

public class Main {
    public static void main(String[] args) {

        System.out.println(Fibonacci_memoization.getFibonacci(100));  // рекурсивный метод с мемоизацией
        System.out.println(Fibonacci1.getFibonacci1(10)); // медленный рекурсивный метод
        System.out.println(Fibonacci1.getFibonacci2(100)); // решение циклом с сохранением данных в массив
        System.out.println(Fibonacci1.getFibonacci3(100)); // решение с сохранением двух ранее вычисленных значений
    }
}
