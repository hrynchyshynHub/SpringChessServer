package com.chess.Chess.server.requestHandlers_impl;

import com.chess.Chess.model.Board;
import com.chess.Chess.model.Player;
import com.chess.Chess.server.RequestHandler;
import com.chess.Chess.service.UserService;
import com.chess.Chess.service.impl.ChessGameEngine;
import com.chess.Chess.util.NetworkModelsUtil;
import network.RequestCode;
import network.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class GetAvailableGamesRequestHandler implements RequestHandler {

    @Autowired
    private ChessGameEngine chessGameEngine;

    @Autowired
    private UserService userService;

    @Override
    public void execute(ObjectInputStream ois, ObjectOutputStream oos) {
        try{
            network.model.Player receivedPlayer = (network.model.Player) ois.readObject();
            Player user = userService.findByUsername(receivedPlayer.getUsername());

            List<Board> boards = chessGameEngine.getAvailableBoards();

            System.out.println(boards);

            if(boards.size() != 0 ){
                oos.writeObject(new Response(RequestCode.OK, NetworkModelsUtil.convertToNetworkBoards(boards)));
            }else{
                oos.writeObject(new Response(RequestCode.ERROR, new ArrayList<>()));
                return;
            }

        }catch (IOException | ClassNotFoundException e){

        }

    }
}
