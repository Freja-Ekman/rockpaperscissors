package se.cygni.rockpaperscissors.api;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.cygni.rockpaperscissors.api.model.GameDto;
import se.cygni.rockpaperscissors.api.model.MoveDto;
import se.cygni.rockpaperscissors.api.model.PlayerDto;
import se.cygni.rockpaperscissors.api.model.StatusDto;
import se.cygni.rockpaperscissors.application.model.Game;
import se.cygni.rockpaperscissors.application.model.GameMove;
import se.cygni.rockpaperscissors.application.model.Move;
import se.cygni.rockpaperscissors.application.services.GameService;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
public class RockPaperScissorsController {
    private GameService gameService;

    public RockPaperScissorsController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Gets the status dto of a specified game.
     *
     * @param id the id of the specified game
     * @return the game status
     * @throws ResponseStatusException if game does not exist or the id has the wrong format
     */
    @GetMapping(path = "/games/{id}" ,produces = "application/json")
    public StatusDto getGame(@PathVariable("id") String id) {
        try {
            Game game = gameService.getGame(UUID.fromString(id));
            if(game == null)
                throw new ResponseStatusException(NOT_FOUND, "Game does not exist");
            return new StatusDto(game);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(BAD_REQUEST, "Wrong id format");
        }
    }

    /**
     * Post a game dto.
     *
     * @return the game dto
     */
    @PostMapping(path = "/games", consumes = "application/json", produces = "application/json")
    public GameDto postGame() {
        UUID id = gameService.createGame();
        return new GameDto(id);
    }

    /**
     * Post a player that joins a specific game.
     *
     * @param id        the specific game id
     * @param playerDto the player dto
     * @throws ResponseStatusException if game does not exist, the id has the wrong format or the game already contains
     * two players
     */
    @PostMapping(path = "/games/{id}/join", consumes = "application/json", produces = "application/json")
    public void postJoin(@PathVariable("id") String id, @RequestBody PlayerDto playerDto) {
        try {
            Game game = gameService.getGame(UUID.fromString(id));
            if(game == null)
                throw new ResponseStatusException(NOT_FOUND, "Game does not exist");
            game.addGameMove(new GameMove(Move.NO_MOVE, playerDto.toDomain()));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(BAD_REQUEST, "Wrong id format");
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(PRECONDITION_FAILED, "Too many players");
        }
    }

    /**
     * Post a move to a specific game of a player.
     *
     * @param id      the id of the specific game
     * @param moveDto the move dto
     * @throws ResponseStatusException if game does not exist, the id has the wrong format, the player does not
     * exist, the move id set to HIDDEN or the player already have made a move
     */
    @PostMapping(path = "/games/{id}/move", consumes = "application/json", produces = "application/json")
    public void postMove(@PathVariable("id") String id, @RequestBody MoveDto moveDto) {
        try {
            Game game = gameService.getGame(UUID.fromString(id));
            if(game == null)
                throw new ResponseStatusException(NOT_FOUND, "Game does not exist");
            GameMove move = moveDto.toDomain();
            try {
                game.getGameMove(move.getPlayer()).setMove(move.getMove());
            } catch (IllegalStateException e) {
                throw new ResponseStatusException(NOT_FOUND, "Player does not exist");
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(BAD_REQUEST, "Can not make move hidden");
            }
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(BAD_REQUEST, "Wrong id format");
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(PRECONDITION_FAILED, "Already moved");
        }

    }
}