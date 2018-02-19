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
public class Bishop extends Piece{
    private static Deque<Cell> defaultCellStack = new ArrayDeque<>();

    public Bishop(Color color) {
        super(color);
        if(color == Color.WHITE){
            getDefaultCellStack().push(new Cell("1c"));
            getDefaultCellStack().push(new Cell("1f"));
        }else{
            getDefaultCellStack().push(new Cell("8c"));
            getDefaultCellStack().push(new Cell("8f"));
        }
    }

    @Override
    public Cell move(Board board, Cell destinationCell) {
        List<String> availableCellsToMove = getAvailableCellsToMove(board);
        for(String id: availableCellsToMove){
            if(destinationCell.getId().equalsIgnoreCase(id)){
                setCurrentCell(destinationCell);
                Piece piece = board.getCellById(id).getPiece();
                if(piece != null ){
                    piece.setAvailable(false); // Remove old piece
                }
                board.getCellById(id).setPiece(this);
                return destinationCell;
            }
        }
        return currentCell;
    }

    @Override
    public List<String> getAvailableCellsToMove(Board board) {
        availableCellsToMove.clear();
        Cell currentCell = getCurrentCell();
        int xPos = currentCell.getX();
        char yPos = currentCell.getY();
        System.out.println(currentCell.toString());
//        int[] rows = new int[]{1,2,3,4,5,6,7,8};
//        char[] columns = new char[]{'a','b','c','d','e','f','g','h'};
        int step = 0;
        for (int i = xPos + 1; i <= 8; i++) {
            step++;
//               System.out.println(i + " " + (char)(yPos + step) + " -- left up");
            String rightUpCell = new String(i + "" + (char) (yPos + step));
            String leftUpCell = new String(i + "" + (char) (yPos - step));
            if (board.getCellById(rightUpCell) != null && board.getCellById(rightUpCell).getPiece() == null) {
                availableCellsToMove.add(rightUpCell);
            }
            if (board.getCellById(leftUpCell) != null && board.getCellById(leftUpCell).getPiece() == null) {
                availableCellsToMove.add(leftUpCell);
            }
        }
        step = 0;
        for(int c = xPos - 1 ; c >= 1; c--){
            step++;
            String rightDownCell = new String(c + "" + (char) (yPos + step));
            String leftDownCell = new String(c + "" + (char) (yPos - step));
            if (board.getCellById(rightDownCell) != null && board.getCellById(rightDownCell).getPiece() == null) {
                availableCellsToMove.add(rightDownCell);
            }
            if (board.getCellById(leftDownCell) != null && board.getCellById(leftDownCell).getPiece() == null) {
                availableCellsToMove.add(leftDownCell);
            }
        }
        return availableCellsToMove;
    }

    public Deque<Cell> getDefaultCellStack() {
        return defaultCellStack;
    }

    public void setDefaultCellStack(Deque<Cell> defaultCellStack) {
        this.defaultCellStack = defaultCellStack;
    }

    @Override
    public String toString() {
        return "Bishop";
    }

}
