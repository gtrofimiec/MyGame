package com.kodilla.mygame;

import javafx.application.Platform;
import javafx.scene.control.Button;

public class ButtonsCreator {

    MyGame myGame;
    DrawingPawnMove drawingPawnMove = new DrawingPawnMove();
    public Button btnThrowDice = new Button("Rzut koscia");
    public Button btnNewGame = new Button("Nowa gra");
    public Button btnExitGame = new Button("Koniec gry");

    public void createThrowDiceButton() {
        setLayout(btnThrowDice,800,500);
        btnThrowDice.setOnAction(e -> drawingPawnMove.pawnsMove());
        myGame.root.getChildren().add(btnThrowDice);
    }

    public void createNewGameButton() {
        setLayout(btnNewGame,800,570);
        btnNewGame.setOnAction(e-> myGame.newGame());
        myGame.root.getChildren().add(btnNewGame);
    }

    public void createExitGameButton() {
        setLayout(btnExitGame,800,600);
        btnExitGame.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });
        myGame.root.getChildren().add(btnExitGame);
    }

    private void setLayout(Button button, int x, int y) {
        button.setLayoutX(x);
        button.setLayoutY(y);
    }
}