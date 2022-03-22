package lessons.lesson_3.ticTacToe.parsers;

import lessons.lesson_3.ticTacToe.GameFromGameplay;
import lessons.lesson_3.ticTacToe.GameWriter;
import lessons.lesson_3.ticTacToe.models.Gameplay;

import java.io.IOException;
import java.util.Scanner;

public class ParserMain {

    // путь к папке файл из который нужно распарсить
    private static final String DIRECTORY_PATH = "src/main/java/lessons/lesson_3/ticTacToe/savedFiles/";
    // Значение окончания файла для определения его типа при поиске в указанной папке
    private static final String XML_FILE_TYPE = ".xml";
    private static final String JSON_FILE_TYPE = ".json";

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите какой тип файла будем воспроизводить: 1 -xml, 2 - json");

        int choiceType = scanner.nextInt();

        // В зависимости от выбора создаем объект класса читателя (по умолчанию xml), если
        Reader reader = new XmlStaxParser();
        String fileType = XML_FILE_TYPE;

        if (choiceType == 2) {
            reader = new JsonParser();
            fileType = JSON_FILE_TYPE;
        }
        String fileName = reader.getFileName(DIRECTORY_PATH, fileType);
        
        Gameplay gameplay = reader.getGameplay(fileName);

        GameFromGameplay.game(gameplay);
    }


}
