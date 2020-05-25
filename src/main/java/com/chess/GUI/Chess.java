package com.chess.GUI;

import com.chess.game.BlackTurns;
import com.chess.game.Color;
import com.chess.game.Game;
import com.chess.game.WhiteTurns;

import javax.swing.*;
import java.util.concurrent.Semaphore;

public class Chess{
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

    public void gameLoop() {
            if (isRestarted) {
                gui.resetGrid();
            }
            isRestarted=false;
            gui.game = new Game();
        Game.blackTurn=-1;
        Game.whiteTurn=-1;
        Game.turn=-2;

        Semaphore whiteSem =new Semaphore(1);
        Semaphore blackSem =new Semaphore(1);
        WhiteTurns whiteTurns = new WhiteTurns(whiteSem,blackSem);
        BlackTurns blackTurns = new BlackTurns(blackSem,whiteSem);

        Thread whiteTurn = new Thread(whiteTurns);
        Thread blackTurn = new Thread(blackTurns);
        whiteTurn.start();
        blackTurn.start();

            if (gui.game.blackPlayer.goesFirst && Game.turn % 2 != 0) {
                gui.currPlayer = gui.game.blackPlayer;
                whiteSem.release();
                gui.currPlayer.isTurn = true;
            } else if (gui.game.whitePlayer.goesFirst && Game.turn % 2 == 0) {
                gui.currPlayer = gui.game.blackPlayer;
                whiteSem.release();
                gui.currPlayer.isTurn = true;
            } else {
                gui.currPlayer = gui.game.whitePlayer;
                blackSem.release();
                gui.currPlayer.isTurn = true;
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

                if(isRestarted)
                {
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
                        blackSem.release();
                        gui.currPlayer = gui.game.whitePlayer;
                    } else {
                        gui.whiteTimer.pause();
                        gui.blackTimer.start();
                        whiteSem.release();
                        gui.currPlayer = gui.game.blackPlayer;
                    }
                    gui.endTurn = false;
                   // blackSem.release();
                    //whiteSem.release();
                }

                if (gui.whiteTimer.isDone())
                    gui.game.whitePlayer.isLoser = true;
                if (gui.blackTimer.isDone())
                    gui.game.blackPlayer.isLoser = true;

            }

            String[] buttons = new String[]{"Yes", "No"};
            String winner;
            if(gui.game.blackPlayer.isLoser)
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

    public void setRestarted()
    {
        isRestarted=true;
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