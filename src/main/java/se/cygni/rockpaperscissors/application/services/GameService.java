package se.cygni.rockpaperscissors.application.services;

import se.cygni.rockpaperscissors.application.model.Game;

import java.util.UUID;

public interface GameService {
    /**
     * Create a game with a specific uuid.
     *
     * @return the unique uuid of the created game
     */
    UUID createGame();

    /**
     * Gets the game with a specific uuid.
     *
     * @param id the uuid of the game
     * @return the game with the specific uuid
     */
    Game getGame(UUID id);
}
