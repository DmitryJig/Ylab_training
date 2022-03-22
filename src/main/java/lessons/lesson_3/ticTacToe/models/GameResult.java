package lessons.lesson_3.ticTacToe.models;

import com.google.gson.annotations.SerializedName;
import lessons.lesson_3.ticTacToe.models.Player;

public class GameResult {
    @SerializedName("Player")
    public Player player;

    public GameResult(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
