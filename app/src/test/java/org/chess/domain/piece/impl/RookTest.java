package org.chess.domain.piece.impl;

import org.chess.domain.board.Board;
import org.chess.domain.board.Position;
import org.chess.domain.piece.Color;
import org.chess.domain.piece.Piece;
import org.chess.domain.piece.PieceFactory;
import org.chess.domain.piece.provider.TestBoardProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @ParameterizedTest
    @CsvSource({
            "a1, a4",
            "a1, d1"
    })
    @DisplayName("룩이 수평/수직 방향으로 이동 가능")
    void 수직_수평_이동_테스트(String fromStr, String toStr) {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece rook = PieceFactory.ROOK.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, rook);

        // when
        boolean isValidMove = rook.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isTrue();
    }

    @Test
    @DisplayName("룩 이동 경로에 장애물이 있으면 이동 불가")
    void 이동_경로_장애물_테스트() {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of("a1");
        Position to = Position.of("a4");
        Piece rook = PieceFactory.ROOK.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, rook);
        // a1과 a4 사이인 a2에 장애물 배치
        Piece blocker = PieceFactory.PAWN.create(Color.BLACK);
        TestBoardProvider.setPiece(board, Position.of("a2"), blocker);

        // when
        boolean isValidMove = rook.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isFalse();
    }

}
