package com.chess.Chess.service.impl;

import com.chess.Chess.database.PlayerRepository;
import com.chess.Chess.service.UserService;
import com.chess.Chess.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player findByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    @Override
    public Player save(Player player) {
        System.out.println(player +  " into service mthod") ;
        return playerRepository.save(player);
    }

    @Override
    public void delete(Player player) {
        playerRepository.delete(player);
    }
}
