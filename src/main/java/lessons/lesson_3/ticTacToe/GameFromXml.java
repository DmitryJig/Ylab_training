package lessons.lesson_3.ticTacToe;

import lessons.lesson_3.ticTacToe.models.Gameplay;

import java.util.List;
import java.util.Map;

/**
 * Класс воспроизводит ход игры из объекта gameplay
 */
public class GameFromXml extends GameMethods {

    public static void game(Gameplay gameplay) {

        Map<String, Character> gamers = gameplay.getGamers();
        String name1 = (String) gamers.keySet().toArray()[0];
        String name2 = (String) gamers.keySet().toArray()[1];
        char symbolX = gamers.get(name1);
        char symbol0 = gamers.get(name2);
        char symbol;
        List<String> steps = gameplay.getSteps();
        initMap();

        int stepNumber = 0;

        for (String step : steps) {
            stepNumber++;
            int x = Character.getNumericValue(step.charAt(0));
            int y = Character.getNumericValue(step.charAt(1));
            if(!(stepNumber % 2 == 0)){
                symbol = symbolX;
            } else {
                symbol = symbol0;
            }
            gameplayTurn(symbol, x, y);
            printMap();
        }
        if (gameplay.getResult().isEmpty()){
            System.out.println("Draw!");
        } else {
            List<String> results = gameplay.getResult();
            System.out.println("Player " + results.get(0) + " -> " + results.get(1) + " is winner as '" + results.get(2) +"'!");
        }
    }
}


