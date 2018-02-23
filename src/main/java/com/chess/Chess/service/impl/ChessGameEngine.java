package com.chess.Chess.service.impl;

import com.chess.Chess.model.Board;
import com.chess.Chess.model.Player;
import com.chess.Chess.util.Color;
import org.springframework.stereotype.Service;

@Service
public class ChessGameEngine {

    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;

    public ChessGameEngine() {
        board = new Board();
        board.initializeBoard();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(Player whitePlayer) {
        this.whitePlayer = whitePlayer;
        this.board.initializatePieces(Color.WHITE);
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(Player blackPlayer) {
        this.blackPlayer = blackPlayer;
        this.board.initializatePieces(Color.BLACK);
    }

    public boolean isPosibleToConnect(){
        return whitePlayer == null || blackPlayer == null;
    }
}
