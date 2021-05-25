package com.kodilla.mygame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

import static com.kodilla.mygame.MyGame.gamePlayers;
import static com.kodilla.mygame.MyGame.root;
import static com.kodilla.mygame.PlayerFactory.allPlayers;

public class GameBoard {

    public static List<Rectangle> gamePools = new ArrayList<>();

    public static int poleWidth = 60;
    public static int poleHeight = 60;
    public static int poleMargin = 5;

    public void drawingGameBoard() {

        int xStartPos = 430;
        int yStartPos = -30;

        for (int i = 0; i < 40; i++) {
            if (i <= 4) {
                yStartPos += poleWidth + poleMargin;
            } else if ((i > 8 && i <= 10) || (i > 14 && i <= 18)) {
                yStartPos += poleWidth + poleMargin;
            } else if ((i > 4 && i <= 8) || (i > 30 && i <= 34) || (i > 38 && i <= 39)) {
                xStartPos += poleWidth + poleMargin;
            } else if ((i > 10 && i <= 14) || (i > 18 && i <= 20) || (i > 24 && i <= 28)) {
                xStartPos -= poleWidth + poleMargin;
            } else if ((i > 20 && i <= 24) || (i > 28 && i <= 30) || (i > 34 && i <= 38)) {
                yStartPos -= poleWidth + poleMargin;
            }
            gamePools.add(new Rectangle(xStartPos, yStartPos, poleWidth, poleHeight));
            gamePools.get(i).setFill(Color.SANDYBROWN);
            root.getChildren().add(gamePools.get(i));
        }
        gamePools.get(0).setFill(Color.DARKRED);
        gamePools.get(10).setFill(Color.DARKGREY);
        gamePools.get(20).setFill(Color.DARKGREEN);
        gamePools.get(30).setFill(Color.DARKBLUE);

    }

    public void fillingStartPools() {
        for (int i = 0; i <= 15; i++) {
            if (i <= 3) {
                fillStartPool(0, i, Color.DARKRED);
            } else if (i > 3 && i <= 7) {
                fillStartPool(1, i-4, Color.DARKGREY);
            } else if (i > 7 && i <= 11) {
                fillStartPool(2, i-8, Color.DARKGREEN);
            } else if (i > 11 && i <= 15) {
                fillStartPool(3, i-12, Color.DARKBLUE);
            }
        }
    }

    private void fillStartPool(int playerID, int poolID, Color color) {
        allPlayers.get(playerID).startPoolsList.get(poolID).setFill(color);
        root.getChildren().add(allPlayers.get(playerID).startPoolsList.get(poolID));
    }

    public static void addStartPoolsList(Player player) {
        if (player.playerName.contains("Player1")) {
            drawingStartPools(player, 600, 70);
        } else if (player.playerName.contains("Player2")) {
            drawingStartPools(player, 600, 590);
        } else if (player.playerName.contains("Player3")) {
            drawingStartPools( player, 70, 590);
        } else if (player.playerName.contains("Player4")) {
            drawingStartPools(player, 70, 70);
        }
    }

    private static void drawingStartPools(Player player, int x, int y) {
       player.startPoolsList.add((new Rectangle(x, y, poleWidth, poleHeight)));
       player.startPoolsList.add((new Rectangle(x + 65, y, poleWidth, poleHeight)));
       player.startPoolsList.add((new Rectangle(x + 65, y + 65, poleWidth, poleHeight)));
       player.startPoolsList.add((new Rectangle(x, y + 65, poleWidth, poleHeight)));
    }

    public static void placingPawnOnStartPools() {
        for(Player player : gamePlayers) {
            for (int i = 0; i <= 3; i++) {
                if(!root.getChildren().contains(player.pawnsImageViewList.get(i))) {
                    root.getChildren().add(player.pawnsImageViewList.get(i));
                }
                player.pawnsImageViewList.get(i).setX(player.startPoolsList.get(i).getX());
                player.pawnsImageViewList.get(i).setY(player.startPoolsList.get(i).getY());
                player.pawnList.get(i).isFirstMove = true;
                player.pawnList.get(i).isReachedEnd = false;
                player.pawnList.get(i).diceCounter = 0;
            }
        }
    }
}