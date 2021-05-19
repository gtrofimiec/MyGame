package com.kodilla.mygame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String playerName;
    Image playerPawnImage;
    int playerStartPosition;
    List<Pawn> pawnList;
    List<ImageView> pawnsImageView;
    List<Rectangle> startPools = new ArrayList<>();

    public Player(String playerName, List<Pawn> pawnList, List<ImageView> pawnsImageView,
                  Image playerPawnImage, int playerStartPosition) {
        this.playerName = playerName;
        this.pawnList = pawnList;
        this.pawnsImageView = pawnsImageView;
        this.playerPawnImage = playerPawnImage;
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