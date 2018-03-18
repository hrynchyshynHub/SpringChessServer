package com.chess.Chess.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@FunctionalInterface
public interface RequestHandler {
    boolean execute(ObjectInputStream ois, ObjectOutputStream oos);
}
