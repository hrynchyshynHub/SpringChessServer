package com.chess.Chess.server.requestHandlers_impl;

import com.chess.Chess.server.RequestHandler;
import com.chess.Chess.service.impl.ChessGameEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Component
public class SelectCellRequestHandler implements RequestHandler {

    @Autowired
    private ChessGameEngine chessGameEngine;

    @Override
    public void execute(ObjectInputStream ois, ObjectOutputStream oos) {
        
    }
}
