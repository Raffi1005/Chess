package com.chess.GUI;

import com.chess.game.*;

import javax.swing.*;

public class Chess {
    private final ChessBoardGUI gui;
    boolean isRestarted = false;

    /**
     * @param gui
     */
    public Chess(ChessBoardGUI gui) {
        this.gui = gui;
    }

    public void gameLoop() {
        if (isRestarted) {
            gui.resetGrid();
        }
        isRestarted = false;
        gui.game = new Game();

        Turns turns = new Turns();
        Thread blackTurns = new BlackTurns(turns);
        Thread whiteTurns = new WhiteTurns(turns);
        blackTurns.start();
        whiteTurns.start();
        Game.whiteTurn = 0;
        Game.blackTurn = 0;
        Game.turn = 0;

        if (gui.game.whitePlayer.goesFirst) {
            gui.currPlayer = gui.game.whitePlayer;
            gui.currPlayer.isTurn = true;
            turns.notifyWhite();
        } else {
            gui.currPlayer = gui.game.blackPlayer;
            gui.currPlayer.isTurn = true;
            turns.notifyBlack();
        }
        gui.blackTimer.start();
        gui.blackTimer.pause();
        gui.whiteTimer.start();
        gui.whiteTimer.pause();


        JOptionPane.showMessageDialog(null, gui.currPlayer.playerColor + " goes first!");


        while (!gui.game.blackPlayer.isLoser && !gui.game.whitePlayer.isLoser) {

            gui.addCurrPlayer();

            if (gui.currPlayer.playerColor == Color.WHITE) {

            }
            if (gui.currPlayer.playerColor == Color.BLACK) {


            }

            if (isRestarted) {
                gameLoop();
            }


            gui.waitForInput();
            if (gui.game.invalid) {
                gui.endTurn = false;
                gui.game.invalid = false;
            } else {
                gui.currPlayer.isTurn = false;
                if (gui.currPlayer == gui.game.blackPlayer) {
                    gui.blackTimer.pause();
                    gui.whiteTimer.start();
                    turns.notifyWhite();
                    gui.currPlayer = gui.game.whitePlayer;
                } else {
                    gui.whiteTimer.pause();
                    gui.blackTimer.start();
                    turns.notifyBlack();
                    gui.currPlayer = gui.game.blackPlayer;
                }
                gui.endTurn = false;
            }


            if (gui.whiteTimer.isDone()) {
                gui.game.whitePlayer.isLoser = true;
                gui.notifyInput();
            }
            if (gui.blackTimer.isDone()) {
                gui.game.blackPlayer.isLoser = true;
                gui.notifyInput();
            }

        }
        gui.blackTimer.pause();
        gui.whiteTimer.pause();

        String[] buttons = new String[]{"Yes", "No"};
        String winner;
        if (gui.game.blackPlayer.isLoser)
            winner = gui.game.whitePlayer.playerColor.toString();
        else
            winner = gui.game.blackPlayer.playerColor.toString();
        int returnValue = JOptionPane.showOptionDialog(null, winner + " WON. Play a new game?", "GAME OVER",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, buttons, buttons[0]);
        if (returnValue == 1)
            System.exit(0);
        else {
            isRestarted = true;
            gameLoop();
        }
    }

    public void setRestarted() {
        isRestarted = true;
    }

    /**
     * @return
     */
    public String getCurrPlayer() {
        return gui.currPlayer.playerColor.toString();
    }
}