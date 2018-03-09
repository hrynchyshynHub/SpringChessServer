package com.chess;

import com.chess.config.MainConfig;
import com.chess.controller_elements.Util;
import com.chess.model.ChessBoard;
import com.chess.network.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import network.OperationType;
import network.RequestCode;
import network.Response;
import network.model.NetworkGameBoard;
import network.model.Player;

import java.util.List;

public class AvailableGames {

    @FXML
    private TableView tableView;

    private ObservableList<NetworkGameBoard> networkGameBoards = FXCollections.observableArrayList();

    @FXML
    private TableColumn<NetworkGameBoard, Integer> idColumn;
    @FXML
    private TableColumn<NetworkGameBoard, Player> firstPlayer;


    @FXML
    public void initialize() {
        Response response= Client.getInstance().send(OperationType.GET_AVAILABLE_GAMES, MainConfig.getUser());
        networkGameBoards.addAll((List<NetworkGameBoard>)response.getData());
        idColumn.setCellValueFactory(new PropertyValueFactory<NetworkGameBoard, Integer>("id"));
        firstPlayer.setCellValueFactory(new PropertyValueFactory<NetworkGameBoard, Player>("firstPlayer"));
        addButtonToTable();
        tableView.setItems(networkGameBoards);
        tableView.setEditable(false);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void addButtonToTable() {
        TableColumn<NetworkGameBoard, Void> colBtn = new TableColumn<>("Join");

        Callback<TableColumn<NetworkGameBoard, Void>, TableCell<NetworkGameBoard, Void>> cellFactory = new Callback<TableColumn<NetworkGameBoard, Void>, TableCell<NetworkGameBoard, Void>>() {
            @Override
            public TableCell<NetworkGameBoard, Void> call(final TableColumn<NetworkGameBoard, Void> param) {
                final TableCell<NetworkGameBoard, Void> cell;
                cell = new TableCell<NetworkGameBoard, Void>() {

                    private final Button btn = new Button("Join");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            NetworkGameBoard data = getTableView().getItems().get(getIndex());
                            data.setSecondPlayer(MainConfig.getUser());

                            Response response = Client.getInstance().send(OperationType.JOIN_GAME, data);

                            if(response.getRequestCode() == RequestCode.ERROR){
                                Util.showAlert("Can`t join", "Error");
                            }else{
                                Stage stage = (Stage)tableView.getScene().getWindow();
                                Util.showAlert("Succesful join", "Success");
                                MainConfig.setNetworkGameBoard((NetworkGameBoard) response.getData());
                                ChessBoard chessBoard = new ChessBoard();
                                Scene scene = new Scene(chessBoard.createContent());
                                chessBoard.initializeBlackPieces();
                                stage.setScene(scene);
                                stage.show();
                            }

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        tableView.getColumns().add(colBtn);
    }
}
