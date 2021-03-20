package se.cygni.rockpaperscissors.application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private GameMove gameMove;
    private Game game;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Testsson");
        gameMove = new GameMove(Move.ROCK, player);
        game = new Game(gameMove);
    }

    @Test
    void getGameId() {
        assertNotNull(game.getId());
    }

    @Test
    void addGameMove() {
        Player player2 = new Player("Tester");
        GameMove gameMove2 = new GameMove(Move.SCISSOR, player2);
        game.addGameMove(gameMove2);
        assertTrue(game.getGameMove2().isPresent());
    }

    @Test
    void addAlreadyExistingGameMove() {
        Player player2 = new Player("Tester");
        Player player3 = new Player("Tester");
        GameMove gameMove2 = new GameMove(Move.ROCK, player2);
        GameMove gameMove3 = new GameMove(Move.SCISSOR, player3);
        game.addGameMove(gameMove2);
        assertThrows(IllegalStateException.class, () -> {game.addGameMove(gameMove3);});
    }

    @Test
    void checkResultOneWinner() {
        Player player2 = new Player("Tester");
        GameMove gameMove2 = new GameMove(Move.SCISSOR, player2);
        game.addGameMove(gameMove2);
        State state = game.checkResult();
        assertEquals(State.PLAYER1WON, state);
    }

    @Test
    void checkResultEqual() {
        Player player2 = new Player("Tester");
        GameMove gameMove2 = new GameMove(Move.ROCK, player2);
        game.addGameMove(gameMove2);
        State state = game.checkResult();
        assertEquals(State.EQUAL, state);
    }

    @Test
    void checkResultNotStarted() {
        State state = game.checkResult();
        assertEquals(State.NOT_STARTED, state);
    }
}
