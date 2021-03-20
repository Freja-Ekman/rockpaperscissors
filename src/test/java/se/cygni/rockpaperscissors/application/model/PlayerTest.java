package se.cygni.rockpaperscissors.application.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerTest {

    @Test
    void getName() {
        Player player = new Player(Move.PAPER, "Testsson");
        assertNotNull(player.getName());
    }
}
