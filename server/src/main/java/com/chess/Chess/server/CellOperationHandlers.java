package com.chess.Chess.server;

import com.chess.Chess.operation_handler.OperationHandler;
import network.OperationType;
import network.RequestCode;
import network.Response;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Transactional
public class CellOperationHandlers {
    private Map<Integer, Map<String, String>> cells = new HashMap<>();


    @OperationHandler(operationType = OperationType.SELECT_CELL)
    public void selectCell(ObjectInputStream ois, ObjectOutputStream oos) throws IOException, ClassNotFoundException {
        Integer boardId = (Integer) ois.readObject();
        String name = (String) ois.readObject();
        String cellId = (String) ois.readObject();

        if (cells.containsKey(boardId)) {
            cells.get(boardId).put(name, cellId);
        } else {
            Map<String, String> map = new ConcurrentHashMap<>();
            map.put(name, cellId);

            cells.put(boardId, map);
        }

        oos.writeObject(new Response(RequestCode.OK));
    }

    @OperationHandler(operationType = OperationType.GET_CELL)
    public void getCell(ObjectInputStream ois, ObjectOutputStream oos) throws IOException, ClassNotFoundException {
        Integer boardId = (Integer) ois.readObject();
        String name = (String) ois.readObject();

        // FIXME: maybe it's better to keep socket connection
        if (cells.containsKey(boardId)) {
            Map<String, String> moves = cells.get(boardId);

            Optional<String> enemy = moves.keySet()
                    .stream()
                    .filter(key -> !key.equals(name))
                    .findAny();

            if (enemy.isPresent()) {
                oos.writeObject(new Response(RequestCode.OK, moves.remove(enemy.get())));
                return;
            }
        }

        oos.writeObject(new Response(RequestCode.OK));
    }


    @OperationHandler(operationType = OperationType.TRY_MOVE)
    public void execute(ObjectInputStream ois, ObjectOutputStream oos) {
        //TODO: implement this method;
    }

}
