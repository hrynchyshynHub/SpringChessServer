package com.chess.Chess.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @RequestMapping(path = "/startGame")
    public void startGame(){
        System.out.println("...Game is starting...");
    }
}
