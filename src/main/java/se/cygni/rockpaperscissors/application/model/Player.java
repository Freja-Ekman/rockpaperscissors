package se.cygni.rockpaperscissors.application.model;

import lombok.Data;
import java.util.UUID;

@Data
public class Player {
    private UUID id;
    private Move move;

    public Player(Move move) {
        this.id = UUID.randomUUID();
        this.move = move;
    }
}
