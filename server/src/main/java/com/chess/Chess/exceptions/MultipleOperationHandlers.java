package com.chess.Chess.exceptions;

/**
 * Trowed when collected more that one OperationHandler
 * for OperationType
 */
public class MultipleOperationHandlers extends RuntimeException {
    public MultipleOperationHandlers(String message) {
        super(message);
    }
}
