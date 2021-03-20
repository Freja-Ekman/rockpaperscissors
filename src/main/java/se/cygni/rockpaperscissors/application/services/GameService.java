package se.cygni.rockpaperscissors.application.services;

import se.cygni.rockpaperscissors.application.model.Game;
import java.util.HashMap;
import java.util.UUID;

public class GameService {
    private HashMap<UUID, Game> games;

    public GameService() {
        games = new HashMap<UUID, Game>();
    }

    public UUID createGame() {
        Game game = new Game();
        UUID id = game.getId();
        games.put(id, game);
        return id;
    }

    public Game getGame(UUID id) {
        return games.get(id);
    }
}
