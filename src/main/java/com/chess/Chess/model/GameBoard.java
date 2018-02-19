package com.chess.Chess.model;

import com.chess.Chess.model.pieces.Piece;
import com.chess.Chess.util.Color;
import com.chess.Chess.util.Move;
import java.util.List;

public interface GameBoard {
     void initializeBoard();
     void initializatePieces(Color color);
     void showBoard();
     Cell getCellById(String id);
     List<Piece> getAvailablePieces(Color color);
     boolean move(Move move);
}
