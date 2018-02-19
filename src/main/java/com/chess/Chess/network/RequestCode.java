package com.chess.Chess.network;

import java.io.Serializable;

public enum RequestCode implements Serializable {
    OK,
    ERROR,
    CONNECTION_ERROR;

    @Override
    public String toString() {
        return name();
    }
}