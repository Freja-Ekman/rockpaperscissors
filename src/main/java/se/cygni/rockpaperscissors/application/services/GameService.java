package se.cygni.rockpaperscissors.application.services;

import com.sun.management.GarbageCollectionNotificationInfo;
import se.cygni.rockpaperscissors.application.model.Game;
import java.util.HashMap;
import java.util.UUID;

public class GameService {
    private static GameService singleton;
    private HashMap<UUID, Game> games;

    private GameService() {
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

    public static synchronized GameService getGameService() {
        if(singleton==null)
            singleton = new GameService();
        return singleton;
    }
}
