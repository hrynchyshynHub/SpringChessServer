package com.chess.Chess.model.pieces;


import com.chess.Chess.model.Board;
import com.chess.Chess.model.Cell;
import com.chess.Chess.util.Color;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */
public class Knight extends Piece {
    private static Deque<Cell> defaultCellStack = new ArrayDeque<>();

    public Knight(Color color) {
        super(color);
        if(color == Color.WHITE){
            getDefaultCellStack().push(new Cell("1b"));
            getDefaultCellStack().push(new Cell("1g"));
        }else{
            getDefaultCellStack().push(new Cell("8b"));
            getDefaultCellStack().push(new Cell("8g"));
        }
    }

    public Deque<Cell> getDefaultCellStack() {
        return defaultCellStack;
    }

    public void setDefaultCellStack(Deque<Cell> defaultCellStack) {
        this.defaultCellStack = defaultCellStack;
    }


    @Override
    public List<String> getAvailableCellsToMove(Board board) {
        availableCellsToMove.clear();
        Cell currentCell = getCurrentCell();
        int xPos = currentCell.getX();
        char yPos = currentCell.getY();

        final int gMoveStepHight = 2;
        final int gMoveStepLow = 1;

        String cell1 = new String(""+ (xPos + gMoveStepHight) + "" + (char)(yPos + gMoveStepLow));
        String cell2 = new String(""+ (xPos + gMoveStepHight) + "" + (char)(yPos - gMoveStepLow));
        String cell3 = new String(""+ (xPos + gMoveStepLow) + "" + (char)(yPos + gMoveStepHight));
        String cell4 = new String(""+ (xPos + gMoveStepLow) + "" + (char)(yPos - gMoveStepHight));
        String cell5 = new String(""+ (xPos - gMoveStepHight) + "" + (char)(yPos + gMoveStepLow));
        String cell6 = new String(""+ (xPos - gMoveStepHight) + "" + (char)(yPos - gMoveStepLow));
        String cell7 = new String(""+ (xPos - gMoveStepLow) + "" + (char)(yPos + gMoveStepHight));
        String cell8 = new String(""+ (xPos - gMoveStepLow) + "" + (char)(yPos - gMoveStepHight));

        List<String> allCells = Arrays.asList(cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8);

        for(int i = 0; i < allCells.size(); i++){
            if(checkExistingOfCell(allCells.get(i), board)){
                availableCellsToMove.add(allCells.get(i));
            }
        }
        return availableCellsToMove;
    }
    public boolean checkExistingOfCell(String id, Board board){
        Cell cell = board.getCellById(id);
        if(cell != null) {
            if(cell.getPiece() != null && cell.getPiece().getColor() != getColor()) {
                return true;   // check of own figure in destination cell exist
            }
            else if(cell.getPiece() == null){
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }
    @Override
    public String toString() {
        return "Knight";
    }

    public static void main(String[] args) {
        Board b =new Board();
        Knight k = new Knight(Color.WHITE);
        k.getAvailableCellsToMove(b);
    }
}
