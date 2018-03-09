package com.chess.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class PieceSetter {
    private Piece piece; // StackPane
    private Cell cell;//Rectabgule
    private ChessBoard chessBoard;

    public PieceSetter(Piece piece, Cell cell, ChessBoard chessBoard) {
        this.piece = piece;
        this.cell = cell;
        this.chessBoard = chessBoard;
        setPieceToCell();
    }

    public void setPieceToCell(){
        File file = new File(piece.getPieceType().getPathToImage());
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView(image);
        piece.getChildren().addAll(cell, imageView);
    }
}
