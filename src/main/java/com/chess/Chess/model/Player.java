package com.chess.Chess.model;



import com.chess.Chess.util.Color;
import lombok.*;

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
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Player implements Serializable{

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String firstName;
    private String secondName;
    private String password;
    private Color color;

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
}
