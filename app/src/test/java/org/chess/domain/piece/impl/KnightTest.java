package org.chess.domain.piece.impl;

import org.chess.domain.board.Board;
import org.chess.domain.board.Position;
import org.chess.domain.piece.Color;
import org.chess.domain.piece.Piece;
import org.chess.domain.piece.PieceFactory;
import org.chess.domain.piece.provider.TestBoardProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @ParameterizedTest
    @CsvSource({
            "b1, c3",
            "b1, a3",
            "g1, f3",
            "g1, h3"
    })
    @DisplayName("나이트가 이동할 수 있는 위치로 이동하려고 하면 이동 가능")
    void 나이트_이동_테스트(String fromStr, String toStr) {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece knight = PieceFactory.KNIGHT.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, knight);

        // when
        boolean isValidMove = knight.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "b1, b3",
            "g1, g3"
    })
    @DisplayName("나이트가 이동할 수 없는 위치로 이동하려고 하면 이동 불가")
    void 나이트_이동_불가_테스트(String fromStr, String toStr) {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece knight = PieceFactory.KNIGHT.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, knight);

        // when
        boolean isValidMove = knight.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isFalse();
    }

}
