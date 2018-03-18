package com.chess.Chess.server.requestHandlers_impl;

import com.chess.Chess.server.RequestHandler;
import com.chess.Chess.service.UserService;
import com.chess.Chess.service.impl.ChessGameEngine;
import com.chess.Chess.util.NetworkModelsUtil;
import network.RequestCode;
import network.Response;
import network.model.NetworkGameBoard;
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
    public boolean execute(ObjectInputStream ois, ObjectOutputStream oos) {
        try{

            NetworkGameBoard networkGameBoard = (NetworkGameBoard)ois.readObject();


            if(chessGameEngine.isPosibleToConnect(networkGameBoard.getId())){
                logger.info("Is posible to connect to server...");
                chessGameEngine.connect(NetworkModelsUtil.convertToPlayer(networkGameBoard.getSecondPlayer()), networkGameBoard.getId());

                oos.writeObject(new Response(RequestCode.OK, networkGameBoard));

            }else{
                oos.writeObject(new Response(RequestCode.ERROR, "Board is occupied by other players"));
            }

        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return true;
    }
}
