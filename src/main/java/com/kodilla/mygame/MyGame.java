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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyGame extends Application {
    int diceResult = 0;

    List<Player> gamePlayers = new ArrayList<>(); // lista graczy

    final List<Rectangle> gamePools = new ArrayList<>(); // lista pól planszy
    final List<Circle> startPoolsPlayer1 = new ArrayList<>(); // listy pół startowych każdego gracza
    final List<Circle> startPoolsPlayer2 = new ArrayList<>();
    final List<Circle> startPoolsPlayer3 = new ArrayList<>();
    final List<Circle> startPoolsPlayer4 = new ArrayList<>();

    final List<ImageView> dices = new ArrayList<>(); // lista możliwych wyników rzutu kostką

    List<String> terminal = new ArrayList<>(); //lista zawartości terminala gry

    Group root = new Group();

    // obrazki pionków graczy
    public Image pawnPlayer1 = new Image(
            (this.getClass().getResource("/pawn1.png")).toString(),
            30,30,false,false);
    public Image pawnPlayer2 = new Image(
            (this.getClass().getResource("/pawn2.png")).toString(),
            30,30,false,false);
    public Image pawnPlayer3 = new Image(
            (this.getClass().getResource("/pawn3.png")).toString(),
            30,30,false,false);
    public Image pawnPlayer4 = new Image(
            (this.getClass().getResource("/pawn4.png")).toString(),
            30,30,false,false);

    // obrazki kostki i poszczególnych wyników
    public Image dice = new Image(
            (this.getClass().getResource("/dice.png")).toString(),
            80,80,false,false);
    public Image dice1 = new Image(
            (this.getClass().getResource("/dice1.png")).toString(),
            80,80,false,false);
    public Image dice2 = new Image(
            (this.getClass().getResource("/dice2.png")).toString(),
            80,80,false,false);
    public Image dice3 = new Image(
            (this.getClass().getResource("/dice3.png")).toString(),
            80,80,false,false);
    public Image dice4 = new Image(
            (this.getClass().getResource("/dice4.png")).toString(),
            80,80,false,false);
    public Image dice5 = new Image(
            (this.getClass().getResource("/dice5.png")).toString(),
            80,80,false,false);
    public Image dice6 = new Image(
            (this.getClass().getResource("/dice6.png")).toString(),
            80,80,false,false);

    ImageView pawnPlayer1View = new ImageView(pawnPlayer1);
    ImageView pawnPlayer2View = new ImageView(pawnPlayer2);
    ImageView pawnPlayer3View = new ImageView(pawnPlayer3);
    ImageView pawnPlayer4View = new ImageView(pawnPlayer4);
    ImageView diceImageView = new ImageView(dice);

    public Label lblTerminal = new Label();
    public Label lblGameResult = new Label();
    public Label lblPlayer1 = new Label("Gracz 1");
    public Label lblPlayer2 = new Label("Gracz 2");
    public Label lblPlayer3 = new Label("Gracz 3");
    public Label lblPlayer4 = new Label("Gracz 4");
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
            lblPlayer1.setLayoutX(650);
            lblPlayer1.setLayoutY(10);
            lblPlayer1.setFont(Font.font("Arial",20));
            lblPlayer1.setStyle("-fx-font-weight: bold");
            lblPlayer2.setLayoutX(650);
            lblPlayer2.setLayoutY(750);
            lblPlayer2.setFont(Font.font("Arial",20));
            lblPlayer2.setStyle("-fx-font-weight: bold");
            lblPlayer3.setLayoutX(70);
            lblPlayer3.setLayoutY(750);
            lblPlayer3.setFont(Font.font("Arial",20));
            lblPlayer3.setStyle("-fx-font-weight: bold");
            lblPlayer4.setLayoutX(70);
            lblPlayer4.setLayoutY(10);
            lblPlayer4.setFont(Font.font("Arial",20));
            lblPlayer4.setStyle("-fx-font-weight: bold");
            btnThrowDice.setLayoutX(800);
            btnThrowDice.setLayoutY(500);
            btnNewGame.setOnAction(e->newGame());
            btnNewGame.setLayoutX(800);
            btnNewGame.setLayoutY(570);
            btnExitGame.setOnAction(e-> Platform.exit());
            btnExitGame.setLayoutX(800);
            btnExitGame.setLayoutY(600);
            diceImageView.setX(800);
            diceImageView.setY(400);
            root.getChildren().addAll(pawnPlayer1View,pawnPlayer2View,
                    pawnPlayer3View,pawnPlayer4View,diceImageView,
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
        gameInstruction();
        newGame();
    }

    public void gameInstruction() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Instrukcja gry");
            alert.setHeaderText("Zanim rozpoczniemy:");
            alert.setContentText(
                    "1. Gracze, rzucaja kostka, az do momentu, kiedy ktorys z nich wyrzuci kostka liczbe 6" +
                            "– wtedy ustawia swoj pionek na polu startowym (kropka w kolorze gracza)." +
                    "\n2. W nastepnej turze rzuca kosta, by nastepnie przesunac pionek o taka liczbe pol " +
                            "w kierunku zgodnym z ruchem wskazowek zegara, ile wyrzuci kostka."+
                    "\n3. Kiedy gracz obejdzie pionkiem cala plansze dookola i przekroczy pole startowe - wygrywa.");
            alert.showAndWait();
        });
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
        // Player1
        startPoolsPlayer1.add((new Circle(650, 70, 30)));
        startPoolsPlayer1.add((new Circle(715, 70, 30)));
        startPoolsPlayer1.add((new Circle(715, 135, 30)));
        startPoolsPlayer1.add((new Circle(650, 135, 30)));

        // Player2
        startPoolsPlayer2.add((new Circle(650, 650, 30)));
        startPoolsPlayer2.add((new Circle(715, 650, 30)));
        startPoolsPlayer2.add((new Circle(715, 715, 30)));
        startPoolsPlayer2.add((new Circle(650, 715, 30)));

        // Player3
        startPoolsPlayer3.add((new Circle(70, 650, 30)));
        startPoolsPlayer3.add((new Circle(135, 650, 30)));
        startPoolsPlayer3.add((new Circle(135, 715, 30)));
        startPoolsPlayer3.add((new Circle(70, 715, 30)));

        // Player4
        startPoolsPlayer4.add((new Circle(70, 70, 30)));
        startPoolsPlayer4.add((new Circle(135, 70, 30)));
        startPoolsPlayer4.add((new Circle(135, 135, 30)));
        startPoolsPlayer4.add((new Circle(70, 135, 30)));

        for(int i=0; i<=15; i++) {
            if(i<=3) {
                startPoolsPlayer1.get(i).setFill(Color.DARKRED);
                root.getChildren().add(startPoolsPlayer1.get(i));
            } else if(i>3 && i<=7) {
                startPoolsPlayer2.get(i-4).setFill(Color.DARKGREY);
                root.getChildren().add(startPoolsPlayer2.get(i-4));
            } else if(i>7 && i<=11) {
                startPoolsPlayer3.get(i-8).setFill(Color.DARKGREEN);
                root.getChildren().add(startPoolsPlayer3.get(i-8));
            } else if(i>11 && i<=15) {
                startPoolsPlayer4.get(i-12).setFill(Color.DARKBLUE);
                root.getChildren().add(startPoolsPlayer4.get(i-12));
            }
        }

        // Oznaczanie pól startowych graczy
        gamePools.get(0).setFill(Color.DARKRED);
        gamePools.get(10).setFill(Color.DARKGREY);
        gamePools.get(20).setFill(Color.DARKGREEN);
        gamePools.get(30).setFill(Color.DARKBLUE);
    }

    public void newGame() {

        //rozstawianie pionków na polach startowych
        pawnPlayer1View.setX(startPoolsPlayer1.get(0).getCenterX());
        pawnPlayer1View.setY(startPoolsPlayer1.get(0).getCenterY());
        pawnPlayer2View.setX(startPoolsPlayer2.get(0).getCenterX());
        pawnPlayer2View.setY(startPoolsPlayer2.get(0).getCenterY());
        pawnPlayer3View.setX(startPoolsPlayer3.get(0).getCenterX());
        pawnPlayer3View.setY(startPoolsPlayer3.get(0).getCenterY());
        pawnPlayer4View.setX(startPoolsPlayer4.get(0).getCenterX());
        pawnPlayer4View.setY(startPoolsPlayer4.get(0).getCenterY());

        //Dodawanie graczy do rozgrywki
        Player player1 = new Player("Player 1",pawnPlayer1View,0);
        Player player2 = new Player("Player 2",pawnPlayer2View,10);
        Player player3 = new Player("Player 3",pawnPlayer3View,20);
        Player player4 = new Player("Player 4",pawnPlayer4View,30);

        gamePlayers.add(player1);
        gamePlayers.add(player2);
        gamePlayers.add(player3);
        gamePlayers.add(player4);

        lblTerminal.setText("==NOWA GRA==");
        // ukrywanie wyświetlonych poprzednio kostek

        root.getChildren().get(root.getChildren().indexOf(diceImageView)).setVisible(true);
        btnThrowDice.setOnAction(e->playersMove());
        for (int i=0; i<=gamePlayers.size()-1; i++) {
            gamePlayers.get(i).isFirstMove = true;
        }
    }

    public void playersMove() {
        for (int i = 0; i <= gamePlayers.size() - 1; i++) {
            playerMove(gamePlayers.get(i));
        }
    }

    public void playerMove(Player player) {
        int diceCounter=0;

        // procedura gdy pierwszy ruch gracza
        if(player.isFirstMove) {
            diceResult = throwADice();
            // Wstawianie pionka na planszę po wyrzuceniu "6"
            if(diceResult==6) {
                player.getImageView().setX(gamePools.get(player.playerStartPosition).getX());
                player.getImageView().setY(gamePools.get(player.playerStartPosition).getY());
                player.setPlayerPosition(player.playerStartPosition);
                player.isFirstMove = false;
                terminal(player.playerName+" wyrzucil "+diceResult
                        +"!\nWchodzi do gry");
            } else {
                terminal(player.playerName + " wyrzucil " + diceResult);
            }
        // procedura przy kolejnych ruchach
        } else {
            diceResult = throwADice();
            player.diceCounter += diceResult;

            player.setPlayerPosition(player.getPlayerPosition()+diceResult);
            terminal(player.playerName + " wyrzucil " + diceResult
                    + "("+player.diceCounter+","+player.playerPosition+")");
            // jeśli gracz nie osiagnął jeszcze mety
            if (player.diceCounter < 40) {
                //jeśli to "żywy" gracz
                if(player.playerStartPosition==0) {
                    player.getImageView().setX(gamePools.get(player.getPlayerPosition()).getX());
                    player.getImageView().setY(gamePools.get(player.getPlayerPosition()).getY());
                // jeśli gracz to komputer
                } else if (player.playerStartPosition > 0) {
                    if (player.getPlayerPosition() < 40) {
                        player.getImageView().setX(gamePools.get(player.getPlayerPosition()).getX());
                        player.getImageView().setY(gamePools.get(player.getPlayerPosition()).getY());
                    } else if (player.getPlayerPosition() >= 40) {
                        player.setPlayerPosition(player.getPlayerPosition() - 40);
                        if (player.getPlayerPosition() < player.playerStartPosition) {
                            player.getImageView().setX(gamePools.get(player.getPlayerPosition()).getX());
                            player.getImageView().setY(gamePools.get(player.getPlayerPosition()).getY());
                        }
                    }
                }

            //jeśli gracz osiagnął metę - wygrana
            } else if (player.diceCounter >= 40) {
                player.getImageView().setX(gamePools.get(player.playerStartPosition).getX());
                player.getImageView().setY(gamePools.get(player.playerStartPosition).getY());
                terminal(player.playerName + " WYGRYWA!");
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("WYGRANA!");
                    alert.setHeaderText(player.playerName+ " wygral ten mecz!");
                    alert.setContentText("Good game :)");
                    alert.showAndWait();
                });
            }
        }
    }

    public int throwADice() {
        int result;
        Random throwing = new Random();
        result = throwing.nextInt(6)+1;

        // Usuwanie ewentualnych obrazków poprzednich wyników kostki
        for(int i=0; i<=5; i++) {
            if (root.getChildren().contains(dices.get(i))) {
                root.getChildren().remove(dices.get(i));
            }
        }
        //ukrywanie obrazka kostki i wyświetlanie kostki z wyrzuconym wynikiem
        root.getChildren().get(root.getChildren().
                indexOf(diceImageView)).setVisible(false);
        dices.get(result-1).setX(800);
        dices.get(result-1).setY(400);
        root.getChildren().add(dices.get(result-1));

        //Wrzutka wyniku do terminala gry
        lblTerminal.setText(lblTerminal.getText()+" wyrzucił "+result+"\n");
        return result;
    }

    //obsługa terminala gry
    public void terminal(String tLine) {
        lblTerminal.setText("");
        if(terminal.size()<9) {
            terminal.add(tLine);
        } else {
            terminal.remove(0);
            terminal.add(tLine);
        }
        for(int i=0; i<= terminal.size()-1; i++) {
            lblTerminal.setText(lblTerminal.getText()+terminal.get(i)+"\n");
        }
    }
}