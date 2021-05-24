package com.kodilla.mygame;

import static com.kodilla.mygame.Dice.diceResult;

public class MovingProcessor {

    boolean gameWon;

    public void pawnFirstMove(Pawn pawn) {
            pawn.pawnActualPosition = pawn.getPlayer().playerStartPosition;
            pawn.diceCounter = 0;
            pawn.isFirstMove = false;
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