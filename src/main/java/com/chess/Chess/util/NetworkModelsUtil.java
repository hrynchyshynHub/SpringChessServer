package com.chess.Chess.util;
import network.model.Player;

public class NetworkModelsUtil {

    public static Player convertToNetworkPlayer(com.chess.Chess.model.Player player){
        return new Player(
            player.getId(),
            player.getUsername(),
            player.getFirstName(),
            player.getSecondName(),
            player.getPassword()
        );
    }

    public static com.chess.Chess.model.Player convertToPlayer(Player player){
        return new com.chess.Chess.model.Player(player.getUsername(), player.getFirstName(), player.getSecondName(), player.getPassword());
    }
}
