package com.chess.model;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import static com.chess.model.ChessBoard.CELL_SIZE;

public  class Piece extends StackPane{

    private PieceType pieceType;
    private Text text;

    public Piece(PieceType pieceType, int x, int y){
        this.pieceType = pieceType;
        setTranslateX(x * CELL_SIZE);
        setTranslateY(y * CELL_SIZE);
    }

    public Piece(PieceType pieceType){
        this.pieceType = pieceType;
    }

    public Piece(String name){
        this.text = new Text(name);
        text.setFill(Color.BLUE);
    }


    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }
}