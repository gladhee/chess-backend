package org.chess.domain.board;

import java.util.Objects;

public class Position {

    private static final int BOARD_SIZE = 8;
    private final int y;
    private final int x;

    private Position(int y, int x) {
        if (y < 0 || y >= BOARD_SIZE || x < 0 || x >= BOARD_SIZE) {
            throw new IllegalArgumentException("유효하지 않은 좌표: (" + y + ", " + x + ")");
        }

        this.y = y;
        this.x = x;
    }

    public static Position of(String pos) {
        if (pos == null || pos.length() != 2) {
            throw new IllegalArgumentException("잘못된 좌표 문자열: " + pos);
        }

        int x = pos.charAt(0) - 'a';
        int rank = pos.charAt(1) - '0';
        int y = BOARD_SIZE - rank;

        return new Position(y, x);
    }

    public static Position of(int y, int x) {
        return new Position(y, x);
    }

    public int deltaX(Position other) {
        return Math.abs(this.x - other.x);
    }

    public int deltaY(Position other) {
        return Math.abs(this.y - other.y);
    }

    public int diffY(Position other) {
        return other.y - this.y;
    }

    /**
    * @brief 현재 위치에서 target 위치로 이동하기 위한 다음 위치를 반환합니다.
    *
    * @param target 이동할 위치
    * @return target 위치로 이동하기 위한 다음 위치
    * */
    public Position nextStepTowards(Position target) {
        int dx = Integer.compare(target.x, this.x);
        int dy = Integer.compare(target.y, this.y);

        return Position.of(this.y + dy, this.x + dx);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return y == position.y && x == position.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

    @Override
    public String toString() {
        char file = (char) ('a' + x);
        char rank = (char) ('8' - y);
        return "" + file + rank;
    }

}
