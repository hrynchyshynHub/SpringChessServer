package com.chess.Chess.exceptions;

/**
 * Trowed when not OperationHandler method found for operation path
 */
public class HandlerNotFoundException extends RuntimeException {
    public HandlerNotFoundException(String message) {
        super(message);
    }
}
