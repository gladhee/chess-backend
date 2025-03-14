package org.chess.web;

import lombok.extern.slf4j.Slf4j;
import org.chess.domain.board.Board;
import org.chess.domain.board.Position;
import org.chess.domain.game.Game;
import org.chess.web.dto.MoveRequestDto;
import org.chess.web.dto.MoveResultDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/chess")
@CrossOrigin(origins = "https://chess-frontend-dusky.vercel.app", allowCredentials = "true")
public class ChessController {

    private Game game;

    public ChessController() {
        this.game = Game.newGame(Board.create());
    }

    @PostMapping("")
    public ResponseEntity<Void> createGame() {
        log.info("Creating new game");
        game = Game.newGame(Board.create());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/move")
    public MoveResultDto move(@RequestBody MoveRequestDto request) {
        log.info("Move request: from={}, to={}", request.from(), request.to());

        Position from = Position.of(request.from());
        Position to = Position.of(request.to());

        return game.move(from, to);
    }

    @GetMapping("/status")
    public String status() {
        return "Status";
    }

    @GetMapping("/score")
    public String score() {
        return "Score";
    }
}
