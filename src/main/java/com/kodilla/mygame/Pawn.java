package com.kodilla.mygame;

import javafx.scene.image.ImageView;

public class Pawn {

    Player player;
    public ImageView imageView;
    public int pawnStartPosition;
    public int pawnActualPosition;
    public boolean isFirstMove;
    public boolean isReachedEnd;
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

    @Override
    public String toString() {
        return "Pawn{" + "player=" + player + '}';
    }
}
