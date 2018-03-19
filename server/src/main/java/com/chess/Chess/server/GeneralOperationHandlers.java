package com.chess.Chess.server;

import com.chess.Chess.model.Board;
import com.chess.Chess.model.Player;
import com.chess.Chess.operation_handler.OperationHandler;
import com.chess.Chess.service.UserService;
import com.chess.Chess.service.impl.ChessGameEngine;
import com.chess.Chess.util.NetworkModelsUtil;
import network.StatusCode;
import network.Response;
import network.model.NetworkGameBoard;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class GeneralOperationHandlers {
    private final static Logger logger = Logger.getLogger(GeneralOperationHandlers.class);

    private final ChessGameEngine chessGameEngine;
    private final UserService userService;

    @Autowired
    public GeneralOperationHandlers(UserService userService, ChessGameEngine chessGameEngine) {
        this.userService = userService;
        this.chessGameEngine = chessGameEngine;
    }

    @OperationHandler
    public Response createGame(network.model.Player receivedPlayer) throws IOException {
        Player user = userService.findByUsername(receivedPlayer.getUsername());

        logger.info("User " + user.getUsername() + " created game");
        Board board = chessGameEngine.createNewBoard(user);
        return new Response(StatusCode.OK, new NetworkGameBoard(board.getId()));
    }

    @OperationHandler
    public Response getAvailableGames(network.model.Player receivedPlayer) throws IOException {
        Player user = userService.findByUsername(receivedPlayer.getUsername());
        List<Board> boards = chessGameEngine.getAvailableBoards();

        System.out.println(boards);

        if (boards.size() != 0) {
            return new Response(StatusCode.OK, NetworkModelsUtil.convertToNetworkBoards(boards));
        } else {
            return new Response(StatusCode.ERROR, new ArrayList<>());
        }
    }

    @OperationHandler
    public Response joinGame(NetworkGameBoard networkGameBoard) throws IOException {
        if (chessGameEngine.isPosibleToConnect(networkGameBoard.getId())) {
            logger.info("Is possible to connect to server...");
            chessGameEngine.connect(
                    NetworkModelsUtil.convertToPlayer(networkGameBoard.getSecondPlayer()), networkGameBoard.getId()
            );

            return new Response(StatusCode.OK, networkGameBoard);
        } else {
            return new Response(StatusCode.ERROR, "Board is occupied by other players");
        }
    }

    @OperationHandler()
    public Response login(network.model.Player receivedPlayer) throws IOException {
        Player user = userService.findByUsername(receivedPlayer.getUsername());

        if (user == null) {
            return new Response(StatusCode.ERROR, "No such user in database");
        } else if (!user.getPassword().equals(receivedPlayer.getPassword())) {
            return new Response(StatusCode.ERROR, "Incorrect password");
        }

        return new Response(StatusCode.OK, NetworkModelsUtil.convertToNetworkPlayer(user));
    }

    @OperationHandler
    public Response registerUser(network.model.Player networkPlayer) throws IOException {
        Player receivedPlayer = NetworkModelsUtil.convertToPlayer(networkPlayer);

        Player player = userService.findByUsername(receivedPlayer.getUsername());

        if (player != null) {
            return new Response(StatusCode.ERROR, "User with such username already exist");
        } else {
            player = userService.save(receivedPlayer);

            return new Response(StatusCode.OK, NetworkModelsUtil.convertToNetworkPlayer(player));
        }
    }
}
