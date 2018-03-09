package com.chess.model;


public enum PieceType {
    PAWN_W("Chess_pawn_w.png"),
    BISHOP_W(""),
    KING_W("Chess_king_w.png"),
    KNIGHT_W("Chess_knight_w.png"),
    QUEEN_W("Chess_queen_b.png"),
    ROCK_W("Chess_rock_w.png"),
    PAWN_B("Chess_pawn_b.png"),
    BISHOP_B("Chess_b_bishop.png"),
    KING_B("Chess_king_b.png"),
    KNIGHT_B("Chess_knight_b.png"),
    QUEEN_B("Chess_queen_b.png"),
    ROCK_B("Chess_rock_b.png");

    private String pathToImage;
    private static final String root = "D:\\mentorship\\Client\\src\\com\\chess\\model\\";

    PieceType(String pathToImage){
        this.pathToImage = pathToImage;
    }

    public String getPathToImage() {
        return root + pathToImage;
    }
}
