package se.cygni.rockpaperscissors.application.model;

import jdk.jshell.spi.ExecutionControl;

public enum Move {
    ROCK(1),
    PAPER(2),
    SCISSOR(3),
    NO_MOVE(0);

    private int value;

    Move(int value) {
        this.value = value;
    }

    public int winsOver(Move move) {
        if(this.value == 0 || move.value == 0)
            throw new IllegalStateException();
        if (this.value == 1 && move.value == 3)
            return 1;
        if (this.value == 3 && move.value == 1)
            return -1;
        if(this.value < move.value)
            return -1;
        if(this.value > move.value)
            return 1;
        return 0;
    }
}
