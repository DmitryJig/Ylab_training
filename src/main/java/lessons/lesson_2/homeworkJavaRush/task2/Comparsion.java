package lessons.lesson_2.homeworkJavaRush.task2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Задачи на условные операторы
 * понимаю что методы надо делать передавая в них аргументы но так быстрее
 * так же не сделал обраотку исключений (неверно введенных данных)
 * потому что хочу еще успеть сделать крестики-нолики
 */
public class Comparsion {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Задача 1. Минимум двух чисел
     * Ввести с клавиатуры два числа, и вывести на экран минимальное из них.
     */
    public static void printMin() {
        System.out.println("Введите первое число");
        int a = scanner.nextInt();
        System.out.println("Введите второе число");
        int b = scanner.nextInt();
        if (a < b) {
            System.out.println(a);
        } else if (b < a) {
            System.out.println(b);
        } else {
            System.out.println("Числа равны");
        }
    }

    /**
     * Задача 2. Максимум четырех чисел
     * Ввести с клавиатуры четыре числа, и вывести максимальное из них.
     */
    public static void printMax() {
        int max = Integer.MIN_VALUE;
        int number = 0;
        System.out.println("Введите четыре числа, можно через пробел или через enter");
        for (int i = 0; i < 4; i++) {
            number = scanner.nextInt();
            if (number > max) {
                max = number;
            }
        }
        System.out.println(max);
    }

    /**
     * Задача 3. Сортировка трех чисел
     * Ввести с клавиатуры три числа, и вывести их в порядке убывания.
     */
    public static void sorting() {
        System.out.println("Введите 3 числа и увидите их в прядке убывания");
        int[] arr = new int[3];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        Arrays.sort(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.println(arr[i]);
        }
    }

    /**
     * Задача 5. 18+
     * Ввести с клавиатуры имя и возраст. Если возраст меньше 18 вывести надпись «Подрасти еще»
     */
    public static void checkAge(){
        System.out.println("Введите Ваше имя");
        String name = scanner.nextLine();
        System.out.println("Введите Ваш возраст");
        int age = scanner.nextInt();
        if (age < 18) {
            System.out.println("Подрасти еще " + name);
        }
    }

    /**
     * Задача 6. И 18-ти достаточно
     * Ввести с клавиатуры имя и возраст.
     * Если возраст больше 20 вывести надпись «И 18-ти достаточно»
     */
    public static void enough_18 () {
        System.out.println("Введите Ваше имя");
        String name = scanner.nextLine();
        System.out.println("Введите Ваш возраст");
        int age = scanner.nextInt();
        if (age > 20) {
            System.out.println("И 18-ти достаточно");
        }
    }
}
