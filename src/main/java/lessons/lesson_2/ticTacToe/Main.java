package lessons.lesson_2.ticTacToe;

import java.util.List;
import java.util.Scanner;

public class Main {

    //Адрес файла где хранится рейтинг игроков,
    // изначально в нем не пустое значение что бы не было ошибки класса ObjectInputStream
    private final static String PATH = "src/main/java/lessons/lesson_2/ticTacToe/save.bin";

    //Сканнер
    static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Игрок 1 введите свое имя");
        Person gamer1 = new Person(SCANNER.nextLine());
        System.out.println("Игрок 2 введите свое имя");
        Person gamer2 = new Person(SCANNER.nextLine());

        List<Person> persons = Serialize.readPersons(PATH); // считываем список игроков с их рейтингами

        int index1 = getIndex(gamer1, persons);
        int index2 = getIndex(gamer2, persons);

        while (true) {
            System.out.println(persons.get(index1) + "\n" + persons.get(index2));
            GameProcess.game(index1, index2, persons);

            System.out.println("Хотите сыграть еще? да/нет");
            String answer = SCANNER.nextLine();

            if (!answer.equalsIgnoreCase("да")) {  //если ответ не равен "да" выход из игры
                break;
            }
        }

        Serialize.writePersons(persons, PATH); // После всех боев записываем результаты игроков в файл
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
}
