package com.chess.Chess.service;

import com.chess.Chess.model.Player;

public interface UserService {
    Player findByUsername(String username);
    Player save(Player player);
    void delete(Player player);
}
