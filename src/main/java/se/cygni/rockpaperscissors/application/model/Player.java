package se.cygni.rockpaperscissors.application.model;

import lombok.Data;
import java.util.UUID;

@Data
public class Player {
    private Move move;
    private String name;

    public Player(Move move, String name) {
        this.move = move;
        this.name = name;
    }
}
