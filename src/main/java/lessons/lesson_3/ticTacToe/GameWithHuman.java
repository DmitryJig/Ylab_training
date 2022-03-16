package lessons.lesson_3.ticTacToe;

import lessons.lesson_3.ticTacToe.models.Person;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Класс игрового процесса при игре 2 человек
 * Тут же происходит запись каждой игры в XML файл
 */

public class GameWithHuman extends GameMethods {

    // Назначаем константы для названий тегов
    private static final String TAG_GAMEPLAY = "Gameplay";
    private static final String TAG_GAME = "Game";
    private static final String TAG_PLAYER = "Player";
    private static final String TAG_STEP = "Step";
    private static final String TAG_GAMERESULT = "GameResult";
    private static char SYMBOL_X = 'X';
    private static char SYMBOL_0 = '0';

    static void game(int index1, int index2, List<Person> persons) {

        // Достаем имена игроков
        String name1 = persons.get(index1).getName();
        String name2 = persons.get(index2).getName();

        // Файл для записи игры называем gameplay_dd_MM_yyyy_HH_mm_ss.xml чтобы не было одинаковых имен
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
        String fileName = "src/main/java/lessons/lesson_3/ticTacToe/savedFiles/gameplay"
                + dateTime.format(formatter) + ".xml";
        XMLOutputFactory output = XMLOutputFactory.newInstance();

        try {
            XMLStreamWriter writer = output.
                    createXMLStreamWriter(new FileOutputStream(fileName), "windows-1251");
            writer.writeStartDocument("windows-1251", "1.0");
            writer.writeCharacters("\n");
            writer.writeStartElement(TAG_GAMEPLAY);
            writer.writeCharacters("\n\t");

            writer.writeEmptyElement(TAG_PLAYER); // закрывать не надо
            writer.writeAttribute("id", "1");
            writer.writeAttribute("name", name1);
            writer.writeAttribute("symbol", String.valueOf(SYMBOL_X));
            writer.writeCharacters("\n\t");

            writer.writeEmptyElement(TAG_PLAYER); // закрывать не надо
            writer.writeAttribute("id", "2");
            writer.writeAttribute("name", name2);
            writer.writeAttribute("symbol", String.valueOf(SYMBOL_0));
            writer.writeCharacters("\n\t");

            writer.writeStartElement(TAG_GAME); // open tag Game

            int stepNumber = 0;

            initMap();
            printMap();

            while (true) {

                stepNumber++;

                writer.writeCharacters("\n\t\t");
                writer.writeStartElement(TAG_STEP); // open tag Step
                writer.writeAttribute("num", String.valueOf(stepNumber));
                writer.writeAttribute("playerId", "1");

                humanTurn(SYMBOL_X, writer); // ход игрока 1

                writer.writeEndElement(); // close tag Step
                printMap();
                if (checkWin('X')) {
                    persons.get(index1).incrementWinsCount();
                    persons.get(index2).incrementLossCount();
                    System.out.println("Побеждает " + name1);

                    writer.writeCharacters("\n\t");
                    writer.writeEndElement(); //close tag Game

                    writer.writeCharacters("\n\t");
                    writer.writeStartElement(TAG_GAMERESULT); // open Gameresult
                    writer.writeEmptyElement(TAG_PLAYER); // закрывать не надо
                    writer.writeAttribute("id", "1");
                    writer.writeAttribute("name", name1);
                    writer.writeAttribute("symbol", String.valueOf(SYMBOL_X));
                    writer.writeEndElement(); //close Gameresult
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья");
                    persons.get(index1).incrementDrawCont();
                    persons.get(index2).incrementDrawCont();

                    writer.writeCharacters("\n\t");
                    writer.writeEndElement(); //close Game
                    writer.writeCharacters("\n\t");
                    writer.writeStartElement(TAG_GAMERESULT); // open Gameresult
                    writer.writeCharacters("Draw!");
                    writer.writeEndElement(); //close Gameresult
                    break;
                }

                stepNumber++;
                writer.writeCharacters("\n\t\t");
                writer.writeStartElement(TAG_STEP);
                writer.writeAttribute("num", String.valueOf(stepNumber));
                writer.writeAttribute("playerId", "2");

                humanTurn(SYMBOL_0, writer); // ход игрока 2

                writer.writeEndElement();
                printMap();
                if (checkWin('0')) {
                    persons.get(index2).incrementWinsCount();
                    persons.get(index1).incrementLossCount();
                    System.out.println("Побеждает " + name2);

                    writer.writeCharacters("\n\t");
                    writer.writeEndElement(); //close tag Game

                    writer.writeCharacters("\n\t");
                    writer.writeStartElement(TAG_GAMERESULT); // open Gameresult
                    writer.writeEmptyElement(TAG_PLAYER); // закрывать не надо
                    writer.writeAttribute("id", "2");
                    writer.writeAttribute("name", name2);
                    writer.writeAttribute("symbol", String.valueOf(SYMBOL_0));
                    writer.writeEndElement(); //close Gameresult
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья");

                    writer.writeCharacters("\n\t");
                    writer.writeEndElement(); //close Game
                    writer.writeCharacters("\n\t");
                    writer.writeStartElement(TAG_GAMERESULT); // open Gameresult
                    writer.writeCharacters("Draw!");
                    writer.writeEndElement(); //close Gameresult
                    break;
                }
            }

            writer.writeCharacters("\n");
            // закрываем
            writer.writeEndElement(); //close Gameplay
            writer.writeCharacters("\n");
            writer.writeEndDocument();
            writer.flush();
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }
}
