package com.chess.Chess.model.pieces;

import com.chess.Chess.model.Board;
import com.chess.Chess.model.Cell;
import com.chess.Chess.util.Color;
import lombok.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Piece {

    private Color color;
    protected Cell currentCell;
    private boolean available; // not dead
    protected List<String> availableCellsToMove = new ArrayList<>();

    public Piece(Color color) {
        this.color = color;
    }

    /**
     * @return destination Cell

     */
    public abstract List<String> getAvailableCellsToMove(Board board); /**state is board state in current time; */


    public abstract Cell move(Board board, Cell destinationCell); /**state is board state in current time; */

    public abstract Deque<Cell> getDefaultCellStack();

}
