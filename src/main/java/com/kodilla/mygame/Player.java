package com.kodilla.mygame;

import javafx.scene.image.ImageView;

public class Player {
    String playerName;
    ImageView imageView;
    int playerStartPosition;
    int playerPosition;
    int diceCounter;
    boolean isFirstMove;

    public Player(String playerName, ImageView imageView, int playerStartPosition) {
        this.playerName = playerName;
        this.imageView = imageView;
        this.playerStartPosition = playerStartPosition;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(int playerPos) {
        this.playerPosition = playerPos;
    }

    @Override
    public String toString() {
        return playerName;
    }
}