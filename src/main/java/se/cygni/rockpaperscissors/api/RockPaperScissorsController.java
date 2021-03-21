package se.cygni.rockpaperscissors.api;


import org.springframework.web.bind.annotation.*;
import se.cygni.rockpaperscissors.api.model.GameDto;
import se.cygni.rockpaperscissors.api.model.MoveDto;
import se.cygni.rockpaperscissors.api.model.PlayerDto;
import se.cygni.rockpaperscissors.api.model.StatusDto;
import se.cygni.rockpaperscissors.application.model.Game;
import se.cygni.rockpaperscissors.application.model.GameMove;
import se.cygni.rockpaperscissors.application.model.Move;
import se.cygni.rockpaperscissors.application.services.GameService;

import java.util.UUID;

@RestController
public class RockPaperScissorsController {
    private GameService gameService;

    public RockPaperScissorsController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(path = "/games/{id}" ,produces = "application/json")
    public StatusDto getGame(@PathVariable("id") String id) {
        Game game = gameService.getGame(UUID.fromString(id));
        return new StatusDto(game);
    }

    @PostMapping(path = "/games", consumes = "application/json", produces = "application/json")
    public GameDto postGame() {
        UUID id = gameService.createGame();
        return new GameDto(id);
    }

    @PostMapping(path = "/games/{id}/join", consumes = "application/json", produces = "application/json")
    public void postJoin(@PathVariable("id") String id, @RequestBody PlayerDto playerDto) {
        Game game = gameService.getGame(UUID.fromString(id));
        game.addGameMove(new GameMove(Move.NO_MOVE, playerDto.toDomain()));
    }

    @PostMapping(path = "/games/{id}/move", consumes = "application/json", produces = "application/json")
    public void postMove(@PathVariable("id") String id, @RequestBody MoveDto moveDto) {
        Game game = gameService.getGame(UUID.fromString(id));
        GameMove move = moveDto.toDomain();
        game.getGameMove(move.getPlayer()).setMove(move.getMove());
    }
}