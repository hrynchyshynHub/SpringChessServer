package com.chess.Chess.server;
import network.OperationType;

public interface RequestManager {
    RequestHandler handleRequest(OperationType type);
}
