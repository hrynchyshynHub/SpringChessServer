package com.chess;

import com.chess.config.ApplicationProperties;
import com.chess.config.MainConfig;
import com.chess.controller_elements.Util;
import com.chess.controller_elements.ViewLoader;
import com.chess.model.ChessBoard;
import com.chess.network.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import network.OperationType;
import network.RequestCode;
import network.Response;
import network.model.NetworkGameBoard;

import java.io.IOException;


/**
 * Created by ivan.hrynchyshyn on 23.11.2017.
 */
public class MenuControlller {
    @FXML
    private Button btnNewGame;
    @FXML
    private Button btnLoadGame;
    @FXML
    private Button btnExit;
    @FXML
    private Label welcomelabel = new Label("Welcome user : " + MainConfig.getUser().getUsername());
    @FXML
    private AnchorPane pane;

    @FXML
    public void initialize(){
        welcomelabel.setLabelFor(btnNewGame);
        welcomelabel.setFont(new Font(25));
        pane.getChildren().add(welcomelabel);
    }

    public void newGame(ActionEvent event){
        Stage stage = (Stage)btnNewGame.getScene().getWindow();
        Parent root;
        Response response = Client.getInstance().send(OperationType.CREATE_GAME, MainConfig.getUser());

        if(response.getRequestCode().equals(RequestCode.ERROR)) {
            Util.showAlert("error", response.getData().toString(), "");
        }else if(response.getRequestCode().equals(RequestCode.OK)){
            Util.showAlert("Game created", "Waiting for oponent");
            MainConfig.setNetworkGameBoard((NetworkGameBoard) response.getData());
            ChessBoard chessBoard = new ChessBoard();
            Scene scene = new Scene(chessBoard.createContent());
            chessBoard.initializeWhitePieces();
            stage.setScene(scene);
            stage.show();
        }
    }

    public void loadGame(ActionEvent event){
        ViewLoader viewLoader = new ViewLoader((Node) event.getSource());
        viewLoader.loadScene("../view/availableGames.fxml", "Games");
    }

    public void exit(ActionEvent event){
        System.exit(0);
    }


}
