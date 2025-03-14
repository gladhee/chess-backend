package org.chess.domain.piece.impl;

import org.chess.domain.board.Board;
import org.chess.domain.board.Position;
import org.chess.domain.piece.Color;
import org.chess.domain.piece.Piece;

public class Queen implements Piece {

    private final Color color;

    public Queen(Color color) {
        this.color = color;
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        int dx = from.deltaX(to);
        int dy = from.deltaY(to);
        // 수평, 수직 이동
        if (dx == 0 || dy == 0) {
            return isPathClear(board, from, to);
        }
        // 대각선 이동
        if (dx == dy) {
            return isPathClear(board, from, to);
        }

        return false;
    }

    private boolean isPathClear(Board board, Position from, Position to) {
        Position current = from.nextStepTowards(to);
        while (!current.equals(to)) {
            if (board.isOccupied(current)) return false;
            current = current.nextStepTowards(to);
        }

        return true;
    }

    @Override
    public boolean isSameTeam(Piece other) {
        return other.belongsTo(color);
    }

    @Override
    public boolean belongsTo(Color color) {
        return this.color == color;
    }

    @Override
    public double getScore() {
        return 9.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Queen queen = (Queen) obj;
        return color == queen.color;
    }

    @Override
    public int hashCode() {
        return color.hashCode();
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "q" : "Q";
    }

}
