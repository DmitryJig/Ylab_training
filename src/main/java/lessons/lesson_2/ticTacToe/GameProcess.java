package lessons.lesson_2.ticTacToe;


import java.util.List;
import java.util.Scanner;

/**
 * В этом классе проходит игровой процесс и содержатся методы для него
 */

public class GameProcess {

    //поле
    private static char[][] map;

    //размер поля любое целое положительное число
    public static final int SIZE = 3;

    //точек для победы любое целое положительное число меньшее или равно SIZE
    public static final int DOTS_TO_WIN = 3;

    /**
     * Метод обьединяющий все методы игрового процесса
     * @param index1 индекс первого игрока в массиве persons
     * @param index2 индекс второго игрока в массиве persons
     * @param persons массив игроков
     */
    public static void game(int index1, int index2, List<Person> persons) {

        initMap();
        printMap();
        while (true) {
            gamerTurn('X');
            printMap();
            if (checkWin('X')) {
                persons.get(index1).incrementWinsCount();
                persons.get(index2).incrementLossCount();
                System.out.println("Побеждает " + persons.get(index1).getName());

                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                persons.get(index1).incrementDrawCont();
                persons.get(index2).incrementDrawCont();
                break;
            }
            gamerTurn('0');
            printMap();
            if (checkWin('0')) {
                persons.get(index2).incrementWinsCount();
                persons.get(index1).incrementLossCount();
                System.out.println("Побеждает " + persons.get(index2).getName());
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
    }

    /**
     * Инициализируем поле map значениями '-'
     */
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = '-';
            }
        }
    }

    /**
     * Вывод игрового поля на экран
     */
    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + "|");
            }
            System.out.println();
        }
    }

    /**
     * ход игрока
     */
    public static void gamerTurn(char symbol) {
        Scanner scanner = new Scanner(System.in);
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = symbol;
        System.out.println("Вы сходили в точку " + (x + 1) + " " + (y + 1));
    }

    /**
     * проверка валидны ли значения поля
     *
     * @param x координата х
     * @param y координата у
     * @return да если сходить можно, нет если место занято или за пределами поля
     */
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        if (map[y][x] != '-') {
            return false;
        }
        return true;
    }

    /**
     * Проверка победы, метод работает с любым размером поля, с любым количеством точек для победы
     *
     * @param symbol символ для которого проверяем крестик или нолик
     * @return true если выиграл
     */
    public static boolean checkWin(char symbol) {

        //проверяем строки и колонки
        for (int i = 0; i < SIZE; i++) {
            int dotCountX = 0;
            int dotCountY = 0;
            for (int j = 0; j < SIZE; j++) {
                dotCountX = map[i][j] == symbol ? ++dotCountX : 0;
                dotCountY = map[j][i] == symbol ? ++dotCountY : 0;
                if (dotCountX >= DOTS_TO_WIN || dotCountY >= DOTS_TO_WIN) {
                    return true;
                }
            }
        }
        // диагональ слева направо сверху вниз

        int countPositions = SIZE - DOTS_TO_WIN + 1; // количество линий, по которым можно выиграть на одной оси

        for (int startPos = 0; startPos < countPositions; startPos++) { // стартовая позиция счетчика
            int j = 0;
            int dotCount1 = 0; // счетчик точек слева направо сверху вниз на диагонали и ниже
            int dotCount2 = 0; // счетчик точек слева направо сверху вниз на диагонали и выше
            for (int i = startPos; i < SIZE; i++) {
                dotCount1 = map[i][j] == symbol ? ++dotCount1 : 0;
                dotCount2 = map[j][i] == symbol ? ++dotCount2 : 0;
                if (dotCount1 >= DOTS_TO_WIN || dotCount2 >= DOTS_TO_WIN) {
                    return true;
                }
                j++;
            }
        }

        // диагональ слева направо снизу вверх

        for (int startPos = SIZE - 1; startPos >= (SIZE - countPositions); startPos--) {
            int j = 0;
            int dotCount1 = 0;
            int dotCount2 = 0;
            for (int i = startPos; i >= 0; i--) {
                dotCount1 = map[i][j] == symbol ? ++dotCount1 : 0;
                dotCount2 = map[SIZE - j - 1][SIZE - i - 1] == symbol ? ++dotCount2 : 0;
                if (dotCount1 >= DOTS_TO_WIN || dotCount2 >= DOTS_TO_WIN) {
                    return true;
                }
                j++;
            }
        }
        return false;
    }

    /**
     * Проверка что в поле не осталось ни одной свободной ячейки
     *
     * @return true если нет ни одной свободной
     */
    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
