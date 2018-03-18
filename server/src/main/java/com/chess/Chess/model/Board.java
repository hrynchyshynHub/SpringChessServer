package com.chess.Chess.model;



import com.chess.Chess.model.pieces.*;
import com.chess.Chess.util.Color;
import com.chess.Chess.util.Move;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ivan.hrynchyshyn on 15.11.2017.
 */
public class Board implements GameBoard{
   private static AtomicInteger id = new AtomicInteger();

   private int uniqueId;
   private Cell[][] cells = new Cell[8][8];
   private Player whitePlayer;
   private Player blackPlayer;
   private boolean isWin;
   private Cell selectedCell;
   private Queue<Move> moves = new PriorityQueue<>();
   private Map<Piece, Cell> piecesOnBoardDefault = new HashMap<>();


   public Board(){
      uniqueId = id.incrementAndGet();
      initializeBoard();
      initializatePieces(Color.WHITE);
      initializatePieces(Color.BLACK);
   }

   @Override
   public void initializeBoard() {
      Color color = Color.WHITE;
       for(int i = 7; i >= 0 ; i--){
           for(int j = 0; j < 8; j++){
               if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                   color = Color.BLACK;
               } else {
                   color = Color.WHITE;
               }
               cells[i][j] = new Cell(i, (char) (j + 97),color);
               System.out.print('[' + cells[i][j].getId() + cells[i][j].getColor() + ']'  );
           }
           System.out.println();
       }
   }

   @Override
   public void initializatePieces(Color color){
      Rock rock1 = new Rock(color);
      Rock rock2 = new Rock(color);
      Knight knight1 = new Knight(color);
      Knight knight2 = new Knight(color);
      Bishop bishop1 = new Bishop(color);
      Bishop bishop2 = new Bishop(color);
      King king = new King(color);
      Queen queen = new Queen(color);
      List<Piece> pieces = new ArrayList<>();
      pieces.add(rock1);
      pieces.add(rock2);
      pieces.add(knight1);
      pieces.add(knight2);
      pieces.add(bishop1);
      pieces.add(bishop2);
      pieces.add(king);
      pieces.add(queen);
      List<Piece> pawns = new ArrayList<>();
      for(int i = 0; i < 8; i++){
         pawns.add(new Pawn(color));
      }
      pieces.addAll(pawns);
      for(int chessCount = 0; chessCount < 16 ; chessCount++ ){
            initializatePiece(pieces.get(chessCount));
      }
   }

   @Override
   public  void showBoard(){
      for(int i =0; i < 8 ; i++){
         for(int j =0; j< 8 ; j++){
            if(cells[i][j].getPiece() != null){
               System.out.print("[" + cells[i][j].getId() + " " + cells[i][j].getColor() + " = " + cells[i][j].getPiece().toString() +  "]");
            }
         }
         System.out.println();
      }
   }

   @Override
   public Cell getCellById(String id){
      Cell foundCell = null;
      for(int i = 0; i < 8; i++){
         for(int j =0 ; j < 8; j++){
            if(cells[i][j].getId().equalsIgnoreCase(id)) foundCell = cells[i][j];
         }
      }
      return foundCell;
   }

   @Override
   public List<Piece> getAvailablePieces(Color color){
      List<Piece> availablePieces = new ArrayList<>();
      for(int i = 0; i < 8; i++){
         for(int j =0 ; j < 8; j++){
            if(cells[i][j].getPiece() != null && cells[i][j].getPiece().isAvailable() && cells[i][j].getPiece().getColor() == color){
               availablePieces.add(cells[i][j].getPiece());
            }
         }
      }
      return availablePieces;
   }

   @Override
   public boolean move(Move move){
      Piece piece = move.getSource().getPiece();
      if(piece == null){ //TODO: Add condition for userColor and Piece color equeals;
         return false;
      }else{
         Cell afterMove = piece.move(this, move.getDestination());
         if(move.getSource().getId().equalsIgnoreCase(afterMove.getId())){
            return false;
         }
         return true;
      }
   }

   private void initializatePiece(Piece piece){
      Cell cell = getCellById(piece.getDefaultCellStack().pop().getId());
      cell.setPiece(piece);
      piece.setCurrentCell(cell);
      piecesOnBoardDefault.put(piece, cell);
   }

   public int getId() {
      return uniqueId;
   }

   public Player getWhitePlayer() {
      return whitePlayer;
   }

   public void setWhitePlayer(Player whitePlayer) {
      this.whitePlayer = whitePlayer;
   }

   public Player getBlackPlayer() {
      return blackPlayer;
   }

   public void setBlackPlayer(Player blackPlayer) {
      this.blackPlayer = blackPlayer;
   }

   public Cell getSelectedCell() {
      return selectedCell;
   }

   public void setSelectedCell(Cell selectedCell) {
      this.selectedCell = selectedCell;
   }

   @Override
   public String toString() {
      return "Board{" + uniqueId +
          "whitePlayer=" + whitePlayer +
          ", blackPlayer=" + blackPlayer +
          '}';
   }
}
