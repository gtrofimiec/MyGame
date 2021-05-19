package com.kodilla.mygame;

import javafx.scene.image.ImageView;

public class Pawn {
    Player player;
    ImageView imageView;
    int pawnStartPosition;
    int pawnActualPosition;
    boolean isFirstMove;
    boolean isReachedEnd;
    int diceCounter;

    public Pawn (Player player, ImageView imageView, int pawnStartPosition) {
        this.player = player;
        this.imageView = imageView;
        this.pawnStartPosition = pawnStartPosition;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Player getPlayer() {
        return player;
    }
}
