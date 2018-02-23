package com.chess.Chess.server.requestHandlers_impl;

import com.chess.Chess.model.Board;
import com.chess.Chess.model.GameBoard;
import com.chess.Chess.model.Player;
import com.chess.Chess.server.RequestHandler;
import com.chess.Chess.service.UserService;
import com.chess.Chess.service.impl.ChessGameEngine;
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
public class CreateGameRequestHandler implements RequestHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private ChessGameEngine chessGameEngine;

    private final static Logger logger = Logger.getLogger(CreateGameRequestHandler.class);



    @Override
    public void execute(ObjectInputStream ois, ObjectOutputStream oos) {
        try {
            network.model.Player receivedPlayer = (network.model.Player) ois.readObject();
            Player user = userService.findByUsername(receivedPlayer.getUsername());

            logger.info("User " + user.getUsername() + " created game");

            chessGameEngine.setWhitePlayer(user);

            oos.writeObject(new Response(RequestCode.OK));



        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
