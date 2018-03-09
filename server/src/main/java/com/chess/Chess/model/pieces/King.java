package com.chess.Chess.model.pieces;

import com.chess.Chess.model.Board;
import com.chess.Chess.model.Cell;
import com.chess.Chess.util.Color;

import java.util.*;

/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */
public class King extends Piece {
    private static Deque<Cell> defaultCellStack = new ArrayDeque<>();

    public King(Color color) {
        super(color);
        if(color == Color.WHITE){
            getDefaultCellStack().push(new Cell("1e"));
        }else{
            getDefaultCellStack().push(new Cell("8e"));
        }
    }

    @Override
    public List<String> getAvailableCellsToMove(Board board) {
        List<String> availableCellsToMove = new ArrayList<>();
        int xPos = currentCell.getX();
        char yPos = currentCell.getY();

         String cell1 = new String("" + xPos  + (char)(yPos+1));
         String cell2 = new String("" + xPos  + (char)(yPos-1));
         String cell3 = new String("" + xPos + 1 + (char)(yPos+1));
         String cell4 = new String("" + xPos + 1 + (char)(yPos-1));
         String cell5 = new String("" + (xPos - 1) + (char)(yPos+1));
         String cell6 = new String("" + (xPos - 1) + (char)(yPos-1));

        List<String> allCells = Arrays.asList(cell1,cell2,cell3,cell4,cell5,cell6);

        //Exclude out of board cells
        for(int i = 0; i < allCells.size(); i++){
            if(checkExistingOfCell(allCells.get(i), board)){
                availableCellsToMove.add(allCells.get(i));
            }
        }

         Color color = getColor() == Color.BLACK ? Color.WHITE : Color.BLACK;

        // Exclude danger cell
         List<Piece> opponentPieces = board.getAvailablePieces(color);
             for(Piece piece: opponentPieces){
                 List<String> oponentPieceAvailableMoves = piece.getAvailableCellsToMove();
                 Iterator<String> oponentPieceAvailableMovesIterator = oponentPieceAvailableMoves.iterator();

                 while (oponentPieceAvailableMovesIterator.hasNext()){
                     String availableMove = oponentPieceAvailableMovesIterator.next();
                     if(allCells.contains(availableMove)){
                         oponentPieceAvailableMovesIterator.remove();
                     }
                 }
         }

         // Exclude cells with friendly chess pieces
         Iterator<String> availableCellsToMoveIterator = availableCellsToMove.iterator();

         while (availableCellsToMoveIterator.hasNext()){
             String availableCellsToMoveId = availableCellsToMoveIterator.next();
             if(board.getCellById(availableCellsToMoveId).getPiece() != null){
                 if(board.getCellById(availableCellsToMoveId).getPiece().getColor() != color){
                     availableCellsToMoveIterator.remove();
                 }
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
        return "King in move branch";
    }

    private boolean checkExistingOfCell(String id, Board board){
        Cell cell = board.getCellById(id);
        if(cell != null) {
            if(cell.getPiece() != null && cell.getPiece().getColor() != getColor()) {
                return true;
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
}
