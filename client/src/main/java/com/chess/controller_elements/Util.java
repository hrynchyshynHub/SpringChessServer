package com.chess.controller_elements;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

public class Util {

    public static void showAlert(String title, String headerText) {
        showAlert(title, headerText, "");
    }

    public static void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static void showConnectionErrorNotice() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Connection error");
        alert.setHeaderText("Can't connect with server");
        alert.setContentText("");
        alert.showAndWait();
    }

    public static boolean confirmDialog(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public static boolean allMatchPattern(String pattern, String... values) {
        return Arrays.stream(values).allMatch(value -> Pattern.matches(pattern, value));
    }
}