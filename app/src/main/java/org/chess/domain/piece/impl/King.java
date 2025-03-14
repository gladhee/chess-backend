package org.chess.domain.piece.impl;

import org.chess.domain.board.Board;
import org.chess.domain.board.Position;
import org.chess.domain.piece.Color;
import org.chess.domain.piece.Piece;

public class King implements Piece {

    private final Color color;

    public King(Color color) {
        this.color = color;
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        return from.deltaX(to) <= 1 && from.deltaY(to) <= 1;
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
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        King king = (King) obj;
        return color == king.color;
    }

    @Override
    public int hashCode() {
        return color.hashCode();
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "k" : "K";
    }

}
