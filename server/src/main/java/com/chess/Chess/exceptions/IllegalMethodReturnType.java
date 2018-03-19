package com.chess.Chess.exceptions;

/**
 * Trowed when OperationHandler method return type
 * is't Response subclass
 */
public class IllegalMethodReturnType extends RuntimeException {
    public IllegalMethodReturnType(String message) {
        super(message);
    }
}
