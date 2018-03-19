package com.chess.Chess.server;

import com.chess.Chess.operation_handler.OperationHandler;
import network.StatusCode;
import network.Response;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Transactional
public class CellOperationHandlers {
    private Map<Integer, Map<String, String>> cells = new HashMap<>();


    @OperationHandler
    public Response selectCell(Integer boardId, String name, String cellId) throws IOException {
        if (cells.containsKey(boardId)) {
            cells.get(boardId).put(name, cellId);
        } else {
            Map<String, String> map = new ConcurrentHashMap<>();
            map.put(name, cellId);

            cells.put(boardId, map);
        }

        return new Response(StatusCode.OK);
    }

    @OperationHandler
    public Response getCell(Integer boardId, String name) throws IOException {
        // FIXME: maybe it's better to keep socket connection
        if (cells.containsKey(boardId)) {
            Map<String, String> moves = cells.get(boardId);

            Optional<String> enemy = moves.keySet()
                    .stream()
                    .filter(key -> !key.equals(name))
                    .findAny();

            if (enemy.isPresent()) {
                return new Response(StatusCode.OK, moves.remove(enemy.get()));
            }
        }

        return new Response(StatusCode.OK);
    }


    @OperationHandler
    public Response tryMove() {
        //TODO: implement this method
        return new Response(StatusCode.ERROR, "Not implemented yet");
    }

}
