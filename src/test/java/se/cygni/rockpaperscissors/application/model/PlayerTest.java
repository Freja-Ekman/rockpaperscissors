package se.cygni.rockpaperscissors.application.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerTest {

    @Test
    void getId() {
        Player player = new Player(Move.PAPER);
        assertNotNull(player.getId());
    }
}
