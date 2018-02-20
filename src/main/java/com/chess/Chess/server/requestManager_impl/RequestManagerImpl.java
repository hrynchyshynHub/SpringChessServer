package com.chess.Chess.server.requestManager_impl;

import com.chess.Chess.server.RequestHandler;
import com.chess.Chess.server.RequestManager;
import com.chess.Chess.server.requestHandlers_impl.LoginHandler;
import com.chess.Chess.server.requestHandlers_impl.RegisterUserRequestHandler;
import network.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestManagerImpl implements RequestManager {

    @Autowired
    private LoginHandler loginHandler;

    @Autowired
    private RegisterUserRequestHandler registerUserRequestHandler;

    @Override
    public RequestHandler handleRequest(OperationType type) {
        switch (type){
            case LOGIN:
                return loginHandler;
            case REGISTER_USER:
                return registerUserRequestHandler;
        }
        return null;
    }
}
