package com.chess.model;


import com.chess.Client;
import com.chess.config.MainConfig;
import game.chess.ChessFx;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;
import network.OperationHandlers;
import network.Response;

import java.io.IOException;
import java.net.URL;


public class ChessBoard {
    private enum State {
        SEND,
        GET
    }


    public static final int BOARD_SIZE = 8;
    public static final int CELL_SIZE = 60;

    private Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];
    private BorderPane root = new BorderPane();

    private Cell selectedCell = null;
    private Cell enemyCell = null;
    private State state;

    public ChessBoard() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(100),
                        ev -> {
                            if (state.equals(State.GET)) {
                                getCell();
                            }
                        }
                ));
        timeline.play();
    }

    public Parent createContent() {
        boolean isWhite = true;
        Color color = Color.WHITE;
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    color = Color.WHITE;
                } else {
                    color = Color.BLACK;
                }
                board[i][j] = new Cell(color, i, (char) (j + 97), this);
                GridPane.setRowIndex(board[i][j], 8 - i);
                GridPane.setColumnIndex(board[i][j], j);
                root.getChildren().addAll(board[i][j]);
                System.out.print('[' + board[i][j].getCellId() + board[i][j].getColor() + ']');
            }
            System.out.println();
        }
        return root;
    }

    public void initializeWhitePieces() {
        state = State.SEND;
    }

    public void initializeBlackPieces() {
        state = State.GET;
    }

    public Pane initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL url = ChessFx.class.getClassLoader().getResource("view/javafx/ChessFx.fxml");
            loader.setLocation(url);
            root = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(ChessFx.class.getResource("/view/javafx/ChessBoard.fxml"));
            GridPane chessBoard = loader1.load();
            chessBoard.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            for (Node node : chessBoard.getChildren()) {
                Integer x = GridPane.getRowIndex(node);
                Integer y = GridPane.getColumnIndex(node);
                int row = x == null ? 0 : x;
                int col = y == null ? 0 : y;
                if (node instanceof Pane) {
                    node.setId("pane"+row+"_"+col);
                }
                row = 8-row-1;

                if ((row+col)%2 == 1) {
                    node.setStyle("-fx-background-color: rgb(255,255,255);");
                } else {
                    node.setStyle("-fx-background-color: rgb(128,128,128);");
                }

                if(row == 1) {
                    ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("/view/images/bpawn.png")));
                    if (node instanceof Pane){
                        ((Pane)node).getChildren().add(img);
                    }
                }
                else if(row == 6) {
                    ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("/view/images/wpawn.png")));
                    if (node instanceof Pane){
                        ((Pane)node).getChildren().add(img);
                    }
                }
                else if (row == 0) {
                    if (col == 0 || col== 7) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("/view/images/brook.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    } else if (col == 1 || col == 6) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("/view/images/bknight.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    } else if (col == 2 || col == 5) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("/view/images/bbishop.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    }
                    else if (col == 4) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("/view/images/bking.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    }
                    else if (col == 3) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("/view/images/bqueen.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    }

                } else if (row == 7) {
                    if (col == 0 || col== 7) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("/view/images/wrook.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    } else if (col == 1 || col == 6) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("/view/images/wknight.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    } else if (col == 2 || col == 5) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("/view/images/wbishop.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    }
                    else if (col == 4) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("/view/images/wking.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    }
                    else if (col == 3) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("/view/images/wqueen.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    }
                }
                else {
                    ImageView img = new ImageView();
                    if (node instanceof Pane){
                        ((Pane)node).getChildren().add(img);
                    }
                }

            }
            root.setCenter(chessBoard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Cell getCellById(String id) {
        Cell foundCell = null;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getCellId().equalsIgnoreCase(id)) foundCell = board[i][j];
            }
        }
        return foundCell;
    }

    public Cell getSelectedCell() {
        return selectedCell;
    }

    public void sendCell() {
        Client.getInstance().send(
            OperationHandlers.SELECT_CELL,
            MainConfig.getNetworkGameBoard().getId(),
            MainConfig.getUser().getUsername(),
            selectedCell.getCellId()
        );
    }

    public void getCell() {
        Response res = Client.getInstance().send(
            OperationHandlers.GET_CELL,
            MainConfig.getNetworkGameBoard().getId(),
            MainConfig.getUser().getUsername()
        );

        Object response = res.getData();

        if (response != null) {
            String cellId = (String) response;
            System.out.println("Get from server " + cellId);

            if (enemyCell != null) {
                enemyCell.hide();
            }

            enemyCell = getCellById(cellId);
            enemyCell.highlight(Color.BLUE);

            state = State.SEND;
        }

    }

    public void setSelectedCell(Cell selectedCell) {
        if (state.equals(State.GET)) {
            return;
        }

        if (this.selectedCell != null) {
            this.selectedCell.hide();
            this.selectedCell.setStrokeType(StrokeType.INSIDE);
            this.selectedCell.setStrokeWidth(0);
        }
        this.selectedCell = selectedCell;
        this.selectedCell.highlight(Color.RED);

        sendCell();
        state = State.GET;
    }
}