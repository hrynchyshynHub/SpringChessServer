package com.chess.Chess.network.model;


public class NetworkModelsUtil {

    public static Player convertToNetworkPlayer(com.chess.Chess.model.Player player){
        return new Player(
            player.getId(),
            player.getUsername(),
            player.getFirstName(),
            player.getSecondName(),
            player.getPassword(),
            player.getColor()
        );
    }
}
