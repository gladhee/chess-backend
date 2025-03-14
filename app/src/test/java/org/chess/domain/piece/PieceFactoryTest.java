package org.chess.domain.piece;

import org.chess.domain.piece.impl.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceFactoryTest {

    @Test
    @DisplayName("객체가 올바르게 생성이 되어야 한다")
    void 객체_생성_테스트() {
        Piece piece = PieceFactory.ROOK.create(Color.WHITE);

        assertThat(piece).isInstanceOf(Rook.class);
    }

}
