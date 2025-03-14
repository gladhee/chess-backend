package org.chess.domain.board;

import org.chess.domain.piece.Color;
import org.chess.domain.piece.Piece;
import org.chess.domain.piece.PieceFactory;
import org.chess.domain.piece.impl.Blank;
import org.chess.domain.piece.provider.TestBoardProvider;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.chess.utils.StringUtils.appendNewLine;

class BoardTest {

    @Test
    @DisplayName("보드 initailize 후 모든 기물이 생성되어야 한다")
    void initializeAllPieces() {
        // given & when
        Board board = Board.create();

        // then
        String blankRank = appendNewLine("........");

        assertThat(appendNewLine("RNBQKBNR") +
                appendNewLine("PPPPPPPP") +
                blankRank + blankRank + blankRank + blankRank +
                appendNewLine("pppppppp") +
                appendNewLine("rnbqkbnr"))
                .isEqualTo(board.toString());
    }

    @Test
    @DisplayName("임의의 주어진 위치의 기물을 조회할 수 있어야 한다")
    void 기물_조회_테스트() {
        // given & when
        Board board = Board.create();

        // then
        assertThat(PieceFactory.ROOK.create(Color.BLACK))
                .isEqualTo(board.getPiece(Position.of("h8")));
        assertThat(PieceFactory.ROOK.create(Color.WHITE))
                .isEqualTo(board.getPiece(Position.of("h1")));
        assertThat(PieceFactory.ROOK.create(Color.BLACK))
                .isEqualTo(board.getPiece(Position.of("a8")));
        assertThat(PieceFactory.ROOK.create(Color.WHITE))
                .isEqualTo(board.getPiece(Position.of("a1")));
    }

    @Test
    @DisplayName("movePiece()를 통해 기물이 올바르게 이동해야 한다")
    void 이동_테스트() {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of("a2");
        Position to = Position.of("a3");
        Piece whitePawn = PieceFactory.PAWN.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, whitePawn);

        // then
        assertThat(board.getPiece(from)).isEqualTo(whitePawn);
        assertThat(board.isOccupied(to)).isFalse();

        // when
        board.movePiece(from, to);

        // then
        assertThat(board.getPiece(to)).isEqualTo(PieceFactory.PAWN.create(Color.WHITE));
        assertThat(board.getPiece(from)).isInstanceOf(Blank.class);
    }

    @Test
    @DisplayName("자신의 기물이 있는 위치로 이동하면 예외가 발생해야 한다")
    void 동일_팀_기물_이동_예외_테스트() {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of("a2");
        Position to = Position.of("a3");
        Piece whitePawn1 = PieceFactory.PAWN.create(Color.WHITE);
        Piece whitePawn2 = PieceFactory.PAWN.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, whitePawn1);
        TestBoardProvider.setPiece(board, to, whitePawn2);

        // when & then
        assertThatThrownBy(() -> board.movePiece(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("기물이 " + from + "에서 " + to + "으로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("출발 위치에 기물이 없으면 이동 시 예외가 발생해야 한다")
    void 빈_출발지_이동_예외_테스트() {
        // given
        Board board = Board.create();
        Position from = Position.of("e4");
        Position to = Position.of("e5");

        // when & then
        assertThatThrownBy(() -> board.movePiece(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("기물이 " + from + "에서 " + to + "으로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("화이트 기물 점수 합산 테스트")
    void 흰색_점수_합산_테스트() {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        TestBoardProvider.setPiece(board, Position.of("a2"), PieceFactory.PAWN.create(Color.WHITE));
        TestBoardProvider.setPiece(board, Position.of("b2"), PieceFactory.KNIGHT.create(Color.WHITE));
        TestBoardProvider.setPiece(board, Position.of("c2"), PieceFactory.BISHOP.create(Color.WHITE));
        TestBoardProvider.setPiece(board, Position.of("d2"), PieceFactory.ROOK.create(Color.WHITE));
        TestBoardProvider.setPiece(board, Position.of("e2"), PieceFactory.QUEEN.create(Color.WHITE));
        TestBoardProvider.setPiece(board, Position.of("f2"), PieceFactory.KING.create(Color.WHITE));

        // Expected total score: 1.0 + 2.5 + 3.0 + 5.0 + 9.0 + 0.0 = 20.5
        double expectedScore = 20.5;

        // when
        double actualScore = board.calculateScore(Color.WHITE);

        // then
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("검정 기물 점수 합산 테스트")
    void 검은색_점수_합산_테스트() {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        // 배치 예시:
        TestBoardProvider.setPiece(board, Position.of("a7"), PieceFactory.PAWN.create(Color.BLACK));
        TestBoardProvider.setPiece(board, Position.of("b7"), PieceFactory.KNIGHT.create(Color.BLACK));
        TestBoardProvider.setPiece(board, Position.of("c7"), PieceFactory.BISHOP.create(Color.BLACK));
        TestBoardProvider.setPiece(board, Position.of("d7"), PieceFactory.ROOK.create(Color.BLACK));
        TestBoardProvider.setPiece(board, Position.of("e7"), PieceFactory.QUEEN.create(Color.BLACK));
        TestBoardProvider.setPiece(board, Position.of("f7"), PieceFactory.KING.create(Color.BLACK));

        // Expected total score: 1.0 + 2.5 + 3.0 + 5.0 + 9.0 + 0.0 = 20.5
        double expectedScore = 20.5;

        // when
        double actualScore = board.calculateScore(Color.BLACK);

        // then
        assertThat(actualScore).isEqualTo(expectedScore);
    }

}
