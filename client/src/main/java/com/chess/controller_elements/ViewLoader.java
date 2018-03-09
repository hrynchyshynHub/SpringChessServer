package com.chess.controller_elements;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.chess.config.ApplicationProperties;

import java.io.IOException;

public class ViewLoader {
    private Node source;

    public ViewLoader(Node source) {
        this.source = source;
    }

    public void loadScene(String path, String title) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root;

        try {
            root = loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
            return;
        }

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root, ApplicationProperties.getWidth(), ApplicationProperties.getHeight()));
        stage.show();
        source.getScene().getWindow().hide();
    }
}
