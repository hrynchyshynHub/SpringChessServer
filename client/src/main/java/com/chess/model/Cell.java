package com.chess.model;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import static com.chess.model.ChessBoard.CELL_SIZE;

public class Cell extends Rectangle{

    private Piece piece;
    private int x;
    private char y;
    private String id;
    private Color color;
    private ChessBoard chessBoard;


    public Cell(Color color, int x, char y, ChessBoard chessBoard){
        super(100,100);
        this.chessBoard = chessBoard;
        this.x = ++x;
        this.y = y;
        this.id = x + "" + y;
        setX(x * CELL_SIZE);
        setY((y-97) * CELL_SIZE);
        this.color = color;
        setFill(color);
        setOnMouseClicked( e -> {
//            if(getPiece() != null) {
                chessBoard.setSelectedCell(this);
//            }
        });
    }
    public boolean hasPiece(){
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;

    }



    public String getCellId() {
        return id;
    }

    @Override
    public String toString() {
        return getFill().toString();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}