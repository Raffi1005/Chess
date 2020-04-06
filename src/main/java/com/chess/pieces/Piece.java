package com.chess.pieces;

import com.chess.game.Player;

public abstract class Piece {
    public int x;
    public int y;
    protected Player player;

    Piece(int x,int y,Player player)
    {
        this.x=x;
        this.y=y;
        this.player=player;
        this.player.myGame.gameboard.boardArray[x][y]=this;
    }

    public abstract boolean isValidPath(int final_x, int final_y);

    public abstract int[][] drawPath(int start_x,int start_y,int final_x,int final_y);

    public abstract Type getType();


}
