package com.chess;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.chess.config.MainConfig;
import com.chess.controller_elements.Util;
import com.chess.controller_elements.ViewLoader;
import com.chess.network.Client;
import javafx.stage.Stage;
import network.OperationType;
import network.RequestCode;
import network.Response;
import network.model.Player;

import java.io.IOException;

/**
 * Created by ivan.hrynchyshyn on 22.11.2017.
 */
public class RegisterController {
    @FXML
    private Button cancelButton;
    @FXML
    private TextField username;
    @FXML
    private TextField firstName;
    @FXML
    private TextField secondName;
    @FXML
    private TextField password;
    @FXML
    private TextField color;


    public void signUp(ActionEvent event) throws IOException{
        String username = this.username.getText();
        String firstName = this.firstName.getText();
        String secondName = this.secondName.getText();
        String password = this.password.getText();
        String color = this.color.getText();

        if (username.isEmpty() || password.isEmpty() ||
            firstName.isEmpty() || secondName.isEmpty()
            || color.isEmpty() ) {
            Util.showAlert("Incorrect inputs",
                "Some fields is empty",
                "Fields can't be empty");
            return;
        }

        Player player = new Player(null, username, firstName, secondName, password);

        Response response = Client.getInstance().send(OperationType.REGISTER_USER, player);

        if(response.getRequestCode().equals(RequestCode.ERROR)){
            Util.showAlert("error", response.getData().toString(), "");
        }else if(response.getRequestCode().equals(RequestCode.OK)){
            player = (Player) response.getData();
            MainConfig.setUser(player);
            Stage stage = (Stage) this.cancelButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("view/Menu.fxml"));
            stage.setScene(new Scene(root, 1400, 800));
            stage.show();
        }
     }

    public void back(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("view/Main.fxml"));
        stage.setScene(new Scene(root, 1400, 800));
        stage.show();
    }
}
