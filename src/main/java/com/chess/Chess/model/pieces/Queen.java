package com.chess.Chess.model.pieces;

import com.chess.Chess.model.Board;
import com.chess.Chess.model.Cell;
import com.chess.Chess.util.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */
public class Queen extends Piece {
    private static Deque<Cell> defaultCellStack = new ArrayDeque<>();

    public Queen(Color color) {
        super(color);
        if(color == Color.WHITE){
            getDefaultCellStack().push(new Cell("1d"));
        }else{
            getDefaultCellStack().push(new Cell("8d"));
        }
    }

    @Override
    public Cell move(Board board, Cell destinationCell) {
        return null;
    }

    @Override
    public List<String> getAvailableCellsToMove(Board board) {
        return null;
    }

    public Deque<Cell> getDefaultCellStack() {
        return defaultCellStack;
    }

    @Override
    public String toString() {
        return "Queen";
    }
}
