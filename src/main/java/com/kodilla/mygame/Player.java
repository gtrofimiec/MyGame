package com.kodilla.mygame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import java.util.List;

public class Player {
    String playerName;
    public int playerStartPosition;
    public List<Pawn> pawnList;
    public List<ImageView> pawnsImageViewList;
    Image playerPawnImage;
    List<Rectangle> startPoolsList;

    public Player(String playerName, List<Pawn> pawnList, List<ImageView> pawnsImageViewList,
                  Image playerPawnImage, List<Rectangle> startPoolsList, int playerStartPosition) {
        this.playerName = playerName;
        this.pawnList = pawnList;
        this.pawnsImageViewList = pawnsImageViewList;
        this.playerPawnImage = playerPawnImage;
        this.startPoolsList = startPoolsList;
        this.playerStartPosition = playerStartPosition;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return playerName;
    }
}