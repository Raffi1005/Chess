package com.chess.GUI;

import javax.swing.*;

public class Menu {
    public static void main(String[] args) throws InterruptedException {

        //Chess inst = new Chess();
        ChessBoardGUI gui = new ChessBoardGUI();
        String[] buttons = { "Start", "Exit" };
        int returnValue = JOptionPane.showOptionDialog(null, "What to do", "MENU",
                JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);
        System.out.println(returnValue);
        if(returnValue == 0)
        {
            gui.startGame();
        }
        if(returnValue == 1)
        {
            System.exit(0);

        }
        //inst.gameLoop(gui);
    }
}
