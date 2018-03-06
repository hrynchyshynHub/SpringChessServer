package com.chess.Chess.service.impl;

import com.chess.Chess.model.Board;
import com.chess.Chess.model.Player;
import com.chess.Chess.util.Color;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChessGameEngine {

    private List<Board> boards = new ArrayList<>();

    public Board createNewBoard(Player player){
        Board board = new Board();
        board.setWhitePlayer(player);
        boards.add(board);
        return board;
    }

    public List<Board> getAvailableBoards(){
        return boards.stream().filter(x-> x.getBlackPlayer() == null).collect(Collectors.toList());
    }

    public Board getBoardById(int id){
        for(Board b: boards){
            if(b.getId() == id) return b;
        }
        return null;
    }

    public boolean isPosibleToConnect(int id){
        Board board = getBoardById(id);
        return board.getBlackPlayer() == null || board.getWhitePlayer() == null;
    }

    public boolean connect(Player player, int gameId){
        Board board = getBoardById(gameId);
        if(isPosibleToConnect(gameId)){
            if ((board.getWhitePlayer() == null)) {
                board.setWhitePlayer(player);
                return true;
            } else {
                board.setBlackPlayer(player);
                return true;
            }
        }
        return false;
    }
}
