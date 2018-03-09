package com.chess;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnRegister;

    public void handleButtonAction(ActionEvent event) throws IOException{
        Stage stage;
        Parent root;

        if(event.getSource() == btnLogin){
             stage = (Stage)btnLogin.getScene().getWindow();
             root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
        }else{
            stage = (Stage)btnRegister.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("view/Register.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
