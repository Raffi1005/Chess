package com.chess.GUI;

import com.chess.game.Color;
import com.chess.game.Game;

import javax.swing.*;

public class Chess {
    private final ChessBoardGUI gui;
    boolean isRestarted = false;

    /**
     *
     * @param gui
     */
    public Chess(ChessBoardGUI gui)
    {
        this.gui=gui;
    }

    public void gameLoop()
    {
            if (isRestarted) {
                gui.resetGrid();
            }
            gui.game = new Game();

            if (gui.game.blackPlayer.goesFirst && gui.game.turn % 2 != 0) {
                gui.currPlayer = gui.game.blackPlayer;
                gui.currPlayer.isTurn = true;
            } else if (gui.game.whitePlayer.goesFirst && gui.game.turn % 2 == 0) {
                gui.currPlayer = gui.game.blackPlayer;
                gui.currPlayer.isTurn = true;
            } else {
                gui.currPlayer = gui.game.whitePlayer;
                gui.currPlayer.isTurn = true;
            }


            JOptionPane.showMessageDialog(null, gui.currPlayer.playerColor + " goes first!");

            while (!gui.game.blackPlayer.isLoser && !gui.game.whitePlayer.isLoser) {
                if (gui.game.turn == 1) {
                    gui.blackTimer.start();
                    gui.blackTimer.pause();
                    gui.whiteTimer.start();
                    gui.whiteTimer.pause();
                }

                gui.addCurrPlayer();

                if (gui.currPlayer.playerColor == Color.WHITE) {
                    gui.whiteTimer.start();
                }
                if (gui.currPlayer.playerColor == Color.BLACK) {
                    gui.blackTimer.start();
                }



                gui.waitForInput();

                if (gui.game.invalid) {
                    gui.endTurn = false;
                    gui.game.invalid = false;
                } else {
                    gui.currPlayer.isTurn = false;
                    if (gui.currPlayer == gui.game.blackPlayer) {
                        gui.blackTimer.pause();
                        gui.currPlayer = gui.game.whitePlayer;
                    } else {
                        gui.whiteTimer.pause();
                        gui.currPlayer = gui.game.blackPlayer;
                    }
                    gui.endTurn = false;

                    gui.game.turn++;
                    System.out.println(gui.game.turn);
                }

                if (gui.whiteTimer.isDone())
                    gui.game.whitePlayer.isLoser = true;
                if (gui.blackTimer.isDone())
                    gui.game.blackPlayer.isLoser = true;
            }

            String[] buttons = new String[]{"Yes", "No"};
            String winner = "";
            if(gui.game.blackPlayer.isLoser)
                winner = gui.game.whitePlayer.playerColor.toString();
            else
                winner = gui.game.blackPlayer.playerColor.toString();
            int returnValue = JOptionPane.showOptionDialog(null, winner + " WON. Play a new game?", "GAME OVER",
                    JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);
            if (returnValue == 1)
                System.exit(0);
            else {
                isRestarted = true;
                gameLoop();
            }
    }

    public void setRestarted()
    {
        isRestarted=true;
        gameLoop();
    }

    /**
     *
     * @return
     */
    public String getCurrPlayer()
    {
        return gui.currPlayer.playerColor.toString();
    }
}