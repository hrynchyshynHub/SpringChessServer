package network.model;

import java.io.Serializable;

public class NetworkGameBoard implements Serializable{
    private Integer id;
    private Player firstPlayer;
    private Player secondPlayer;


    public NetworkGameBoard(Integer id, Player firstPlayer, Player secondPlayer) {
        this.id = id;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public Integer getId() {
        return id;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }
}
