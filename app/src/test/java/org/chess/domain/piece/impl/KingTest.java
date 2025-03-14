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

class KingTest {

    @ParameterizedTest
    @CsvSource({
            "e1, e2",
            "e1, d1",
            "e1, f1",
            "e1, d2",
            "e1, f2"
    })
    @DisplayName("킹이 한 칸 이동 가능")
    void 한_칸_이동_테스트(String fromStr, String toStr) {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece king = PieceFactory.KING.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, king);

        // when
        boolean isValidMove = king.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isTrue();
    }

    @Test
    @DisplayName("킹이 두 칸 이상 이동하면 이동 불가")
    void 유효하지_않는_이동_테스트() {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of("e1");
        Position to = Position.of("e3");
        Piece king = PieceFactory.KING.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, king);

        // when
        boolean isValidMove = king.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isFalse();
    }

}
