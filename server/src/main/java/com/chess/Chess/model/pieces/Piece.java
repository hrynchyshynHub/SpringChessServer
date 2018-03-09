package com.chess.Chess.model.pieces;

import com.chess.Chess.model.Board;
import com.chess.Chess.model.Cell;
import com.chess.Chess.util.Color;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */

public abstract class Piece {

    private Color color;
    protected Cell currentCell;
    private boolean available; // not dead
    protected List<String> availableCellsToMove = new ArrayList<>();

    public Piece(Color color) {
        this.color = color;
    }

    public Piece(Color color, Cell currentCell, boolean available, List<String> availableCellsToMove) {
        this.color = color;
        this.currentCell = currentCell;
        this.available = available;
        this.availableCellsToMove = availableCellsToMove;
    }

    public Piece() {
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return available == piece.available &&
            color == piece.color &&
            Objects.equals(currentCell, piece.currentCell) &&
            Objects.equals(availableCellsToMove, piece.availableCellsToMove);
    }

    @Override
    public int hashCode() {

        return Objects.hash(color, currentCell, available, availableCellsToMove);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<String> getAvailableCellsToMove() {
        return availableCellsToMove;
    }

    public void setAvailableCellsToMove(List<String> availableCellsToMove) {
        this.availableCellsToMove = availableCellsToMove;
    }

    /**
     * @return destination Cell

     */
    public abstract List<String> getAvailableCellsToMove(Board board); /**state is board state in current time; */


    public abstract Cell move(Board board, Cell destinationCell); /**state is board state in current time; */

    public abstract Deque<Cell> getDefaultCellStack();

}
