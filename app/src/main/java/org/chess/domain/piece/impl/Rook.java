package org.chess.domain.piece.impl;

import org.chess.domain.board.Board;
import org.chess.domain.board.Position;
import org.chess.domain.piece.Color;
import org.chess.domain.piece.Piece;

public class Rook implements Piece {

    private final Color color;

    public Rook(Color color) {
        this.color = color;
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        if (from.deltaX(to) != 0 && from.deltaY(to) != 0) {
            return false;
        }

        return isPathClear(board, from, to);
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
        return 5.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Rook rook = (Rook) obj;
        return color == rook.color;
    }

    @Override
    public int hashCode() {
        return color.hashCode();
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "r" : "R";
    }

}
