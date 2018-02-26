package network.model;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class NetworkGameBoard implements Serializable{
    private Integer id;
    private Player firstPlayer;
    private Player secondPlayer;

    private NetworkGameBoard networkGameBoard;
    private final SimpleStringProperty gameid;
    private final SimpleStringProperty firstPlayerNick;
    private final SimpleStringProperty secondPlayerNick;


    public NetworkGameBoard(Integer id, Player firstPlayer, Player secondPlayer) {
        this.id = id;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;

        this.gameid = new SimpleStringProperty(id.toString());
        this.firstPlayerNick = new SimpleStringProperty(firstPlayer.getUsername());
        this.secondPlayerNick = new SimpleStringProperty(secondPlayer.toString());
    }
    public NetworkGameBoard(NetworkGameBoard networkGameBoard) {
        this.networkGameBoard = networkGameBoard;
        this.id = networkGameBoard.id;
        this.firstPlayer = networkGameBoard.firstPlayer;
        this.secondPlayer = networkGameBoard.secondPlayer;

        this.gameid = new SimpleStringProperty(networkGameBoard.id.toString());
        this.firstPlayerNick = new SimpleStringProperty(networkGameBoard.firstPlayer.getUsername());
        this.secondPlayerNick = new SimpleStringProperty(networkGameBoard.secondPlayer.toString());
    }
}
