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
public class Pawn extends Piece {

    private boolean isFirstMove = true;
    private static Deque<Cell> defaultCellStack = new ArrayDeque<>();

    public Pawn(Color color) {
        super(color);
        if(color == Color.WHITE){
            for(char latter = 'a'; latter <= 'h'; latter++){
                getDefaultCellStack().push(new Cell("2" + latter));
            }
        }else{
            for(char latter = 'a'; latter <= 'h'; latter++){
                getDefaultCellStack().push(new Cell("7" + latter));
            }
        }
    }



    @Override
    public List<String> getAvailableCellsToMove(Board board) {
        availableCellsToMove.clear();
        Cell currentCell = getCurrentCell();
        int xPos = currentCell.getX();
        char yPos = currentCell.getY();
        if (getColor() == Color.WHITE) {
            xPos++;
            availableCellsToMove.add(new String(xPos + "" + yPos));
            if (board.getCellById(new String(xPos + "" + (char) (yPos + 1))).getPiece() != null && board.getCellById(new String(xPos + "" + (char) (yPos + 1))).getPiece().getColor() != getColor()) {
                availableCellsToMove.add(new String(xPos + "" + (char) (yPos + 1)));
            }
            if (board.getCellById(new String(xPos + "" + (char) (yPos - 1))).getPiece() != null && board.getCellById(new String(xPos + "" + (char) (yPos - 1))).getPiece().getColor() != getColor()) {
                availableCellsToMove.add(new String(xPos + "" + (char) (yPos - 1)));
            }
            if (isFirstMove) {
                availableCellsToMove.add(new String(++xPos + "" + yPos));
            }
        } else {
            xPos--;
            availableCellsToMove.add(new String(xPos + "" + yPos));
            if (board.getCellById(new String(xPos + "" + (char) (yPos + 1))).getPiece() != null && board.getCellById(new String(xPos + "" + (char) (yPos + 1))).getPiece().getColor() != getColor()) {
                availableCellsToMove.add(new String(xPos + "" + (char) (yPos + 1)));
            }
            if (board.getCellById(new String(xPos + "" + (char) (yPos - 1))).getPiece() != null && board.getCellById(new String(xPos + "" + (char) (yPos - 1))).getPiece().getColor() != getColor()) {
                availableCellsToMove.add(new String(xPos + "" + (char) (yPos - 1)));
            }

            if (isFirstMove) {
                availableCellsToMove.add(new String(--xPos + "" + yPos));
            }
        }
        return availableCellsToMove;
    }

    public Deque<Cell> getDefaultCellStack() {
        return defaultCellStack;
    }

    @Override
    public String toString() {
        return "Pawn";
    }
}
