package com.chess.GUI;

import javax.swing.*;

/**
 * Game of chess for project
 * @author Michal Potent
 */
public class Menu {
    Menu()
    {
        createMenu();
    }

    public void createMenu()
    {
        ChessBoardGUI gui = new ChessBoardGUI();
        String[] buttons = {"Start", "Exit"};
        int returnValue;
        do {
            returnValue = JOptionPane.showOptionDialog(null, "What to do", "MENU",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
            if (returnValue == 0) {
                gui.startGame();
            }
            if (returnValue == 1) {
                System.exit(0);
            }
        }while(returnValue==-1);
    }

    public static void main(String[] args) {
        new Menu();
    }
}
