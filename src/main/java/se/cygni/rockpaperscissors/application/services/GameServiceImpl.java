package se.cygni.rockpaperscissors.application.services;

import org.springframework.stereotype.Service;
import se.cygni.rockpaperscissors.application.model.Game;
import java.util.HashMap;
import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {
    private final HashMap<UUID, Game> games;

    public GameServiceImpl() {
        games = new HashMap<>();
    }

    @Override
    public UUID createGame() {
        Game game = new Game();
        UUID id = game.getId();
        games.put(id, game);
        return id;
    }

    @Override
    public Game getGame(UUID id) {
        return games.get(id);
    }
}
