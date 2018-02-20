package com.chess.Chess.server.requestHandlers_impl;

import com.chess.Chess.model.Player;

import com.chess.Chess.server.RequestHandler;
import com.chess.Chess.service.UserService;
import com.chess.Chess.util.NetworkModelsUtil;
import network.RequestCode;
import network.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Component
@Transactional
public class RegisterUserRequestHandler implements RequestHandler {

    @Autowired
    private UserService userService;

    @Override
    public void execute(ObjectInputStream ois, ObjectOutputStream oos) {
        try{
            Player recivedPlayer = NetworkModelsUtil.convertToPlayer((network.model.Player)ois.readObject());

            Player player = userService.findByUsername(recivedPlayer.getUsername());

            if (player != null) {
                oos.writeObject(new Response(
                    RequestCode.ERROR, "User with such username already exist"
                ));
            } else {
                player = userService.save(recivedPlayer);

                oos.writeObject(
                    new Response(RequestCode.OK, NetworkModelsUtil.convertToNetworkPlayer(player))
                );
            }

        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
