package com.chess.Chess.server;

import com.chess.Chess.model.Board;
import com.chess.Chess.model.Player;
import com.chess.Chess.operation_handler.OperationHandler;
import com.chess.Chess.service.UserService;
import com.chess.Chess.service.impl.ChessGameEngine;
import com.chess.Chess.util.NetworkModelsUtil;
import network.OperationType;
import network.RequestCode;
import network.Response;
import network.model.NetworkGameBoard;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    @OperationHandler(operationType = OperationType.CREATE_GAME)
    public void createGame(ObjectInputStream ois, ObjectOutputStream oos) throws IOException, ClassNotFoundException {
        network.model.Player receivedPlayer = (network.model.Player) ois.readObject();
        Player user = userService.findByUsername(receivedPlayer.getUsername());

        logger.info("User " + user.getUsername() + " created game");
        Board board = chessGameEngine.createNewBoard(user);
        oos.writeObject(
                new Response(RequestCode.OK, new NetworkGameBoard(board.getId(), null, null))
        );
    }

    @OperationHandler(operationType = OperationType.GET_AVAILABLE_GAMES)
    public void getAvailableGames(ObjectInputStream ois, ObjectOutputStream oos)
            throws IOException, ClassNotFoundException {
        network.model.Player receivedPlayer = (network.model.Player) ois.readObject();
        Player user = userService.findByUsername(receivedPlayer.getUsername());
        List<Board> boards = chessGameEngine.getAvailableBoards();

        System.out.println(boards);

        if (boards.size() != 0) {
            oos.writeObject(new Response(RequestCode.OK, NetworkModelsUtil.convertToNetworkBoards(boards)));
        } else {
            oos.writeObject(new Response(RequestCode.ERROR, new ArrayList<>()));
        }
    }

    @OperationHandler(operationType = OperationType.JOIN_GAME)
    public void joinGame(ObjectInputStream ois, ObjectOutputStream oos) throws IOException, ClassNotFoundException {
        NetworkGameBoard networkGameBoard = (NetworkGameBoard) ois.readObject();

        if (chessGameEngine.isPosibleToConnect(networkGameBoard.getId())) {
            logger.info("Is possible to connect to server...");
            chessGameEngine.connect(
                    NetworkModelsUtil.convertToPlayer(networkGameBoard.getSecondPlayer()), networkGameBoard.getId()
            );

            oos.writeObject(new Response(RequestCode.OK, networkGameBoard));
        } else {
            oos.writeObject(new Response(RequestCode.ERROR, "Board is occupied by other players"));
        }
    }

    @OperationHandler(operationType = OperationType.LOGIN)
    public void login(ObjectInputStream ois, ObjectOutputStream oos) throws IOException, ClassNotFoundException {
        network.model.Player receivedPlayer = (network.model.Player) ois.readObject();
        Player user = userService.findByUsername(receivedPlayer.getUsername());

        if (user == null) {
            oos.writeObject(new Response(RequestCode.ERROR, "No such user in database"));
        } else if (!user.getPassword().equals(receivedPlayer.getPassword())) {
            oos.writeObject(new Response(RequestCode.ERROR, "Incorrect password"));
        }
        oos.writeObject(new Response(RequestCode.OK, NetworkModelsUtil.convertToNetworkPlayer(user)));
    }

    @OperationHandler(operationType = OperationType.REGISTER_USER)
    public void registerUser(ObjectInputStream ois, ObjectOutputStream oos)
            throws IOException, ClassNotFoundException {
        Player receivedPlayer = NetworkModelsUtil.convertToPlayer((network.model.Player) ois.readObject());

        Player player = userService.findByUsername(receivedPlayer.getUsername());

        if (player != null) {
            oos.writeObject(new Response(RequestCode.ERROR, "User with such username already exist"));
        } else {
            player = userService.save(receivedPlayer);

            oos.writeObject(new Response(RequestCode.OK, NetworkModelsUtil.convertToNetworkPlayer(player)));
        }
    }
}
