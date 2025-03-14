package org.chess.domain.game;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.chess.domain.board.Board;
import org.chess.domain.board.Position;
import org.chess.domain.piece.Color;
import org.chess.web.dto.MoveResultDto;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public class Game {

    private final Board board;
    private Color currentTurn;

    private Game(Board board) {
        this.board = board;
        currentTurn = Color.WHITE;
    }

    public static Game newGame(Board board) {
        return new Game(board);
    }

    /**
     * @param from 출발 위치 (예: "a2")
     * @param to   도착 위치 (예: "a3")
     * @throws IllegalArgumentException 이동하려는 기물이 현재 턴의 기물이 아닐 때 및 이동이 불가능할 때
     * @brief 주어진 from, to 체스 표기법 문자열에 따라 기물을 이동시킨다.
     */
    public MoveResultDto move(Position from, Position to) {
        try {
            isMyTurn(from);
            board.movePiece(from, to);
            toggleTurn();

            return MoveResultDto.of(from, to, currentTurn.toString(), true, board.toString(), board.calculateScore(Color.WHITE), board.calculateScore(Color.BLACK), board.isKingDead(currentTurn));
        } catch (IllegalArgumentException e) {
            log.error("Failed to move piece: {}", e.getMessage());
            return MoveResultDto.of(from, to, currentTurn.toString(), false, board.toString(), board.calculateScore(Color.WHITE), board.calculateScore(Color.BLACK), board.isKingDead(currentTurn));
        }
    }

    private void isMyTurn(Position from) {
        if (!board.getPiece(from).belongsTo(currentTurn)) {
            throw new IllegalArgumentException("현재 턴(" + currentTurn + ")의 기물이 아닙니다.");
        }
    }

    private void toggleTurn() {
        currentTurn = (currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

}
