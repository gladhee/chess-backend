package org.chess.domain.piece;

import org.chess.domain.piece.impl.*;

public enum PieceFactory {

    KING {
        @Override
        public Piece create(Color color) {
            return new King(color);
        }
    },
    PAWN {
        @Override
        public Piece create(Color color) {
            return new Pawn(color);
        }
    },
    ROOK {
        @Override
        public Piece create(Color color) {
            return new Rook(color);
        }
    },
    KNIGHT {
        @Override
        public Piece create(Color color) {
            return new Knight(color);
        }
    },
    BISHOP {
        @Override
        public Piece create(Color color) {
            return new Bishop(color);
        }
    },
    QUEEN {
        @Override
        public Piece create(Color color) {
            return new Queen(color);
        }
    },
    BLANK {
        @Override
        public Piece create(Color color) {
            return new Blank(color);
        }
    };

    public abstract Piece create(Color color);

}
