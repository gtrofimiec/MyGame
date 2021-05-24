package com.kodilla.mygame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static com.kodilla.mygame.MyGame.gamePlayers;

public class PlayerFactory {

    public static List<Player> allPlayers = new ArrayList<>();
    Image playerPawnImage;
    public Player player;

    public void createAllPlayers() {
        allPlayers.clear();
        for(int i=1; i<=4; i++) {
            List<Rectangle> startPoolsList = new ArrayList<>();
            List<ImageView> pawnsImageViewList = new ArrayList<>();
            List<Pawn> pawnList = new ArrayList<>();

            allPlayers.add(new Player("Player"+i, pawnList, pawnsImageViewList,
                    playerPawnImage, startPoolsList, (i*10)-10));
            player = allPlayers.get(i-1);
            GameBoard.addStartPoolsList(player);
            stylePlayer(i);
        }
    }

    public void createGamePlayers() {
        gamePlayers.clear();
        for(int i=0; i<=Messages.playersCount; i++) {
            gamePlayers.add(allPlayers.get(i));
        }
    }

    public void stylePlayer(int i) {
        playerPawnImage= new Image((this.getClass().getResource
                ("/Pawn"+i+".png")).toString(),30,30,
                false,false);
        for(int y=1; y<=4; y++) {
            player.pawnsImageViewList.add(new ImageView(playerPawnImage));
            player.pawnList.add(new Pawn(player, player.pawnsImageViewList.get(y - 1),
                    player.playerStartPosition));
        }
    }
}