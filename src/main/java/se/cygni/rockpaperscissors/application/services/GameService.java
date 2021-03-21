package se.cygni.rockpaperscissors.application.services;

import se.cygni.rockpaperscissors.application.model.Game;

import java.util.UUID;

public interface GameService {
    UUID createGame();

    Game getGame(UUID id);
}
