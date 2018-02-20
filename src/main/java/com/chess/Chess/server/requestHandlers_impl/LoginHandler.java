package com.chess.Chess.server.requestHandlers_impl;

import com.chess.Chess.server.RequestHandler;
import com.chess.Chess.service.UserService;
import com.chess.Chess.model.Player;
import com.chess.Chess.util.NetworkModelsUtil;
import network.RequestCode;
import network.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Component
@Transactional
public class LoginHandler implements RequestHandler{

    @Autowired
    private UserService userService;

    @Override
    public void execute(ObjectInputStream ois, ObjectOutputStream oos) {
       try{
           network.model.Player receivedPlayer = (network.model.Player) ois.readObject();

           Player user = userService.findByUsername(receivedPlayer.getUsername());

           if (user == null) {
               oos.writeObject(new Response(RequestCode.ERROR, "No such user in database"));
               return;
           } else if (!user.getPassword().equals(receivedPlayer.getPassword())) {
               oos.writeObject(new Response(RequestCode.ERROR, "Incorrect password"));
               return;
           }
           oos.writeObject(new Response(RequestCode.OK, NetworkModelsUtil.convertToNetworkPlayer(user)));
       }catch (IOException | ClassNotFoundException e){
           System.out.println(e);
       }
    }
}
