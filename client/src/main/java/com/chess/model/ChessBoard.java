package com.chess.model;


import com.chess.Client;
import com.chess.config.MainConfig;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;
import network.OperationHandlers;
import network.Response;


public class ChessBoard {
    private enum State {
        SEND,
        GET
    }


    public static final int BOARD_SIZE = 8;
    public static final int CELL_SIZE = 60;

    private Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];
    private GridPane root = new GridPane();

    private Cell selectedCell = null;
    private Cell enemyCell = null;
    private State state;

    public ChessBoard() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(500),
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

    private Piece createPiece(PieceType type, int x, int y) {
        Piece piece = new Piece(type, x, y);
        return piece;
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