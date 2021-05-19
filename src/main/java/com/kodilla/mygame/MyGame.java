package com.kodilla.mygame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.*;

public class MyGame extends Application {
    int playersCount;

    MovingProcessor movingProc = new MovingProcessor();
    Messages message = new Messages();


    List<Player> gamePlayers = new ArrayList<>();
    List<Pawn> player1Pawns = new ArrayList<>();
    List<Pawn> player2Pawns = new ArrayList<>();
    List<Pawn> player3Pawns = new ArrayList<>();
    List<Pawn> player4Pawns = new ArrayList<>();

    // obrazki pionków graczy
    public Image pawnPlayer1Image = new Image((this.getClass().getResource("/pawn1.png")).toString(),30,30,false,false);
    public Image pawnPlayer2Image = new Image((this.getClass().getResource("/pawn2.png")).toString(),30,30,false,false);
    public Image pawnPlayer3Image = new Image((this.getClass().getResource("/pawn3.png")).toString(),30,30,false,false);
    public Image pawnPlayer4Image = new Image((this.getClass().getResource("/pawn4.png")).toString(),30,30,false,false);

    List<ImageView> player1PawnsView = new ArrayList<>();
    List<ImageView> player2PawnsView = new ArrayList<>();
    List<ImageView> player3PawnsView = new ArrayList<>();
    List<ImageView> player4PawnsView = new ArrayList<>();

    //kontruktory graczy
    Player player1 = new Player("Player 1", player1Pawns, player1PawnsView, pawnPlayer1Image,0);
    Player player2 = new Player("Player 2", player2Pawns, player2PawnsView, pawnPlayer2Image,10);
    Player player3 = new Player("Player 3", player3Pawns, player3PawnsView, pawnPlayer3Image,20);
    Player player4 = new Player("Player 4", player4Pawns, player4PawnsView, pawnPlayer4Image,30);


    List<Rectangle> gamePools = new ArrayList<>(); // lista pól planszy

    List<ImageView> dices = new ArrayList<>(); // lista możliwych wyników rzutu kostką

    Group root = new Group();

    // obrazki kostki i poszczególnych wyników
    public Image dice = new Image((this.getClass().getResource("/dice.png")).toString(),80,80,false,false);
    public Image dice1 = new Image((this.getClass().getResource("/dice1.png")).toString(),80,80,false,false);
    public Image dice2 = new Image((this.getClass().getResource("/dice2.png")).toString(),80,80,false,false);
    public Image dice3 = new Image((this.getClass().getResource("/dice3.png")).toString(),80,80,false,false);
    public Image dice4 = new Image((this.getClass().getResource("/dice4.png")).toString(),80,80,false,false);
    public Image dice5 = new Image((this.getClass().getResource("/dice5.png")).toString(),80,80,false,false);
    public Image dice6 = new Image((this.getClass().getResource("/dice6.png")).toString(),80,80,false,false);

    ImageView diceImageView = new ImageView(dice);

    public Label lblTerminal = new Label();
    public Label lblGameResult = new Label();
    public Label lblPlayer1 = new Label(player1.playerName);
    public Label lblPlayer2 = new Label(player2.playerName);
    public Label lblPlayer3 = new Label(player3.playerName);
    public Label lblPlayer4 = new Label(player4.playerName);
    public Button btnNewGame = new Button("Nowa gra");
    public Button btnThrowDice = new Button("Rzut koscia");
    public Button btnExitGame = new Button("Wyjdz");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            Scene scene = new Scene(root, 1000, 800, Color.BROWN);

            // tworzenie listy obrazków kostek
            dices.add(new ImageView(dice1));
            dices.add(new ImageView(dice2));
            dices.add(new ImageView(dice3));
            dices.add(new ImageView(dice4));
            dices.add(new ImageView(dice5));
            dices.add(new ImageView(dice6));

            //Rysowanie planszy
            drawingGameBoard();

            lblTerminal.setLayoutX(800);
            lblTerminal.setLayoutY(100);
            lblTerminal.setFont(Font.font("Arial",15));
            lblTerminal.setStyle("-fx-font-weight: bold");
            lblGameResult.setAlignment(Pos.CENTER);
            lblGameResult.setLayoutX(325);
            lblGameResult.setLayoutY(375);
            lblGameResult.setFont(Font.font("Arial",30));
            lblPlayer1.setLayoutX(600);
            lblPlayer1.setLayoutY(30);
            lblPlayer1.setFont(Font.font("Arial",20));
            lblPlayer1.setStyle("-fx-font-weight: bold");
            lblPlayer2.setLayoutX(600);
            lblPlayer2.setLayoutY(730);
            lblPlayer2.setFont(Font.font("Arial",20));
            lblPlayer2.setStyle("-fx-font-weight: bold");
            lblPlayer3.setLayoutX(70);
            lblPlayer3.setLayoutY(730);
            lblPlayer3.setFont(Font.font("Arial",20));
            lblPlayer3.setStyle("-fx-font-weight: bold");
            lblPlayer4.setLayoutX(70);
            lblPlayer4.setLayoutY(30);
            lblPlayer4.setFont(Font.font("Arial",20));
            lblPlayer4.setStyle("-fx-font-weight: bold");
            btnThrowDice.setLayoutX(800);
            btnThrowDice.setLayoutY(500);
            btnThrowDice.setOnAction(e -> pawnsMove());
            btnNewGame.setOnAction(e->newGame());
            btnNewGame.setLayoutX(800);
            btnNewGame.setLayoutY(570);
            btnExitGame.setOnAction(e -> {
                Platform.exit();
                System.exit(0);
            });
            btnExitGame.setLayoutX(800);
            btnExitGame.setLayoutY(600);
            diceImageView.setX(800);
            diceImageView.setY(400);
            root.getChildren().addAll(diceImageView,
                    lblTerminal,lblPlayer1,lblPlayer2,lblPlayer3,lblPlayer4,
                    btnThrowDice,btnNewGame,btnExitGame);

            primaryStage.setTitle("Chinczyk");
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
        message.gameIntroduction(player1,lblPlayer1);
        newGame();
    }


    public void drawingGameBoard() {

        // Ustalanie wymiarów jednego pola
        int poleWidth = 60;
        int poleHeight = 60;
        int poleMarigin = 5;

        // Miejsce rozpoczęcia rysowania
        int xPos = 430;
        int yPos = -30;

        // Rysowanie planszy
        for(int i=0; i<40; i++) {
            if (i<=4) {
                yPos += poleWidth + poleMarigin;
            } else if ((i>8 && i<=10) || (i>14 && i<=18)) {
                yPos += poleWidth + poleMarigin;
            } else if((i>4 && i <=8) || (i>30 && i<=34) || (i>38 && i<=39)) {
                xPos += poleWidth + poleMarigin;
            } else if ((i>10 && i<=14) || (i>18 && i<=20) || (i>24 && i<=28)) {
                xPos -= poleWidth + poleMarigin;
            } else if ((i>20 && i<=24) || (i>28 && i<=30) || (i>34 && i<=38)) {
                yPos -= poleWidth + poleMarigin;
            }
            gamePools.add(new Rectangle(xPos, yPos, poleWidth, poleHeight));
            gamePools.get(i).setFill(Color.SANDYBROWN);
            root.getChildren().add(gamePools.get(i));
        }

        // Rysowanie pól wyjściowych
        player1.startPools.add((new Rectangle(600, 70, poleWidth, poleHeight)));
        player1.startPools.add((new Rectangle(665, 70, poleWidth, poleHeight)));
        player1.startPools.add((new Rectangle(665, 135, poleWidth, poleHeight)));
        player1.startPools.add((new Rectangle(600, 135, poleWidth, poleHeight)));

        player2.startPools.add((new Rectangle(600, 590, poleWidth, poleHeight)));
        player2.startPools.add((new Rectangle(665, 590, poleWidth, poleHeight)));
        player2.startPools.add((new Rectangle(665, 655, poleWidth, poleHeight)));
        player2.startPools.add((new Rectangle(600, 655, poleWidth, poleHeight)));

        player3.startPools.add((new Rectangle(70, 590, poleWidth, poleHeight)));
        player3.startPools.add((new Rectangle(135, 590, poleWidth, poleHeight)));
        player3.startPools.add((new Rectangle(135, 655, poleWidth, poleHeight)));
        player3.startPools.add((new Rectangle(70, 655, poleWidth, poleHeight)));

        player4.startPools.add((new Rectangle(70, 70, poleWidth, poleHeight)));
        player4.startPools.add((new Rectangle(135, 70, poleWidth, poleHeight)));
        player4.startPools.add((new Rectangle(135, 135, poleWidth, poleHeight)));
        player4.startPools.add((new Rectangle(70, 135, poleWidth, poleHeight)));

        for(int i=0; i<=15; i++) { //dodawanie pól wyjściowych do roota
            if(i<=3) {
                player1.startPools.get(i).setFill(Color.DARKRED);
                root.getChildren().add(player1.startPools.get(i));
            } else if(i>3 && i<=7) {
                player2.startPools.get(i-4).setFill(Color.DARKGREY);
                root.getChildren().add(player2.startPools.get(i-4));
            } else if(i>7 && i<=11) {
                player3.startPools.get(i-8).setFill(Color.DARKGREEN);
                root.getChildren().add(player3.startPools.get(i-8));
            } else if(i>11 && i<=15) {
                player4.startPools.get(i-12).setFill(Color.DARKBLUE);
                root.getChildren().add(player4.startPools.get(i-12));
            }
        }

        gamePools.get(0).setFill(Color.DARKRED); // Oznaczanie pól startowych graczy
        gamePools.get(10).setFill(Color.DARKGREY);
        gamePools.get(20).setFill(Color.DARKGREEN);
        gamePools.get(30).setFill(Color.DARKBLUE);
    }

    public void newGame() {

        message.howManyPlayers();

        movingProc.gameWon = false;
        gamePlayers.clear();
        List<Player> temp = new ArrayList<>();
        temp.add(player1);
        temp.add(player2);
        temp.add(player3);
        temp.add(player4);

        for(int i=0; i<=message.playersCount;i++) {
            gamePlayers.add(temp.get(i));
        }

        for(Player player : gamePlayers) { //tworzenie i rozstawianie pionków na polach startowych
            player.pawnList.clear();
            for (int i = 0; i <= 3; i++) { // <- USTAWIANIE LICZBY PIONKÓW GRACZA
                player.pawnsImageView.add(new ImageView(player.playerPawnImage));
                player.pawnList.add(new Pawn(player, player.pawnsImageView.get(i), i));
                if(!root.getChildren().contains(player.pawnsImageView.get(i))) {
                    root.getChildren().add(player.pawnsImageView.get(i));
                }
                player.pawnList.get(i).imageView.setX(player.startPools.get(i).getX());
                player.pawnList.get(i).imageView.setY(player.startPools.get(i).getY());
                player.pawnList.get(i).isFirstMove = true;
                player.pawnList.get(i).isReachedEnd = false;
                player.pawnList.get(i).diceCounter = 0;
            }
        }

        message.terminal.clear(); // czyszczenie terminala
        terminalProc("==NOWA GRA==");

        root.getChildren().get(root.getChildren().indexOf(diceImageView)).setVisible(true);
    }

    public void pawnsMove() {
        JFrame frame = new JFrame();
        for(Player player : gamePlayers) {
            for (Pawn pawn : player.pawnList) {
                throwADice();
                pawn.diceCounter+= movingProc.diceResult;
                if(pawn.isFirstMove) {
                    movingProc.pawnFirstMove(pawn);
                    if (!pawn.isFirstMove) {
                        drawingPawnMove(pawn);
                        message.firstMoveMessage(frame,pawn);
                        firstMoveTerminal(pawn);
                    } else {
                        regMoveTerminal(pawn);
                    }
                } else if (pawn.diceCounter < 40) {
                    movingProc.pawnMove(pawn);
                    drawingPawnMove(pawn);
                    regMoveTerminal(pawn);
                } else if (pawn.diceCounter>=40) {
                    movingProc.pawnLastMove(pawn);
                    drawingPawnMove(pawn);
                    message.lastMoveMessage(frame, player, this);
                    lastMoveTerminal(player, pawn);
                }
            }
        }
    }

    public void drawingPawnMove(Pawn pawn) {
        pawn.getImageView().setX(gamePools.get(pawn.pawnActualPosition).getX());
        pawn.getImageView().setY(gamePools.get(pawn.pawnActualPosition).getY());
    }

    public void throwADice() {
        movingProc.throwADice();

        for(ImageView dice : dices) { // Usuwanie ewentualnych obrazków poprzednich wyników kostki
            if (root.getChildren().contains(dice)) {
                root.getChildren().remove(dice);
            }
        }
        root.getChildren().get(root.getChildren().indexOf(diceImageView)).setVisible(false);
        dices.get(movingProc.diceResult-1).setX(800);
        dices.get(movingProc.diceResult-1).setY(400);
        root.getChildren().add(dices.get(movingProc.diceResult-1));
    }

    public void terminalProc(String tLine) {
        lblTerminal.setText("");
        message.terminalProcessor(tLine);
        for(int i=0; i<= message.terminal.size()-1; i++) {
            lblTerminal.setText(lblTerminal.getText()+message.terminal.get(i)+"\n");
        }
    }

    public void firstMoveTerminal(Pawn pawn) {
        terminalProc(pawn.player.playerName + " wyrzucil " + movingProc.diceResult
                +"("+pawn.diceCounter+", "+pawn.pawnActualPosition+")" + "!\nWchodzi do gry");
    }

    public void regMoveTerminal(Pawn pawn) {
        terminalProc(pawn.player.playerName + " wyrzucil " + movingProc.diceResult
                +"("+pawn.diceCounter+", "+pawn.pawnActualPosition+")");
    }

    public void lastMoveTerminal(Player player, Pawn pawn) {
        terminalProc(player.playerName + " WYGRYWA!"+"("+pawn.diceCounter+", "+pawn.pawnActualPosition+")");
    }
}