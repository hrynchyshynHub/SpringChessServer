package com.chess.Chess.util;

import com.chess.Chess.model.Cell;
import lombok.*;
import java.io.Serializable;


/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Move implements Serializable{
    private Cell source;
    private Cell destination;

}
