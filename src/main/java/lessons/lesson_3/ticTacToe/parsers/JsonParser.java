package lessons.lesson_3.ticTacToe.parsers;

import com.google.gson.Gson;
import lessons.lesson_3.ticTacToe.models.Gameplay;
import lessons.lesson_3.ticTacToe.models.JsonGameplayRoot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonParser implements Reader {
    @Override

    public Gameplay getGameplay(String fileName) {
        Gameplay gameplay = new Gameplay();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)))){

            Gson gson = new Gson();
            JsonGameplayRoot gRoot = gson.fromJson(bufferedReader, JsonGameplayRoot.class);
            gameplay = gRoot.getGameplay();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gameplay;
    }
}
