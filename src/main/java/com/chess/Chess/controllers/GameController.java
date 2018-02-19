package com.chess.Chess.controllers;

import com.chess.Chess.model.Board;
import com.chess.Chess.util.Color;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {



    @GetMapping(path = "/startGame")
    public void startGame(){
        System.out.println("...Game is starting...");
        Board board = new Board();
        board.initializeBoard();
        board.initializatePieces(Color.BLACK);
        board.initializatePieces(Color.WHITE);
        board.showBoard();
    }
}
