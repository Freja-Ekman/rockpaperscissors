package se.cygni.rockpaperscissors.application.model;

import lombok.Getter;

public class GameMove {
    @Getter
    private Move move;
    @Getter
    private Player player;

    public GameMove(Move move, Player player) {
        this.move = move;
        this.player = player;
    }

    public void setMove(Move move) {
        if(this.move != Move.NO_MOVE)
            throw new IllegalStateException("Already moved");
        if(move == Move.HIDDEN)
            throw new IllegalArgumentException("Not allowed");
        this.move = move;
    }
}
