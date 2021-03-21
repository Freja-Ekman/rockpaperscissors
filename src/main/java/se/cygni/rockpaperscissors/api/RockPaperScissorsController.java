package se.cygni.rockpaperscissors.api;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import se.cygni.rockpaperscissors.api.model.GameDto;
import se.cygni.rockpaperscissors.api.model.MoveDto;
import se.cygni.rockpaperscissors.api.model.PlayerDto;
import se.cygni.rockpaperscissors.api.model.StatusDto;
import se.cygni.rockpaperscissors.application.model.Game;
import se.cygni.rockpaperscissors.application.model.GameMove;
import se.cygni.rockpaperscissors.application.model.Move;
import se.cygni.rockpaperscissors.application.model.Player;
import se.cygni.rockpaperscissors.application.services.GameService;

import java.util.UUID;

@Component
public class RockPaperScissorsHandler {
    private GameService gameService;

    public RockPaperScissorsHandler(GameService gameService) {
        this.gameService = gameService;
    }

    public Mono<ServerResponse> getGame(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Game> game = gameService.getGame(UUID.fromString(id));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(game.map(it -> new StatusDto(it)), StatusDto.class);
    }

    public Mono<ServerResponse> postGame(ServerRequest request) {
        Mono<UUID> id = gameService.createGame();
        return JsonWriter.write(id.map(it -> new GameDto(it))).flatMap((json) -> ServerResponse.ok()
                .body(Mono.just(json), String.class));
    }

    public Mono<ServerResponse> postJoin(ServerRequest request) {
        String id = request.pathVariable("id");
        request.bodyToMono(PlayerDto.class).map(PlayerDto::toDomain)
                .zipWith(gameService.getGame(UUID.fromString(id)))
                .doOnNext(tuple -> tuple.getT2().addGameMove(new GameMove(Move.NO_MOVE, tuple.getT1()))).block();
        return ServerResponse.noContent().build();
    }

    public Mono<ServerResponse> postMove(ServerRequest request) {
        String id = request.pathVariable("id");
        request.bodyToMono(MoveDto.class).map(MoveDto::toDomain)
                .zipWith(gameService.getGame(UUID.fromString(id)))
                .doOnNext(tuple -> tuple.getT2().getGameMove(tuple.getT1().getPlayer()).setMove(tuple.getT1().getMove())).block();
        return ServerResponse.noContent().build();
    }
}