package com.chess.Chess.server.requestManager_impl;

import com.chess.Chess.server.RequestHandler;
import com.chess.Chess.server.RequestManager;
import com.chess.Chess.server.requestHandlers_impl.*;
import network.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestManagerImpl implements RequestManager {

    @Autowired
    private LoginHandler loginHandler;

    @Autowired
    private RegisterUserRequestHandler registerUserRequestHandler;

    @Autowired
    private MoveRequestImpl moveRequestHandler;

    @Autowired
    private CreateGameRequestHandler createGameRequestHandler;

    @Autowired
    private JoinGameRequestHandler joinGameRequestHandler;

    @Autowired
    private GetAvailableGamesRequestHandler getAvailableGamesRequestHandler;

    @Override
    public RequestHandler handleRequest(OperationType type) {
        switch (type){
            case LOGIN:
                return loginHandler;
            case REGISTER_USER:
                return registerUserRequestHandler;
            case CREATE_GAME:
                return createGameRequestHandler;
            case TRY_MOVE:
                return  moveRequestHandler;
            case JOIN_GAME:
                return joinGameRequestHandler;
            case GET_AVAILABLE_GAMES:
                return getAvailableGamesRequestHandler;
        }
        return null;
    }
}
