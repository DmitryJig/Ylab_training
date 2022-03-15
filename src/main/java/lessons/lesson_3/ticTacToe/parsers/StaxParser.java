package lessons.lesson_3.ticTacToe.parsers;

import lessons.lesson_3.ticTacToe.GameFromXml;
import lessons.lesson_3.ticTacToe.models.Gameplay;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Класс stax парсера
 */

public class StaxParser {

    private static final String TAG_PLAYER = "Player";
    private static final String TAG_STEP = "Step";
    private static final String TAG_GAMERESULT = "GameResult";
    // путь к папке файл из который нужно распарсить
    private static final String DIRECTORY_PATH = "src/main/java/lessons/lesson_3/ticTacToe/savedFiles/";


    public static void main(String[] args) throws IOException {

        // Узнаем у пользователя какой файл из директории он хочет распарсить
        String fileName = getFileName(DIRECTORY_PATH);
        // Если файлов нет пишем об этом и прерываем программу
        if (fileName == null) {
            System.out.println("В папке нет файлов");
            return;
        }
        Gameplay gameplay = getGameplay(fileName);

        GameFromXml.game(gameplay);
    }

    /**
     * Метод спрашивает у пользователя какой из файлов в передаваемой методу директории надо распарсить
     * @param directory директория из которой выводим список файлов для парсинга
     * @return относительный путь выбранного файла
     * @throws IOException
     */
    public static String getFileName(String directory) throws IOException {
        File dir = new File(directory);
        File[] arrFiles = dir.listFiles();
        List<File> fileList = Arrays.asList(arrFiles);
        // Фильтруем список чтобы имена файлов начинались на "gameplay"
        List<File> cleanList = fileList.stream().filter(x -> x.getName().startsWith("gameplay")).collect(Collectors.toList());
        // если директория пуста то возвращаем пустоту
        if (cleanList.size() == 0) {
            return null;
        }
        // Выводим список файлов
        for (int i = 0; i < cleanList.size(); i++) {
            System.out.println((i + 1) + " " + cleanList.get(i).getName());
        }
        System.out.println("Выберите из списка номер файла для воспроизведения хода игры");
        Scanner scanner = new Scanner(System.in);
        int fileNumber = scanner.nextInt();

        return cleanList.get(fileNumber - 1).getCanonicalPath();
    }

    /**
     * Метод считывает из файла данные игроков и их ходы,
     * записывает все в объект класса Gameplay
     *
     * @param fileName имя файла для чтения
     * @return объект класса Gameplay со считанными в него данными
     */
    public static Gameplay getGameplay(String fileName) {

        Gameplay gameplay = new Gameplay();
        String name = "";
        Character symbol = 'X';
        boolean isGameResult = false; // флаг о том находимся ли мы внутри тэга GameResult
        try {
            XMLStreamReader reader = XMLInputFactory.newDefaultFactory().createXMLStreamReader(new FileInputStream(fileName));

            while (reader.hasNext()) {
                reader.next();

                if (reader.isStartElement()) {
                    // Если мы заходим в тэг GameResult ставим флаг в true
                    if (reader.getLocalName().equals(TAG_GAMERESULT)) {
                        isGameResult = true;
                    }
                    // Если имя тэга Player пишем в gameplay информацию об игроках
                    if (!isGameResult && reader.getLocalName().equals(TAG_PLAYER)) {

                        name = reader.getAttributeValue(1);
                        symbol = reader.getAttributeValue(2).toCharArray()[0];
                        gameplay.addGamer(name, symbol);
                        continue;
                    }
                    // Если имя тэга Step заносим координату хода в коллекцию объекта gameplay
                    if (reader.getLocalName().equals(TAG_STEP)) {
                        gameplay.addStep(reader.getElementText());
                    }
                    // Если мы внутри тега Player и внутри тега GameResult добавляем результаты в коллекцию с результатами
                    // (если коллекция будет пуста будет распечатано "Draw!"
                    if (isGameResult && reader.getLocalName().equals(TAG_PLAYER)) {
                        for (int i = 0; i < 3; i++) {
                            gameplay.addResult(reader.getAttributeValue(i));
                        }
                    }
                }
                // Если тег GameResult закрывающий возвращаем значение флага в false
                if (reader.isEndElement() && reader.getLocalName().equals(TAG_GAMERESULT)) {
                    isGameResult = false;
                }
            }
        } catch (
                FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return gameplay;
    }
}
