package com.chess.model;


import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;


public class ChessBoard{

    public static final int BOARD_SIZE = 8;
    public static final int CELL_SIZE = 60;

    private Cell [][]board = new Cell[BOARD_SIZE][BOARD_SIZE];
    private GridPane root = new GridPane();

    private Cell selectedCell = null;

    public Parent createContent(){
        boolean isWhite = true;
        Color color = Color.WHITE;
        for(int i = 7; i >= 0 ; i--){
            for(int j = 0; j < 8; j++){
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    color = Color.WHITE;
                } else {
                    color = Color.BLACK;
                }
                board[i][j] = new Cell(color, i, (char) (j + 97), this);
                GridPane.setRowIndex(board[i][j], 8-i);
                GridPane.setColumnIndex(board[i][j], j);
                root.getChildren().addAll(board[i][j]);
                System.out.print('[' + board[i][j].getCellId() + board[i][j].getColor() + ']'  );
            }
            System.out.println();
            }
        return root;
    }

    public void initializeWhitePieces(){

    }
    public void initializeBlackPieces(){

    }
    private Piece createPiece(PieceType type, int x, int y){
        Piece piece = new Piece(type, x, y);
        return piece;
    }

    private Cell getCellById(String id){
        Cell foundCell = null;
        for(int i = 0; i < 8; i++){
            for(int j =0 ; j < 8; j++){
                if(board[i][j].getCellId().equalsIgnoreCase(id)) foundCell = board[i][j];
            }
        }
        return foundCell;
    }

    public Cell getSelectedCell() {
        return selectedCell;
    }

    public void setSelectedCell(Cell selectedCell) {
        if(this.selectedCell != null){
            this.selectedCell.setStrokeType(StrokeType.INSIDE);
            this.selectedCell.setStrokeWidth(0);
        }
        this.selectedCell = selectedCell;
        this.selectedCell.setStrokeType(StrokeType.INSIDE);
        this.selectedCell.setStroke(Color.RED);
        this.selectedCell.setStrokeWidth(3);
        System.out.println(selectedCell.getCellId());
    }
}