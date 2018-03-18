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
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SelectCellRequestHandler implements RequestHandler {

    public static Map<Integer, Map<String, String>> celss = new HashMap<>();

    @Autowired
    private ChessGameEngine chessGameEngine;

    @Override
    public boolean execute(ObjectInputStream ois, ObjectOutputStream oos) {
        try {
            Integer boardId = (Integer) ois.readObject();
            String name = (String) ois.readObject();
            String cellId = (String) ois.readObject();

            if (celss.containsKey(boardId)) {
                celss.get(boardId).put(name, cellId);
            } else {
                Map<String, String> map = new ConcurrentHashMap<>();
                map.put(name, cellId);

                celss.put(boardId, map);
            }

            oos.writeObject(new Response(RequestCode.OK));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }
}
