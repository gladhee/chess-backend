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

class BishopTest {

    @ParameterizedTest
    @CsvSource({
            "c1, a3",
            "c1, e3"
    })
    @DisplayName("비숍이 대각선 방향으로 이동 가능")
    void 대각선_이동_테스트(String fromStr, String toStr) {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece bishop = PieceFactory.BISHOP.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, bishop);

        // when
        boolean isValidMove = bishop.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isTrue();
    }

    @Test
    @DisplayName("비숍 이동 경로에 장애물이 있으면 이동 불가")
    void 이동_경로_장애물_테스트() {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of("c1");
        Position to = Position.of("a3");
        Piece bishop = PieceFactory.BISHOP.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, bishop);
        // c1과 a3 사이인 b2에 장애물 배치
        Piece blocker = PieceFactory.PAWN.create(Color.BLACK);
        TestBoardProvider.setPiece(board, Position.of("b2"), blocker);

        // when
        boolean isValidMove = bishop.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isFalse();
    }

}
