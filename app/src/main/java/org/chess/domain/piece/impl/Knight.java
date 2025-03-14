package org.chess.domain.piece.impl;

import org.chess.domain.board.Board;
import org.chess.domain.board.Position;
import org.chess.domain.piece.Color;
import org.chess.domain.piece.Piece;

public class Knight implements Piece {

    private final Color color;

    public Knight(Color color) {
        this.color = color;
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        int dx = from.deltaX(to);
        int dy = from.deltaY(to);

        return (dx == 1 && dy == 2) || (dx == 2 && dy == 1);
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
        return 2.5;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Knight knight = (Knight) obj;
        return color == knight.color;
    }

    @Override
    public int hashCode() {
        return color.hashCode();
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "n" : "N";
    }

}
