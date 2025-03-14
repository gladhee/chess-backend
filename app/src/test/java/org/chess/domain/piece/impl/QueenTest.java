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

class QueenTest {

    @ParameterizedTest
    @CsvSource({
            "d1, d4",
            "d1, a1"
    })
    @DisplayName("퀸이 수평/수직 방향으로 이동 가능")
    void 수직_수평_이동_테스트(String fromStr, String toStr) {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece queen = PieceFactory.QUEEN.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, queen);

        // when
        boolean isValidMove = queen.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "d1, f3",
            "d1, b3"
    })
    @DisplayName("퀸이 대각선 방향으로 이동 가능")
    void 대각선_이동_테스트(String fromStr, String toStr) {
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece queen = PieceFactory.QUEEN.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, queen);

        // when
        boolean isValidMove = queen.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isTrue();
    }

    @Test
    @DisplayName("퀸 이동 경로에 장애물이 있으면 이동 불가")
    void 이동_경로에_장애물_이동_불가_테스트() {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of("d1");
        Position to = Position.of("d4");
        Piece queen = PieceFactory.QUEEN.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, queen);
        // d1과 d4 사이인 d2에 장애물 배치
        Piece blocker = PieceFactory.PAWN.create(Color.WHITE);
        TestBoardProvider.setPiece(board, Position.of("d2"), blocker);

        // when
        boolean isValidMove = queen.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isFalse();
    }

}
