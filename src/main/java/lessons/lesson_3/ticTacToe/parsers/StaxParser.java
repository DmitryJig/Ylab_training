package lessons.lesson_3.ticTacToe.parsers;

import lessons.lesson_3.ticTacToe.GameFromXml;
import lessons.lesson_3.ticTacToe.models.Gameplay;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Класс stax парсера
 */

public class StaxParser {

    private static final String TAG_PLAYER = "Player";
    private static final String TAG_STEP = "Step";
    private static final String TAG_GAMERESULT = "GameResult";

    public static void main(String[] args) {

        // путь к файлу который нужно распарсить
        String fileName = "src/main/java/lessons/lesson_3/ticTacToe/savedFiles/gameplay15_03_2022_08_46_04.xml";

        Gameplay gameplay = getGameplay(fileName);

        GameFromXml.game(gameplay);
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
                        System.out.println(gameplay.getResult()); // delete
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
