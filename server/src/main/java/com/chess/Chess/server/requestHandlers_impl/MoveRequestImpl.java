package com.chess.Chess.server.requestHandlers_impl;

import com.chess.Chess.server.RequestHandler;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Component
@Transactional
public class MoveRequestImpl implements RequestHandler {

    @Override
    public boolean execute(ObjectInputStream ois, ObjectOutputStream oos) {
        return false;
    }
}
