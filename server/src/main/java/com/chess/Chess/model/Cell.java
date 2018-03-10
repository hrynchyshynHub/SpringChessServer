package com.chess.Chess.model;


import com.chess.Chess.model.pieces.Piece;
import com.chess.Chess.util.Color;

import java.io.Serializable;

/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */

public class Cell implements Serializable{
    private boolean isPosibleDestination;    /**available for next move*/
    private Piece piece;
    private int x;
    private char y;
    private boolean isSelected = false;
    private boolean isCheck = false;
    private Color color;
    private String id;


    public Cell() {
    }

    public Cell(int x, char y, Color color) {
        this.x = ++x;
        this.y = y;
        this.color = color;
        this.id = x + "" + y;
    }

    public Cell(String id) {
        this.id = id;
        this.x = id.charAt(0) - 48;
        this.y = id.charAt(1);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "piece=" + piece +
                ", x=" + x +
                ", y=" + y +
                ", color=" + color +
                '}';
    }

    public String getId(){
        return id;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        setPosibleDestination(true);
    }

    public Color getColor() {
        return color;
    }

    public boolean isPosibleDestination() {
        return isPosibleDestination;
    }

    public void setPosibleDestination(boolean posibleDestination) {
        isPosibleDestination = posibleDestination;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public char getY() {
        return y;
    }

    public void setY(char y) {
        this.y = y;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setId(String id) {
        this.id = id;
    }
}
