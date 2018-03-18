package com.chess.Chess.server.requestHandlers_impl;

import com.chess.Chess.server.RequestHandler;
import com.chess.Chess.service.impl.ChessGameEngine;
import network.RequestCode;
import network.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class SelectCellRequestHandler implements RequestHandler {

    public static Map<Pair<Integer, String>, String> celss = new HashMap<>();

    @Autowired
    private ChessGameEngine chessGameEngine;

    @Override
    public boolean execute(ObjectInputStream ois, ObjectOutputStream oos) {
        try {
            Integer boardId = (Integer) ois.readObject();
            String name = (String) ois.readObject();
            String cellId = (String) ois.readObject();

            celss.put(Pair.of(boardId, name), cellId);

            oos.writeObject(new Response(RequestCode.OK));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }
}
