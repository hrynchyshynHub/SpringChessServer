package com.chess.Chess.server;

public interface RequestManager {
    RequestHandler handleRequest(OperationType type);
}
