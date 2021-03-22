package se.cygni.rockpaperscissors.application.model;

public enum Move {
    ROCK(1),
    PAPER(2),
    SCISSOR(3),
    NO_MOVE(0),
    HIDDEN(-1);

    private final int value;

    Move(int value) {
        this.value = value;
    }

    /**
     * Calculates the winning move.
     *
     * @param move the move that is compared to this move
     * @return 0 if no move were made, 1 if this won, -1 if move one
     */
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
