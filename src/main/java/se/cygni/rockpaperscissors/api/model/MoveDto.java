package se.cygni.rockpaperscissors.api.model;

import lombok.Data;
import se.cygni.rockpaperscissors.application.model.GameMove;
import se.cygni.rockpaperscissors.application.model.Move;
import se.cygni.rockpaperscissors.application.model.Player;

@Data
public class MoveDto {
    private Move move;
    private String name;

    public MoveDto() {

    }

    /**
     * Translate into a GameMove from the domain.
     *
     * @return the game move from the domain
     */
    public GameMove toDomain() {
        return new GameMove(move, new Player(name));
    }
}
