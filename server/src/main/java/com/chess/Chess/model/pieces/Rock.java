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
public class Rock extends Piece {
    private static Deque<Cell> defaultCellStack = new ArrayDeque<>();

    public Rock(Color color) {
        super(color);
        if(color == Color.WHITE){
            getDefaultCellStack().push(new Cell("1a"));
            getDefaultCellStack().push(new Cell("1h"));
        }else{
            getDefaultCellStack().push(new Cell("8h"));
            getDefaultCellStack().push(new Cell("8a"));
        }
    }


    @Override
    public List<String> getAvailableCellsToMove(Board board) {
        availableCellsToMove.clear();
        setCurrentCell(new Cell("5d"));
        Cell currentCell = getCurrentCell();
        int xPos = currentCell.getX();
        char yPos = currentCell.getY();

        for(int i = xPos; i <= 8 ; i++){
            Piece piece = board.getCellById(new String(i + "" + yPos)).getPiece();
            if(piece == null){
                availableCellsToMove.add(new String(i+""+yPos));
            }else
                break;
        }
        for(int i = xPos; i > 0 ; i--){
            Piece piece = board.getCellById(new String(i + "" + yPos)).getPiece();
            if(piece == null){
                availableCellsToMove.add(new String(i+""+yPos));
            }else
                break;
        }

        for(char a = yPos ; a <= 'h'; a++){
            Piece piece = board.getCellById(new String(xPos + "" + a)).getPiece();
            if(piece == null){
                availableCellsToMove.add(new String(xPos+""+a));
            }else
                break;
        }
        for(char a = yPos ; a > 'a'; a--){
            Piece piece = board.getCellById(new String(xPos + "" + a)).getPiece();
            if(piece == null){
                availableCellsToMove.add(new String(xPos+""+a));
            }else
                break;
        }
        return availableCellsToMove;
    }

    public Deque<Cell> getDefaultCellStack() {
        return defaultCellStack;
    }


    @Override
    public String toString() {
        return "Rock";
    }

}
