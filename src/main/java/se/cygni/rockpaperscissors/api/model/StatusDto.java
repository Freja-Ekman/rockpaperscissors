package se.cygni.rockpaperscissors.api.model;

import lombok.Data;
import se.cygni.rockpaperscissors.application.model.Game;
import se.cygni.rockpaperscissors.application.model.GameMove;
import se.cygni.rockpaperscissors.application.model.Player;
import se.cygni.rockpaperscissors.application.model.State;

import java.util.Optional;

@Data
public class StatusDto {
    private State state;
    private String player1;
    private String player2;

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
    }
}
