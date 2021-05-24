package com.kodilla.mygame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.kodilla.mygame.MyGame.root;

public class Dice {

    public static List<Image> dicesList = new ArrayList<>();
    public static ImageView diceImageView = new ImageView();
    public static int diceResult;

    public Image dice = new Image((this.getClass().getResource("/dice.png")).toString(),80,80,false,false);
    public Image dice1 = new Image((this.getClass().getResource("/dice1.png")).toString(),80,80,false,false);
    public Image dice2 = new Image((this.getClass().getResource("/dice2.png")).toString(),80,80,false,false);
    public Image dice3 = new Image((this.getClass().getResource("/dice3.png")).toString(),80,80,false,false);
    public Image dice4 = new Image((this.getClass().getResource("/dice4.png")).toString(),80,80,false,false);
    public Image dice5 = new Image((this.getClass().getResource("/dice5.png")).toString(),80,80,false,false);
    public Image dice6 = new Image((this.getClass().getResource("/dice6.png")).toString(),80,80,false,false);

    public void diceCreator() {
        // tworzenie listy obrazków kostek
        dicesList.add(dice1);
        dicesList.add(dice2);
        dicesList.add(dice3);
        dicesList.add(dice4);
        dicesList.add(dice5);
        dicesList.add(dice6);
        diceImageView.setImage(dice);
        diceImageView.setX(800);
        diceImageView.setY(400);
        root.getChildren().add(diceImageView);
    }

    public int throwADice() {
        Random throwing = new Random();
        diceResult=0;
        diceResult = throwing.nextInt(6) + 1;
        return diceResult;
    }
}