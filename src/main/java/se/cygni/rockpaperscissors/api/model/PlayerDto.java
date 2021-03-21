package se.cygni.rockpaperscissors.api.model;

import lombok.Data;
import se.cygni.rockpaperscissors.application.model.Player;

@Data
public class PlayerDto {
    private String name;

    public PlayerDto() {

    }

    /**
     * Translate into a player from the domain.
     *
     * @return the player
     */
    public Player toDomain() {
        return new Player(name);
    }
}
