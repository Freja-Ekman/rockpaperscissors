package se.cygni.rockpaperscissors.application.model;

import lombok.Getter;

public class GameMove {
    @Getter
    private Move move;
    @Getter
    private final Player player;

    public GameMove(Move move, Player player) {
        this.move = move;
        this.player = player;
    }

    /**
     * Sets move if there is no move yet.
     * Checks that the move can not be set to HIDDEN.
     * @param move the move shall be set
     * @throws IllegalStateException if move is set to HIDDEN or player already have made a move.
     */
    public void setMove(Move move) {
        if(this.move != Move.NO_MOVE)
            throw new IllegalStateException("Already moved");
        if(move == Move.HIDDEN)
            throw new IllegalArgumentException("Not allowed");
        this.move = move;
    }
}
