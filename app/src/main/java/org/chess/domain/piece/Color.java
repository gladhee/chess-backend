package org.chess.domain.piece;

public enum Color {

    WHITE("white"),
    BLACK("black"),
    NOCOLOR("null");

    private final String color;

    Color(String color) {
        this.color = color;
    }

}
