package se.cygni.rockpaperscissors.application.model;

import lombok.Data;

import java.util.Optional;

@Data
public class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }


}
