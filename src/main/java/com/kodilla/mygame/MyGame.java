package com.kodilla.mygame;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

public class MyGame extends Application {

    public static PlayerFactory playerFactory = new PlayerFactory();
    public static MovingProcessor movingProc = new MovingProcessor();
    public static LabelsCreator labelsCreator = new LabelsCreator();
    public static Messages message = new Messages();
    public static Terminal terminal = new Terminal();
    public static List<Player> gamePlayers = new ArrayList<>();
    public static Group root = new Group();
    GameBoard gameBoard = new GameBoard();
    ButtonsCreator buttonsCreator = new ButtonsCreator();
    PrimaryStageStyler pSS = new PrimaryStageStyler();
    Dice dices = new Dice();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            Scene scene = new Scene(root, 1000, 800, Color.BROWN);
            playerFactory.createAllPlayers();
            dices.diceCreator();
            gameBoard.drawingGameBoard();
            gameBoard.fillingStartPools();
            labelsCreator.createTerminalLabel();
            buttonsCreator.createThrowDiceButton();
            buttonsCreator.createNewGameButton();
            buttonsCreator.createExitGameButton();

            primaryStage.setScene(scene);
            pSS.stageStyle(primaryStage);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
        message.gameIntroduction();
        message.Instruction();
        newGame();
    }

    public static void newGame() {

        message.howManyPlayers();
        movingProc.gameWon = false;
        playerFactory.createGamePlayers();
        labelsCreator.createPlayerNameLabels();
        GameBoard.placingPawnOnStartPools();
        terminal.terminal.clear();
        terminal.terminalProc("==NOWA GRA==");
        root.getChildren().get(root.getChildren().indexOf(Dice.diceImageView)).setVisible(true);
    }
}