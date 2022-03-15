package lessons.lesson_3.ticTacToe.parsers;

import lessons.lesson_3.ticTacToe.models.Gameplay;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Класс SAX хэндлера
 */

public class SaxParserHandler extends DefaultHandler {

    private static final String TAG_PLAYER = "Player";
    private static final String TAG_STEP = "Step";
    private static final String TAG_GAMERESULT = "GameResult";

    Gameplay gameplay = new Gameplay();

    private String currentTagName;
    private Boolean isGameResult = false;

    public Gameplay getGameplay() {
        return gameplay;
    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        currentTagName = qName;
        if (qName.equals(TAG_GAMERESULT)) {
            isGameResult = true;
        }
        if (!isGameResult && currentTagName.equals(TAG_PLAYER)) {
            gameplay.addGamer(attributes.getValue(1), attributes.getValue(2).toCharArray()[0]);
        }
        if (isGameResult && currentTagName.equals(TAG_PLAYER)) {
            for (int i = 0; i < 3; i++) {
                gameplay.addResult(attributes.getValue(i));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals(TAG_GAMERESULT)){
            isGameResult = false;
        }
        currentTagName = null;

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (currentTagName == null) {
            return;
        }
        if (currentTagName.equals(TAG_STEP)) {
            gameplay.addStep(new String(ch, start, length));
        }
    }
}
