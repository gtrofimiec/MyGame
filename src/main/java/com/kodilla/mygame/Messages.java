package com.kodilla.mygame;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javax.swing.*;
import static com.kodilla.mygame.PlayerFactory.allPlayers;

public class Messages {

    public static int playersCount;
    public static String humanPlayerName;
    MyGame myGame;

    public void gameIntroduction() {
        try {
            humanPlayerName = JOptionPane.showInputDialog("Podaj swoje imie");
            if (humanPlayerName == null) {
                humanPlayerName = "John Doe";
            }
            allPlayers.get(0).setPlayerName(humanPlayerName);
        } catch (Exception e) {
            JFrame frame = new JFrame("Error");
            JOptionPane.showMessageDialog(frame, "Nie przedstawiles sie. " +
                            "Zostana ustawione dane domyslne: \nImie: JOHN DOE",
                    "Brak danych ...", JOptionPane.ERROR_MESSAGE);
            allPlayers.get(0).setPlayerName("John Doe");
            humanPlayerName = "John Doe";
        }
    }

    public void howManyPlayers() {
        try {
            String temp= JOptionPane.showInputDialog("Ilu przeciwnikow? (1-3)");
            playersCount = Integer.parseInt(temp);
            if(playersCount>3) {
                playersCount = 3;
            }
        } catch (Exception e) {
            JFrame frame = new JFrame("Error");
            JOptionPane.showMessageDialog(frame, "Nie podales liczby przeciwnikow. " +
                     "Zostana ustawione dane domyslne: \nLiczba graczy: 3",
                    "Brak danych ...", JOptionPane.ERROR_MESSAGE);
            playersCount=3;
        }
    }

    public void Instruction() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Instrukcja gry");
            alert.setHeaderText("Zanim rozpoczniemy:");
            alert.setContentText(
                    "1. Gracze, rzucaja kostka, az do momentu, kiedy ktorys z nich wyrzuci kostka liczbe 6." +
                            " Wtedy ustawia swoj pionek na polu startowym (kwadrat w kolorze gracza)." +
                            "\n2. W nastepnej turze rzuca kosta, by nastepnie przesunac pionek o taka liczbe pol " +
                            "w kierunku zgodnym z ruchem wskazowek zegara, ile wyrzuci kostka."+
                            "\n3. Kiedy gracz objedzie pionkiem cala plansze dookola i przekroczy pole startowe - wygrywa.");
            alert.showAndWait();
        });
    }

    public void firstMoveMessage(JFrame frame, Pawn pawn) {
        JOptionPane.showMessageDialog(frame, pawn.player.playerName
                + " wyrzucil 6!\nWchodzi do gry", "Pierwszy ruch",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void lastMoveMessage(JFrame frame, Player player) {
        int userOption=JOptionPane.showConfirmDialog(frame,player.playerName +
                 " wygral ten mecz!. Czy chcesz zaczac od nowa?",
                "Mecz zakonczony",0);
        if(userOption==0) {
            try {
                myGame.newGame();
            } catch (Exception e) {
                System.out.println("error"+e);
            }
        } else {
            Platform.exit();
            System.exit(0);
        }
    }
}