package com.chess.Chess.server.requestManager_impl;

import com.chess.Chess.server.OperationType;
import com.chess.Chess.server.RequestHandler;
import com.chess.Chess.server.RequestManager;
import com.chess.Chess.server.requestHandlers_impl.LoginHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestManagerImpl implements RequestManager {

    @Autowired
    private LoginHandler loginHandler;

    @Override
    public RequestHandler handleRequest(OperationType type) {
        switch (type){
            case LOGIN:
                return loginHandler;
        }
        return null;
    }
}
