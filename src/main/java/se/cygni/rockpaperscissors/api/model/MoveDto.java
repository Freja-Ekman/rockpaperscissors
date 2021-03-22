package se.cygni.rockpaperscissors.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import se.cygni.rockpaperscissors.application.model.GameMove;
import se.cygni.rockpaperscissors.application.model.Move;
import se.cygni.rockpaperscissors.application.model.Player;

@Data
@NoArgsConstructor
public class MoveDto {
    private Move move;
    private String name;

    /**
     * Translate into a GameMove from the domain.
     *
     * @return the game move from the domain
     */
    public GameMove toDomain() {
        return new GameMove(move, new Player(name));
    }
}
