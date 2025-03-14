package org.chess.domain.piece.provider;

import org.chess.domain.board.Board;
import org.chess.domain.board.Position;
import org.chess.domain.piece.Color;
import org.chess.domain.piece.Piece;
import org.chess.domain.piece.PieceFactory;

import java.lang.reflect.Field;
import java.util.Map;

public class TestBoardProvider {

    /**
     * @return 모든 좌표가 BlankPiece로 채워진 Board 인스턴스
     * @brief 리플렉션을 사용해 Board의 내부 board 맵을 직접 조작하여,
     * 모든 좌표에 BLANK 로 채운 빈 보드를 생성한다.
     */
    @SuppressWarnings("unchecked")
    public static Board createEmptyBoard() {
        Board board = Board.create();
        try {
            Field boardField = Board.class.getDeclaredField("board");
            boardField.setAccessible(true);
            Map<Position, Piece> boardMap = (Map<Position, Piece>) boardField.get(board);
            boardMap.clear(); // 기본 배치 제거
            for (int y = 0; y < 8; y++) {
                for (int x = 0; x < 8; x++) {
                    boardMap.put(Position.of(y, x), PieceFactory.BLANK.create(Color.NOCOLOR));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("빈 보드 생성 실패", e);
        }
        return board;
    }

    /**
     * @param board 기물을 배치할 Board 인스턴스
     * @param pos   기물을 배치할 좌표
     * @param piece 배치할 기물 인스턴스
     * @brief 리플렉션을 사용해 보드 내부의 board 맵에서 지정한 좌표에 기물을 강제로 배치한다.
     */
    @SuppressWarnings("unchecked")
    public static void setPiece(Board board, Position pos, Piece piece) {
        try {
            Field boardField = Board.class.getDeclaredField("board");
            boardField.setAccessible(true);
            Map<Position, Piece> boardMap = (Map<Position, Piece>) boardField.get(board);
            boardMap.put(pos, piece);
        } catch (Exception e) {
            throw new RuntimeException("기물 배치 실패", e);
        }
    }

}
