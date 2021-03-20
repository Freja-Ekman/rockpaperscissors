package se.cygni.rockpaperscissors.application.model;

import lombok.Data;
import java.util.Optional;
import java.util.UUID;

@Data
public class Game {
    private Optional<GameMove> gameMove1;
    private Optional<GameMove> gameMove2;
    private UUID id;


    public Game() {
        this.id = UUID.randomUUID();
        gameMove1 = Optional.empty();
        gameMove2 = Optional.empty();
    }

    public void addGameMove(GameMove gameMove) {
        if(gameMove1.isEmpty()) {
            gameMove1 = Optional.of(gameMove);
        } else if(gameMove1.isPresent() && gameMove2.isEmpty()) {
            gameMove2 = Optional.of(gameMove);
        } else {
            throw new IllegalStateException("Player already exists");
        }
    }

    public State checkResult() {
        if(gameMove1.isEmpty() || gameMove2.isEmpty())
            return State.NOT_STARTED;
        int result = gameMove1.get().getMove().winsOver(gameMove2.get().getMove());
        switch (result) {
            case 1:
                return State.PLAYER1WON;
            case -1:
                return State.PLAYER2WON;
            default:
                return State.EQUAL;
        }
    }
}
