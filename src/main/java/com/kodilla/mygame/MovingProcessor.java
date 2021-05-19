package com.kodilla.mygame;

import java.util.Random;

public class MovingProcessor {

    int diceResult;
    boolean gameWon;

    public int throwADice() {
        Random throwing = new Random();
        diceResult=0;
        diceResult = throwing.nextInt(6) + 1;
        return diceResult;
    }

    public void pawnFirstMove(Pawn pawn) {
        if (diceResult == 6) {
            pawn.pawnActualPosition = pawn.getPlayer().playerStartPosition;
            pawn.diceCounter = 0;
            pawn.isFirstMove = false;
        } else {
            pawn.pawnActualPosition = -1;
        }
    }

    public void pawnLastMove(Pawn pawn) {
        pawn.isReachedEnd = true;
        pawn.pawnActualPosition = pawn.getPlayer().playerStartPosition;
        pawn.diceCounter = 0;
        gameWon = true;
    }

    public void pawnMove(Pawn pawn) {
        pawn.pawnActualPosition += diceResult;
        if (pawn.pawnActualPosition >= 40) {
            pawn.pawnActualPosition -= 40;
        }
    }
}