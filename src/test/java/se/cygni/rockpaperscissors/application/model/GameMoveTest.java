package se.cygni.rockpaperscissors.application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameMoveTest {
    private Player player;
    private GameMove gameMove;

    @BeforeEach
    void GameMoveTest() {
        player = new Player("Testsson");
        gameMove = new GameMove(Move.ROCK, player);
    }

    @Test
    void createGameMove() {
        assertNotNull(gameMove);
    }
}