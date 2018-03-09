package com.chess.Chess.util;

import com.chess.Chess.model.Board;
import network.model.NetworkGameBoard;
import network.model.Player;

import java.util.ArrayList;
import java.util.List;

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

    public static NetworkGameBoard convertToNetworkBoard(Board board){
        Player whiteplayer = null;
        Player blackPlayer = null;
        if(board.getWhitePlayer() != null){
            whiteplayer = convertToNetworkPlayer(board.getWhitePlayer());
        }
        if(board.getBlackPlayer() != null){
            blackPlayer = convertToNetworkPlayer(board.getBlackPlayer());
        }

        return new NetworkGameBoard(board.getId(), whiteplayer, blackPlayer);
    }

    public static List<NetworkGameBoard> convertToNetworkBoards(List<Board> boards){
        List<NetworkGameBoard> networkGameBoards = new ArrayList<>();
        for(Board board : boards ){
            networkGameBoards.add(convertToNetworkBoard(board));
        }
        return networkGameBoards;
    }
}
