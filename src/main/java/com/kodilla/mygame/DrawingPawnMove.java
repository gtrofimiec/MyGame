package com.kodilla.mygame;

import javax.swing.*;

import static com.kodilla.mygame.GameBoard.gamePools;
import static com.kodilla.mygame.MyGame.gamePlayers;

public class DrawingPawnMove {
    MyGame myGame;
    MovingProcessor movingProc = new MovingProcessor();
    Messages message = new Messages();
    Terminal terminal = new Terminal();
    Dice dice = new Dice();

    public void throwADice() {
        dice.throwADice();
        Dice.diceImageView.setImage(Dice.dicesList.get(Dice.diceResult -1));
    }

    public void pawnsMove() {
        JFrame frame = new JFrame();
        for(Player player : gamePlayers) {
            for (Pawn pawn : player.pawnList) {
                throwADice();
                if(pawn.isFirstMove && Dice.diceResult==6) {
                    movingProc.pawnFirstMove(pawn);
                    drawingPawnMove(pawn);
                    message.firstMoveMessage(frame, pawn);
                    terminal.firstMoveTerminal(pawn);
                    terminal.regMoveTerminal(pawn);
                } else if (pawn.isFirstMove && pawn.diceCounter==0) {
                    terminal.regMoveTerminal(pawn);

                } else if (pawn.diceCounter < 40) {
                    pawn.diceCounter+= Dice.diceResult;

                    movingProc.pawnMove(pawn);
                    drawingPawnMove(pawn);
                    terminal.regMoveTerminal(pawn);
                } else {
                    pawn.diceCounter+= Dice.diceResult;
                    movingProc.pawnLastMove(pawn);
                    drawingPawnMove(pawn);
                    message.lastMoveMessage(frame, player);
                    terminal.lastMoveTerminal(player, pawn);
                }
            }
        }
    }

    public void drawingPawnMove(Pawn pawn) {
        pawn.getImageView().setX(gamePools.get(pawn.pawnActualPosition).getX());
        pawn.getImageView().setY(gamePools.get(pawn.pawnActualPosition).getY());
    }
}