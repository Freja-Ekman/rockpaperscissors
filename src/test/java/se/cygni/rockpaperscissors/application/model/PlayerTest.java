package se.cygni.rockpaperscissors.application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Testsson");
    }
    @Test
    void getName() {
        assertNotNull(player.getName());
    }
}
