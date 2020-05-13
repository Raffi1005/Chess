package com.chess.pieces;

import com.chess.game.Player;

public abstract class Piece {
    public int x;
    public int y;
    public Player player;

    /**
     *
     * @param x
     * @param y
     * @param player
     */
    Piece(int x,int y,Player player)
    {
        this.x=x;
        this.y=y;
        this.player=player;
        this.player.myGame.gameBoard.boardArray[x][y]=this;
    }

    /**
     *
     * @param final_x
     * @param final_y
     * @return
     */
    public abstract boolean isValidPath(int final_x, int final_y);

    /**
     *
     * @param start_x
     * @param start_y
     * @param final_x
     * @param final_y
     * @return
     */
    public abstract int[][] drawPath(int start_x,int start_y,int final_x,int final_y);

    /**
     *
     * @return
     */
    public abstract Type getType();


}
