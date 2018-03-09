package com.chess.config;


import network.model.NetworkGameBoard;
import network.model.Player;

public class MainConfig {
    private static Player player;
    private static NetworkGameBoard networkGameBoard;

    public static void reset() {
        player = null;
    }

    public static Player getUser() {
        return player;
    }

    public static void setUser(Player user) {
        MainConfig.player = user;
    }

    public static void resetBoard(){
        networkGameBoard = null;
    }

    public static NetworkGameBoard getNetworkGameBoard() {
        return networkGameBoard;
    }

    public static void setNetworkGameBoard(NetworkGameBoard networkGameBoard) {
        MainConfig.networkGameBoard = networkGameBoard;
    }
}
