package org.chess.domain.piece;

import org.chess.domain.board.Board;
import org.chess.domain.board.Position;

public interface Piece {

    boolean isValidMove(Board board, Position from, Position to);

    boolean isSameTeam(Piece other);

    boolean belongsTo(Color color);

    double getScore();

}
