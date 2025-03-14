package org.chess.web.dto;

public record MoveRequestDto(String from, String to, String currentPosition, String currentTurn) {
}
