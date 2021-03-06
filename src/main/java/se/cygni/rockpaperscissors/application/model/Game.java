package se.cygni.rockpaperscissors.application.model;

import lombok.Getter;

import java.util.Optional;
import java.util.UUID;

public class Game {
    @Getter
    private Optional<GameMove> gameMove1;
    @Getter
    private Optional<GameMove> gameMove2;
    @Getter
    private final UUID id;

    public Game() {
        this.id = UUID.randomUUID();
        gameMove1 = Optional.empty();
        gameMove2 = Optional.empty();
    }

    /**
     * Add game move if it does not already exist.
     *
     * @param gameMove the game move
     * @throws IllegalStateException thrown if two player already exists
     */
    public void addGameMove(GameMove gameMove) {
        if(gameMove1.isEmpty()) {
            gameMove1 = Optional.of(gameMove);
        } else if(gameMove2.isEmpty()) {
            gameMove2 = Optional.of(gameMove);
        } else {
            throw new IllegalStateException("Player already exists");
        }
    }

    /**
     * Gets game move of a player if there is a game move.
     *
     * @param player the player
     * @return the game move of a specific player
     * @throws IllegalStateException thrown when the player is not a part of the game and have not made a move
     */
    public GameMove getGameMove(Player player) {
        if(gameMove1.isPresent() && gameMove1.get().getPlayer().equals(player))
            return gameMove1.get();
        if(gameMove2.isPresent() && gameMove2.get().getPlayer().equals(player))
            return gameMove2.get();
        throw new IllegalStateException("Player is not a part of the game");
    }

    /**
     * Gets result of a game or the state of a not finished game.
     *
     * @return the state of a game
     */
    public State checkResult() {
        if(gameMove1.isEmpty() || gameMove2.isEmpty())
            return State.NOT_FINISHED;
        try {
            int result = gameMove1.get().getMove().winsOver(gameMove2.get().getMove());
            switch (result) {
                case 1:
                    return State.PLAYER1WON;
                case -1:
                    return State.PLAYER2WON;
                default:
                    return State.EQUAL;
            }
        } catch (IllegalStateException e) {
            return State.NOT_FINISHED;
        }

    }
}
