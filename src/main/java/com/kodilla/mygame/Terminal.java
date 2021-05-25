package com.kodilla.mygame;

import java.util.ArrayList;
import java.util.List;

import static com.kodilla.mygame.LabelsCreator.lblTerminal;

public class Terminal {

    Dice dice;
    List<String> terminal = new ArrayList<>();

    public void terminalProc(String tLine) {
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

    public void firstMoveTerminal(Pawn pawn) {
        terminalProc(pawn.player.playerName + " wyrzucil " + dice.diceResult + "!\nWchodzi do gry");
    }

    public void regMoveTerminal(Pawn pawn) {
        terminalProc(pawn.player.playerName + " wyrzucil " + dice.diceResult);
    }

    public void lastMoveTerminal(Player player, Pawn pawn) {
        terminalProc(player.playerName + " WYGRYWA!"+"("+pawn.diceCounter+", "+pawn.pawnActualPosition+")");
    }
}