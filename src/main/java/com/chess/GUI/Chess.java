package com.chess.GUI;

import com.chess.game.Color;
import com.chess.game.Game;
import com.chess.game.MyTimer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chess {
    boolean isRestarted = false;

    public void gameLoop(ChessBoardGUI gui)
    {
        if(isRestarted)
        {
            //gui.resetGrid();
        }
        gui.game = new Game();

        if (gui.game.blackPlayer.goesFirst && gui.game.turn % 2 != 0)
        {
            gui.currPlayer = gui.game.blackPlayer;
            gui.currPlayer.isTurn = true;
        }
        else if(gui.game.whitePlayer.goesFirst && gui.game.turn%2 ==0)
        {
            gui.currPlayer = gui.game.blackPlayer;
            gui.currPlayer.isTurn = true;
        }
        else {
            gui.currPlayer = gui.game.whitePlayer;
            gui.currPlayer.isTurn = true;
        }


        JOptionPane.showMessageDialog(null, gui.currPlayer.playerColor + " goes first!");

        while (!gui.game.blackPlayer.isLoser && !gui.game.whitePlayer.isLoser) {
            if(gui.game.turn==1)
            {
                gui.blackTimer.start();
                gui.blackTimer.pause();
                gui.whiteTimer.start();
                gui.whiteTimer.pause();
            }
            //JOptionPane.showMessageDialog(null, gui.currPlayer.playerColor + ", make your move.");
            String[] buttons = new String[]{"OK"};
            int returnValue = JOptionPane.showOptionDialog(null, gui.currPlayer.playerColor + ", make your move.","TURN",
                    JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);
            if(returnValue == 0&&gui.currPlayer.playerColor==Color.WHITE)
            {
                gui.whiteTimer.start();
            }
            if(returnValue == 0&&gui.currPlayer.playerColor==Color.BLACK)
            {
                gui.blackTimer.start();
            }

            /*String[] buttons = { "OK", "Tie", "I give up" };
            int returnValue = JOptionPane.showOptionDialog(null, gui.currPlayer.playerColor + ", make your move.", "TURN",
                    JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);
            if(returnValue == 1)
            {
                gui.game.blackPlayer.score++;
                gui.game.whitePlayer.score++;
                buttons = new String[]{"Yes", "No"};
                returnValue = JOptionPane.showOptionDialog(null, "Game is tied. Play a new game?", "GAME OVER",
                        JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);
                if(returnValue == 0)
                {
                    buttons = new String[]{"Yes", "No"};
                    returnValue = JOptionPane.showOptionDialog(null, "Opponent, do you agree to restart the game?", "GAME OVER",
                            JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);
                    if (returnValue == 0)
                    {
                        isRestarted =true;
                        gameLoop(gui);
                    }


                }
            }
            if(returnValue == 2)
            {
                gui.currPlayer.isLoser = true;
                if(gui.currPlayer == gui.game.blackPlayer)
                    gui.game.whitePlayer.score++;
                else
                    gui.game.blackPlayer.score++;
                buttons = new String[]{"Yes", "No"};
                returnValue = JOptionPane.showOptionDialog(null, "Play a new game?", "GAME OVER",
                        JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);
                if(returnValue == 1)
                    System.exit(0);
                else
                {
                    isRestarted =true;
                    gameLoop(gui);
                }
            }*/
            gui.waitForInput();

            if(gui.game.invalid)
            {
                gui.endTurn = false;
                gui.game.invalid = false;
            }

            else
            {
                gui.currPlayer.isTurn = false;
                if(gui.currPlayer == gui.game.blackPlayer) {
                    gui.blackTimer.pause();
                    gui.currPlayer = gui.game.whitePlayer;
                }
                else {
                    gui.whiteTimer.pause();
                    gui.currPlayer = gui.game.blackPlayer;
                }
                gui.endTurn = false;

                gui.game.turn++;
                System.out.println(gui.game.turn);
            }

            if(gui.whiteTimer.isDone())
                gui.game.whitePlayer.isLoser=true;
            if(gui.blackTimer.isDone())
                gui.game.blackPlayer.isLoser=true;
        }

        String[] buttons = new String[]{"Yes", "No"};
        int returnValue = JOptionPane.showOptionDialog(null, "CHECKMATE. Play a new game?", "GAME OVER",
                JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);
        if(returnValue == 1)
            System.exit(0);
        else
        {
            isRestarted =true;
            gameLoop(gui);
        }
    }

    public ChessBoardGUI getChessBoardGUI() {
        return new ChessBoardGUI();
    }
}