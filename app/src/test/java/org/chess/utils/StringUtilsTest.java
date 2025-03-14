package org.chess.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

    @Test
    @DisplayName("문자열을 주면 개행 문자를 추가한 문자열을 반환한다.")
    void 문자열_개행문자_추가_테스트() {
        // given
        String hello = "hello";
        String expected = "hello" + StringUtils.NEWLINE;

        // when
        String actual = StringUtils.appendNewLine(hello);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("move 문자열을 주면 이동할 위치를 반환한다.")
    void move_이동할_위치_반환_테스트() {
        // given
        String move = "move e2 e4";
        String[] expected = {"e2", "e4"};

        // when
        String[] actual = StringUtils.getMovePositions(move);

        // then
        Assertions.assertThat(actual).containsExactly(expected);
    }

}
