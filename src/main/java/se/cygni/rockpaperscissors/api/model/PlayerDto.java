package se.cygni.rockpaperscissors.api.model;

import lombok.Data;
import se.cygni.rockpaperscissors.application.model.Player;

@Data
public class PlayerDto {
    private String name;

    public PlayerDto() {

    }

    public Player toDomain() {
        return new Player(name);
    }
}
