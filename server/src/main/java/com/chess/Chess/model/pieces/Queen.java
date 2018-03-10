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
    public List<String> getAvailableCellsToMove(Board board) {
        availableCellsToMove.clear();
        Cell currentCell = getCurrentCell();
        int xPos = currentCell.getX();
        char yPos = currentCell.getY();
        int step = 0;
        for (int i = xPos + 1; i <= 8; i++) {
            step++;
            String rightUpCell = new String(i + "" + (char) (yPos + step));
            if (board.getCellById(rightUpCell) != null){
                Piece piece = board.getCellById(rightUpCell).getPiece();
                if(piece == null ) {
                    availableCellsToMove.add(rightUpCell);
                }else{
                    if(piece.getColor() != getColor()){
                        availableCellsToMove.add(rightUpCell);
                        break;
                    }else
                        break;
                }
            }
        }

        for (int i = xPos + 1; i <= 8; i++) {
            step++;
            String leftUpCell = new String(i + "" + (char) (yPos - step));
            if (board.getCellById(leftUpCell) != null){
                Piece piece = board.getCellById(leftUpCell).getPiece();
                if(piece == null ) {
                    availableCellsToMove.add(leftUpCell);
                }else{
                    if(piece.getColor() != getColor()){
                        availableCellsToMove.add(leftUpCell);
                        break;
                    }else
                        break;
                }
            }
        }

        step = 0;

        for(int c = xPos - 1 ; c >= 1; c--){
            step++;
            String rightDownCell = new String(c + "" + (char) (yPos + step));
            if (board.getCellById(rightDownCell) != null){
                Piece piece = board.getCellById(rightDownCell).getPiece();
                if(piece == null ) {
                    availableCellsToMove.add(rightDownCell);
                }else{
                    if(piece.getColor() != getColor()){
                        availableCellsToMove.add(rightDownCell);
                        break;
                    }else
                        break;
                }
            }
        }
        for(int c = xPos - 1 ; c >= 1; c--){
            step++;
            String leftDownCell = new String(c + "" + (char) (yPos - step));
            if (board.getCellById(leftDownCell) != null){
                Piece piece = board.getCellById(leftDownCell).getPiece();
                if(piece == null ) {
                    availableCellsToMove.add(leftDownCell);
                }else{
                    if(piece.getColor() != getColor()){
                        availableCellsToMove.add(leftDownCell);
                        break;
                    }else
                        break;
                }
            }
        }

        for(int i = xPos; i <= 8 ; i++){
            Piece piece = board.getCellById(new String(i + "" + yPos)).getPiece();
            if(piece == null){
                availableCellsToMove.add(new String(i+""+yPos));
            }else{
                if(piece.getColor() != getColor()){
                    availableCellsToMove.add(new String(i+""+yPos));
                    break;
                }else{
                    break;
                }
            }
        }
        for(int i = xPos; i > 0 ; i--){
            Piece piece = board.getCellById(new String(i + "" + yPos)).getPiece();
            if(piece == null){
                availableCellsToMove.add(new String(i+""+yPos));
            }else{
                if (piece.getColor() != getColor()){
                    availableCellsToMove.add(new String(i+""+yPos));
                    break;
                }else
                    break;
            }
        }

        for (char a = yPos; a <= 'h'; a++) {
            Piece piece = board.getCellById(new String(xPos + "" + a)).getPiece();
            if (piece == null) {
                availableCellsToMove.add(new String(xPos + "" + a));
            } else {
                if (piece.getColor() != getColor()) {
                    availableCellsToMove.add(new String(xPos + "" + a));
                    break;
                } else
                    break;
            }
        }
        for(char a = yPos ; a > 'a'; a--){
            Piece piece = board.getCellById(new String(xPos + "" + a)).getPiece();
            if(piece == null){
                availableCellsToMove.add(new String(xPos+""+a));
            }else{
                if(piece.getColor() != getColor()){
                    availableCellsToMove.add(new String(xPos+""+a));
                    break;
                }else{
                    break;
                }
            }
        }
        return availableCellsToMove;
    }

    public Deque<Cell> getDefaultCellStack() {
        return defaultCellStack;
    }

    @Override
    public String toString() {
        return "Queen";
    }

}
