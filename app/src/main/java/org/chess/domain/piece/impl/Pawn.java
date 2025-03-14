package org.chess.domain.piece.impl;

import org.chess.domain.board.Board;
import org.chess.domain.board.Position;
import org.chess.domain.piece.Color;
import org.chess.domain.piece.Piece;

public class Pawn implements Piece {

    private final Color color;

    public Pawn(Color color) {
        this.color = color;
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        int dx = from.deltaX(to);
        int dy = from.diffY(to);

        if (this.color == Color.WHITE) {
            if (dx == 0 && dy == -1 && !board.isOccupied(to)) {
                return true;
            }
            if (dx == 0 && dy == -2 && isInitialPosition(from) && !board.isOccupied(to)) {
                Position intermediate = from.nextStepTowards(to);
                if (!board.isOccupied(intermediate)) {
                    return true;
                }
            }
            // 대각선 캡처: 한 칸 대각선 (dx == 1, dy == -1)이고, 상대 기물이 있어야 함
            if (dx == 1 && dy == -1) {
                Piece toPiece = board.getPiece(to);
                if (toPiece instanceof Blank) {
                    return false;
                }

                return toPiece != null && !toPiece.belongsTo(this.color);
            }
        } else {
            if (dx == 0 && dy == 1 && !board.isOccupied(to)) {
                return true;
            }
            if (dx == 0 && dy == 2 && isInitialPosition(from) && !board.isOccupied(to)) {
                Position intermediate = from.nextStepTowards(to);
                if (!board.isOccupied(intermediate)) {
                    return true;
                }
            }
            // 대각선 캡처
            if (dx == 1 && dy == 1) {
                Piece toPiece = board.getPiece(to);
                if (toPiece instanceof Blank) {
                    return false;
                }

                return !toPiece.belongsTo(this.color);
            }
        }

        return false;
    }

    private boolean isInitialPosition(Position from) {
        String posStr = from.toString(); // ex: "a2" or "a7"
        char rank = posStr.charAt(1);
        if (this.color == Color.WHITE) {
            return rank == '2';
        } else {
            return rank == '7';
        }
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
        return 1.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pawn pawn = (Pawn) obj;
        return color == pawn.color;
    }

    @Override
    public int hashCode() {
        return color.hashCode();
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "p" : "P";
    }

}
