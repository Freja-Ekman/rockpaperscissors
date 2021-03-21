package se.cygni.rockpaperscissors.application.model;

import lombok.Data;

@Data
public class GameMove {
    private Move move;
    private Player player;

    public GameMove(Move move, Player player) {
        this.move = move;
        this.player = player;
    }
}
