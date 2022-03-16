package lessons.lesson_3.ticTacToe.parsers;

import lessons.lesson_3.ticTacToe.GameFromXml;
import lessons.lesson_3.ticTacToe.models.Gameplay;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Класс SAX парсера
 */

public class SaxGameplayParser {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        // путь к файлу который нужно распарсить
        String fileName = "src/main/java/lessons/lesson_3/ticTacToe/savedFiles/gameplay.xml";

        Gameplay gameplay = parse(fileName);
        GameFromXml.game(gameplay);
    }

    public static Gameplay parse(String fileName) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SaxParserHandler handler = new SaxParserHandler();
        SAXParser parser = factory.newSAXParser();
        parser.parse(fileName, handler);

        return handler.getGameplay();
    }
}
