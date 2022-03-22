package lessons.lesson_3.ticTacToe.models;

import com.google.gson.annotations.SerializedName;
import lessons.lesson_3.ticTacToe.models.Gameplay;

public class JsonGameplayRoot {

    @SerializedName("Gameplay")
    private Gameplay gameplay;

    public JsonGameplayRoot() {
    }
    public JsonGameplayRoot(Gameplay gameplay) {
        this.gameplay = gameplay;
    }

    public Gameplay getGameplay() {
        return gameplay;
    }
}
