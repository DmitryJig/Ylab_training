package lessons.lesson_2.ticTacToe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    //Адрес файла где хранится рейтинг игроков,
    // изначально в нем не пустое значение что бы не было ошибки класса ObjectInputStream
    private final static String PATH = "src/main/java/lessons/lesson_2/ticTacToe/save.bin";

    //Сканнер
    static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        // Считываем имена игроков и создаем обьекты игроков со считанными именами
        System.out.println("Игрок 1 введите свое имя");
        Person gamer1 = new Person(SCANNER.nextLine());
        System.out.println("Игрок 2 введите свое имя");
        Person gamer2 = new Person(SCANNER.nextLine());

        // десериализуем список игроков с их рейтингами из файла
        List<Person> persons = Serialize.readPersons(PATH);

        // ищем индексы игроков в полученной из десериализации коллекции, сли их нет добавляем их в конец коллекции
        int index1 = getIndex(gamer1, persons);
        int index2 = getIndex(gamer2, persons);

        while (true) {
            // Игровой процесс
            GameProcess.game(index1, index2, persons);

            // Вызываем метод печатающий рейтинг игроков и их место в нем
            printRating(gamer1, gamer2, persons);

            System.out.println("Хотите сыграть еще? да/нет");
            String answer = SCANNER.nextLine();

            //если ответ не равен "да" выход из игры
            if (!answer.equalsIgnoreCase("да")) {
                break;
            }
        }

        // После всех боев записываем результаты игроков в файл (сериализуем)
        Serialize.writePersons(persons, PATH);
    }

    /**
     * Метод ищет во входящей коллекции индекс нахождения игрока,
     * если его нет то добавляет игрока в коллекцию и возвращает индекс его нахождения
     * это нужно для
     *
     * @param gamer   объект игрока
     * @param persons коллекция объектов игроков
     * @return индекс вхождения игрока в коллекции
     */
    public static int getIndex(Person gamer, List<Person> persons) {
        int index = 0;
        if (persons.contains(gamer)) {
            index = persons.indexOf(gamer);
        } else {
            persons.add(gamer);
            index = persons.size() - 1;
        }
        return index;
    }

    /**
     * Метод печатает рейтинг игроков и место только что игравших игроков в этом рейтинге
     * @param gamer1 объект игрока 1
     * @param gamer2 объект игрока 2
     * @param persons коллекция игроков
     */
    public static void printRating(Person gamer1, Person gamer2, List<Person> persons){
        // Сортируем коллекцию игроков в порядке убывания количества побед
        persons = persons.stream().sorted((x, y) -> (y.getWinsCount() - x.getWinsCount())).collect(Collectors.toList());

        // Печатаем рейтинг игроков (у кого больше побед те выше в списке)
        System.out.println("Рейтинг игроков:");
        for (int i = 0; i < persons.size(); i++) {
            System.out.println((i + 1) + " место " + persons.get(i));
        }
        System.out.println("Игрок " + gamer1.getName() + " Вы на " + (persons.indexOf(gamer1) + 1) + " месте");
        System.out.println("Игрок " + gamer2.getName() + " Вы на " + (persons.indexOf(gamer2) + 1) + " месте");
    }
}
