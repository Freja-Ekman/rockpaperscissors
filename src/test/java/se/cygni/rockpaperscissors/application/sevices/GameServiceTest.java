package se.cygni.rockpaperscissors.application.sevices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.cygni.rockpaperscissors.application.model.Game;
import se.cygni.rockpaperscissors.application.services.GameService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameServiceTest {
    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = GameService.getGameService();
    }

    @Test
    void createAndGame() {
        UUID id = gameService.createGame();
        Game game = gameService.getGame(id);
        assertEquals(id, game.getId());
    }
}