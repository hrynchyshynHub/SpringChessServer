package com.chess.Chess.network.model;

import com.chess.Chess.util.Color;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private Long id;
    private String username;
    private String firstName;
    private String secondName;
    private String password;
    private Color color;
}
