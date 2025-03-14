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

class BlankTest {

    @ParameterizedTest
    @CsvSource({
            "c1, a3",
            "c1, e3"
    })
    @DisplayName("항상 유효하지 않는 움직임 반환 테스트")
    void 반드시_실패_테스트(String fromStr, String toStr) {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece blank = PieceFactory.BLANK.create(Color.NOCOLOR);
        TestBoardProvider.setPiece(board, from, blank);

        // when
        boolean isValidMove = blank.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isFalse();
    }

}
