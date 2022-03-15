package lessons.lesson_2.homeworkJavaRush.task2;

import java.util.Scanner;

public class Cycles {
    public static void main(String[] args) {
        printMultiplicationTable();
    }

    /**
     * 1. 10 чисел
     * Вывести на экран числа от 1 до 10, используя цикл while.
     */
    public static void printNumbers1To10() {
        int number = 0;
        while (number < 10) {
            number++;
            System.out.println(number);
        }
    }

    /**
     * 2. 10 чисел наоборот
     * Вывести на экран числа от 10 до 1, используя цикл while.
     */
    public static void printNumbers10To1() {
        int number = 10;
        while (number > 0) {
            System.out.println(number);
            number--;
        }
    }

    /**
     * 3. Хорошего не бывает много
     * Ввести с клавиатуры строку и число N.
     * Вывести на экран строку N раз, используя цикл while.
     */
    public static void printStringNTimes() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку");
        String line = scanner.nextLine();
        System.out.println("Введите число");
        int n = scanner.nextInt();
        while (n > 0) {
            System.out.println(line);
            n--;
        }
    }

    /**
     * 4. S-квадрат
     * Вывести на экран квадрат из 10х10 букв S используя цикл while.
     * Буквы в каждой строке не разделять.
     */
    public static void printSquare() {
        int i = 10;
        while (i > 0) {
            int j = 10;
            while (j > 0) {
                System.out.print("S");
                j--;
            }
            System.out.println();
            i--;
        }
    }

    /**
     * 5. Таблица умноження
     * Вывести на экран таблицу умножения 10х10 используя цикл while.
     * Числа разделить пробелом.
     */
    public static void printMultiplicationTable() {
        int i = 0;
        while (i < 10) {
            i++;
            int j = 0;
            while (j < 10) {
                j++;
                System.out.print((i * j) + "\t");
            }
            System.out.println();
        }
    }
}
