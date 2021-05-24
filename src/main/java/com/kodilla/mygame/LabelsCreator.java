package com.kodilla.mygame;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

import static com.kodilla.mygame.PlayerFactory.allPlayers;

public class LabelsCreator {
    public static Label lblHumanPlayer;
    public static Label lblTerminal = new Label();

    MyGame myGame;

    public void createPlayerNameLabels() {
        for(Player player : allPlayers) {
            javafx.scene.control.Label label = new javafx.scene.control.Label(player.playerName);
            label.setFont(Font.font("Arial", 20));
            label.setStyle("-fx-font-weight: bold");
            if(player == allPlayers.get(0)) {
                lblHumanPlayer=label;
                lblHumanPlayer.setLayoutX(600);
                lblHumanPlayer.setLayoutY(30);
                myGame.root.getChildren().add(lblHumanPlayer);
            } else if(player == allPlayers.get(1)) {
                label.setLayoutX(600);
                label.setLayoutY(730);
                myGame.root.getChildren().add(label);
            } else if(player == allPlayers.get(2)) {
                label.setLayoutX(70);
                label.setLayoutY(730);
                myGame.root.getChildren().add(label);
            } else if(player == allPlayers.get(3)) {
                label.setLayoutX(70);
                label.setLayoutY(30);
                myGame.root.getChildren().add(label);
            }
        }
    }

    public void createTerminalLabel() {
        lblTerminal.setLayoutX(800);
        lblTerminal.setLayoutY(100);
        lblTerminal.setFont(Font.font("Arial",15));
        lblTerminal.setStyle("-fx-font-weight: bold");
        myGame.root.getChildren().add(lblTerminal);
    }
}
