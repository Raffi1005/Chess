package com.chess.pieces;

import com.chess.game.Player;

public abstract class Piece {
    public int x;
    public int y;
    public Player player;
    public boolean isMovedTwo;
    public boolean roszada=false;

    /**
     * Pawn Constructor
     * @param x Starting x-axis
     * @param y Starting y-axis
     * @param player
     */
    Piece(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
        this.player.myGame.gameBoard.boardArray[x][y] = this;
        this.isMovedTwo=false;
    }

    /**
     * Check if path is clear
     * @param final_x
     * @param final_y
     * @return
     */
    public abstract boolean isValidPath(int final_x, int final_y);

    /**
     * @param start_x
     * @param start_y
     * @param final_x
     * @param final_y
     * @return
     */
    public abstract int[][] drawPath(int start_x, int start_y, int final_x, int final_y);

    /**
     * @return
     */
    public abstract Type getType();

    public abstract boolean getIsMovedTwo();


}
