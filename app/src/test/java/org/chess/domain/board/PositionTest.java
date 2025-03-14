package org.chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {

    @Test
    @DisplayName("좌표가 같은 Position 객체는 equals() 메소드 결과가 true여야 한다")
    void 동등성_비교_테스트() {
        // given
        Position pos1 = Position.of(0, 0);
        Position pos2 = Position.of(0, 0);

        // when
        boolean actual = pos1.equals(pos2);

        // then
        assertThat(actual).isTrue();
    }

    // nextStepTowards() 테스트
    @Test
    @DisplayName("현재 위치에서 target 위치로 이동하기 위한 다음 위치를 반환한다.")
    void target_위치로_이동하기_위한_다음_위치_반환_테스트() {
        // given
        Position from = Position.of(0, 0);
        Position to = Position.of(2, 2);

        // when
        Position nextStep = from.nextStepTowards(to);
        Position actual = nextStep.nextStepTowards(to);

        // then
        assertThat(nextStep).isEqualTo(Position.of(1, 1));
        assertThat(actual).isEqualTo(to);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a8", "h1", "d5", "e4"})
    @DisplayName("체스 표기법 문자열을 올바른 내부 좌표로 변환한다.")
    void 내부_좌표_변환_테스트(String posStr) {
        // when
        Position pos = Position.of(posStr);

        // then
        assertThat(pos.toString()).isEqualTo(posStr);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a9", "z1", "a"})
    @DisplayName("체스 표기법이 올바르지 않은 경우 예외를 던져야 한다")
    void 내부_좌표_변환_예외_테스트(String posStr) {
        assertThatThrownBy(() -> Position.of(posStr)) // when
                .isInstanceOf(IllegalArgumentException.class); // then

        assertThatThrownBy(() -> Position.of(posStr)) // when
                .isInstanceOf(IllegalArgumentException.class); // then

        assertThatThrownBy(() -> Position.of(posStr)) // when
                .isInstanceOf(IllegalArgumentException.class); // then
    }

}
