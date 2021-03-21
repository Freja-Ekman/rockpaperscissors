package se.cygni.rockpaperscissors.application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private GameMove starterGameMove;
    private Game game;
    private Player starter;

    @BeforeEach
    void setUp() {
        game = new Game();
        starter = new Player("Testsson");
        starterGameMove = new GameMove(Move.ROCK, starter);
    }

    @Test
    void getGameId() {
        assertNotNull(game.getId());
    }

    @Test
    void addOneGameMove() {
        game.addGameMove(starterGameMove);
        assertTrue(game.getGameMove1().isPresent());
    }

    @Test
    void addTwoGameMoves() {
        game.addGameMove(starterGameMove);
        Player player = new Player("Tester");
        GameMove playerGameMove = new GameMove(Move.SCISSOR, player);
        game.addGameMove(playerGameMove);
        assertTrue(game.getGameMove2().isPresent());
    }

    @Test
    void addThreeGameMoves() {
        game.addGameMove(starterGameMove);
        Player player = new Player("Tester");
        Player player1 = new Player("Testare");
        GameMove gameMove = new GameMove(Move.ROCK, player);
        GameMove gameMove1 = new GameMove(Move.SCISSOR, player1);
        game.addGameMove(gameMove);
        assertThrows(IllegalStateException.class, () -> {game.addGameMove(gameMove1);});
    }

    @Test
    void checkResultOneWinner() {
        game.addGameMove(starterGameMove);
        Player player = new Player("Tester");
        GameMove gameMove = new GameMove(Move.SCISSOR, player);
        game.addGameMove(gameMove);
        State state = game.checkResult();
        assertEquals(State.PLAYER1WON, state);
    }

    @Test
    void checkResultEqual() {
        game.addGameMove(starterGameMove);
        Player player = new Player("Tester");
        GameMove gameMove = new GameMove(Move.ROCK, player);
        game.addGameMove(gameMove);
        State state = game.checkResult();
        assertEquals(State.EQUAL, state);
    }

    @Test
    void checkResultNoGameMoveFromOne() {
        game.addGameMove(starterGameMove);
        State state = game.checkResult();
        assertEquals(State.NOT_STARTED, state);
    }

    @Test
    void checkResultNoGameMoveFromBoth() {
        State state = game.checkResult();
        assertEquals(State.NOT_STARTED, state);
    }

    @Test
    void getGameMoveNoMove() {
        assertThrows(IllegalStateException.class, () -> {game.getGameMove(starter);});
    }

    @Test
    void getGameMove() {
        game.addGameMove(starterGameMove);
        assertEquals(starterGameMove, game.getGameMove(starter));
    }
}
