package com.chess.Chess.server;

public enum OperationType {
    LOGIN,
    REGISTER_USER;

    @Override
    public String toString() {
        return this.name();
    }
}
