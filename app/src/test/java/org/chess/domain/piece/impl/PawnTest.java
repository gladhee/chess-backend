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

class PawnTest {

    @ParameterizedTest
    @CsvSource({
            "a2, a3",
            "b2, b3",
            "d2, d3"
    })
    @DisplayName("화이트 폰이 한 칸 전진 가능해야한다")
    void 화이트_폰_한_칸_전진_테스트(String fromStr, String toStr) {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece pawn = PieceFactory.PAWN.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, pawn);

        // when
        boolean isValidMove = pawn.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "a2, a4",
            "b2, b4",
            "d2, d4"
    })
    @DisplayName("화이트 폰이 초기 위치에서 두 칸 전진 가능")
    void 화이트_폰_두_칸_전진_테스트(String fromStr, String toStr) {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece pawn = PieceFactory.PAWN.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, pawn);

        // when
        boolean isValidMove = pawn.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isTrue();
    }

    @Test
    @DisplayName("화이트 폰이 두 칸 전진 시 중간 칸에 장애물이 있으면 이동 불가")
    void 화이트_폰_두_칸_전진시_중간에_장애물_이동_불가_테스트() {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of("a2");
        Position intermediate = Position.of("a3");
        Position to = Position.of("a4");
        Piece whitePawn = PieceFactory.PAWN.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, whitePawn);
        Piece blocker = PieceFactory.PAWN.create(Color.WHITE);
        TestBoardProvider.setPiece(board, intermediate, blocker);

        // when
        boolean isValidMove = whitePawn.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isFalse();
    }

    @ParameterizedTest
    @CsvSource({
            "a2, b3",
            "d2, c3"
    })
    @DisplayName("화이트 폰이 대각선으로 적을 잡을 수 있어야 한다")
    void 화이트_폰_대각선_캡처_테스트(String fromStr, String toStr) {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece whitePawn = PieceFactory.PAWN.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, whitePawn);
        Piece blackPawn = PieceFactory.PAWN.create(Color.BLACK);
        TestBoardProvider.setPiece(board, to, blackPawn);

        // when
        boolean isValidMove = whitePawn.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "a7, a6",
            "c7, c6",
            "e7, e6"
    })
    @DisplayName("검정 폰이 한 칸 전진 가능해야한다")
    void 검정_폰_한_칸_전진_테스트(String fromStr, String toStr) {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece blackPawn = PieceFactory.PAWN.create(Color.BLACK);
        TestBoardProvider.setPiece(board, from, blackPawn);

        // when
        boolean isValidMove = blackPawn.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "a7, a5",
            "c7, c5",
            "e7, e5"
    })
    @DisplayName("검정 폰이 초기 위치에서 두 칸 전진 가능")
    void 검정_폰_두_칸_전진_테스트(String fromStr, String toStr) {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece blackPawn = PieceFactory.PAWN.create(Color.BLACK);
        TestBoardProvider.setPiece(board, from, blackPawn);

        // when
        boolean isValidMove = blackPawn.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "a7, b6",
            "d7, c6"
    })
    @DisplayName("검정 폰이 대각선으로 적을 잡을 수 있어야 한다")
    void 검정_폰_대각선_캡처_테스트(String fromStr, String toStr) {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece blackPawn = PieceFactory.PAWN.create(Color.BLACK);
        TestBoardProvider.setPiece(board, from, blackPawn);
        Piece whitePawn = PieceFactory.PAWN.create(Color.WHITE);
        TestBoardProvider.setPiece(board, to, whitePawn);

        // when
        boolean isValidMove = blackPawn.isValidMove(board, from, to);

        // then
        assertThat(isValidMove).isTrue();
    }

}
