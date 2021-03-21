package se.cygni.rockpaperscissors.api.model;

import lombok.Data;

import java.util.UUID;

@Data
public class GameDto {
    private String game_id;

    public GameDto(UUID id) {
        game_id = id.toString();
    }
}
