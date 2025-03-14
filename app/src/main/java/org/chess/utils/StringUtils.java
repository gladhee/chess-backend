package org.chess.utils;

public class StringUtils {

    public static final String NEWLINE = System.lineSeparator();

    private static final int BEGIN_INDEX = 5;

    private StringUtils() {
    }

    public static String appendNewLine(String str) {
        return str + NEWLINE;
    }

    public static String[] getMovePositions(String move) {
        return move.substring(BEGIN_INDEX).split(" ");
    }

}
