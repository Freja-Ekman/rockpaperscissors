package se.cygni.rockpaperscissors.application.model;

import lombok.Data;
import java.util.Optional;
import java.util.UUID;

@Data
public class Game {
    private GameMove gameMove1;
    private Optional<GameMove> gameMove2;
    private UUID id;


    public Game(GameMove gameMove1) {
        this.gameMove1 = gameMove1;
        this.id = UUID.randomUUID();
        gameMove2 = Optional.empty();
    }

    public void addGameMove(GameMove gameMove) {
        if(gameMove2.isPresent())
            throw new IllegalStateException("Player already exists");
        gameMove2 = Optional.of(gameMove);
    }

    public State checkResult() {
        if(gameMove2.isEmpty())
            return State.NOT_STARTED;
        int result = gameMove1.getMove().winsOver(gameMove2.get().getMove());
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
