package com.chess.Chess.server.requestHandlers_impl;

import com.chess.Chess.server.RequestHandler;
import network.RequestCode;
import network.Response;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Optional;

@Component
public class GetCellRequestHandler implements RequestHandler {

    @Override
    public boolean execute(ObjectInputStream ois, ObjectOutputStream oos) {

        try {
            Integer boardId = (Integer) ois.readObject();
            String name = (String) ois.readObject();

            Thread thread = new Thread(() -> {
                try {
                    while (true) {
                        if (SelectCellRequestHandler.celss.containsKey(boardId)) {
                            Map<String, String> moves = SelectCellRequestHandler.celss.get(boardId);
                            Optional<String> enemy = moves.keySet().stream()
                                    .filter(key -> !key.equals(name)).findAny();

                            if (enemy.isPresent()) {
                                oos.writeObject(
                                        new Response(RequestCode.OK, moves.remove(enemy.get()))
                                );
                                oos.flush();
                                return;
                            }
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
