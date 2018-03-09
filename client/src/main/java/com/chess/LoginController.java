package com.chess;

import com.chess.config.MainConfig;
import com.chess.controller_elements.Util;
import com.chess.controller_elements.ViewLoader;
import com.chess.network.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import network.OperationType;
import network.RequestCode;
import network.Response;
import network.model.Player;

import java.io.IOException;

/**
 * Created by ivan.hrynchyshyn on 22.11.2017.
 */
public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;


    public void signIn(ActionEvent event) throws IOException{
        Stage stage =  (Stage)username.getScene().getWindow();
        String userName = username.getText();
        String pass = password.getText();

        if (userName.isEmpty() || pass.isEmpty()) {
            Util.showAlert("Incorrect inputs",
                "Some fields is empty",
                "Fields can't be empty");
            return;
        }

        Player player = new Player(userName, pass);

        Response response = Client.getInstance().send(OperationType.LOGIN, player);

        if(response.getRequestCode().equals(RequestCode.ERROR)){
            Util.showAlert("error", response.getData().toString(), "");
        }else if(response.getRequestCode().equals(RequestCode.OK)){
            player = (Player) response.getData();
            MainConfig.setUser(player);
            Parent root = FXMLLoader.load(getClass().getResource("view/Menu.fxml"));
            stage.setScene(new Scene(root, 1400, 800));
            stage.show();
        }

    }

    public void back(ActionEvent event) throws IOException {
        Stage stage = (Stage)username.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("view/Main.fxml"));
        stage.setScene(new Scene(root, 1400, 800));
        stage.show();
    }

}
