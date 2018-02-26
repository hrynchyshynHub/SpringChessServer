package com.chess.Chess.server.requestHandlers_impl;

import com.chess.Chess.model.Player;
import com.chess.Chess.server.RequestHandler;
import com.chess.Chess.service.UserService;
import com.chess.Chess.service.impl.ChessGameEngine;
import com.chess.Chess.util.NetworkModelsUtil;
import network.RequestCode;
import network.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Component
@Transactional
public class JoinGameRequestHandler implements RequestHandler {

    @Autowired
    private UserService userService;
    @Autowired
    private ChessGameEngine chessGameEngine;

    private final static Logger logger = Logger.getLogger(JoinGameRequestHandler.class);

    @Override
    public void execute(ObjectInputStream ois, ObjectOutputStream oos) {
        try{
            network.model.Player receivedPlayer = (network.model.Player) ois.readObject();
            Player user = userService.findByUsername(receivedPlayer.getUsername());

//            if(chessGameEngine.isPosibleToConnect()){
//                logger.info("Is posible to connect to server...");
//                if(chessGameEngine.getBlackPlayer() == null){
//                    chessGameEngine.setBlackPlayer(NetworkModelsUtil.convertToPlayer(receivedPlayer));
//                }else if(chessGameEngine.getWhitePlayer() == null){
//                    chessGameEngine.setWhitePlayer(NetworkModelsUtil.convertToPlayer(receivedPlayer));
//                }
//                //TODO: check if the user is not the same
//
//                oos.writeObject(new Response(RequestCode.OK,"You are connected"));
//
//            }else{
//                oos.writeObject(new Response(RequestCode.ERROR, "Board is occupied by other players"));
//            }

        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
