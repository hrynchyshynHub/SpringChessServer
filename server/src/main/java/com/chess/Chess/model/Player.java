package com.chess.Chess.model;


import com.chess.Chess.util.Color;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */

@Entity
@Table(name = "players")
public class Player implements Serializable{

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String firstName;
    private String secondName;
    private String password;
    private Color color;



    public Player() {
    }

    public Player(String username, String firstName, String secondName, String password){
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
    }

    public Player(String username, String firstName, String secondName, String password, Color color){
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
