package se.cygni.rockpaperscissors.application.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoveTest {

    @Test
    void scissorBeatsPaper() {
        assertEquals(1, Move.SCISSOR.winsOver(Move.PAPER));
    }

    @Test
    void paperBeatsRock() {
        assertEquals(1, Move.PAPER.winsOver(Move.ROCK));
    }

    @Test
    void rockBeatsScissor() {
        assertEquals(1, Move.ROCK.winsOver(Move.SCISSOR));
    }

    @Test
    void scissorLosesRock() {
        assertEquals(-1, Move.SCISSOR.winsOver(Move.ROCK));
    }

    @Test
    void paperLosesScissor() {
        assertEquals(-1, Move.PAPER.winsOver(Move.SCISSOR));
    }

    @Test
    void rockLosesPaper() {
        assertEquals(-1, Move.ROCK.winsOver(Move.PAPER));
    }

    @Test
    void rockEqualRock() {
        assertEquals(0, Move.ROCK.winsOver(Move.ROCK));
    }

    @Test
    void paperEqualPaper() {
        assertEquals(0, Move.PAPER.winsOver(Move.PAPER));
    }

    @Test
    void scissorEqualScissor() {
        assertEquals(0, Move.SCISSOR.winsOver(Move.SCISSOR));
    }
}
