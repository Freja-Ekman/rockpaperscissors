package se.cygni.rockpaperscissors.api.model;

import lombok.Data;
import se.cygni.rockpaperscissors.application.model.*;

import java.util.Optional;

@Data
public class StatusDto {
    private State state;
    private String player1;
    private String player2;
    private Move movePlayer1;
    private Move movePlayer2;

    public StatusDto(Game game) {
        state = game.checkResult();
        Optional<GameMove> optionalGameMove1 = game.getGameMove1();
        Optional<GameMove> optionalGameMove2 = game.getGameMove2();
        if (optionalGameMove1.isPresent()) {
            player1 = optionalGameMove1.get().getPlayer().getName();
        } else {
            player1 = "Unknown";
        }
        if (optionalGameMove2.isPresent()) {
            player2 = optionalGameMove2.get().getPlayer().getName();
        } else {
            player2 = "Unknown";
        }
        if(state != State.NOT_FINISHED) {
            movePlayer1 = optionalGameMove1.get().getMove();
            movePlayer2 = optionalGameMove2.get().getMove();
        } else {
            movePlayer1 = Move.HIDDEN;
            movePlayer2 = Move.HIDDEN;
        }
    }
}
