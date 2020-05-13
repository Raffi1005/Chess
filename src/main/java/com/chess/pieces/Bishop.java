package com.chess.pieces;

import com.chess.game.Player;

public class Bishop extends Piece {
    Type type;

    /**
     *
     * @param x
     * @param y
     * @param player
     */
    public Bishop(int x, int y, Player player) {
        super(x, y, player);
        this.type=Type.BISHOP;
    }

    @Override
    public boolean isValidPath(int final_x, int final_y) {
        return (Math.abs(final_x-this.x)==Math.abs(final_y-this.y));
    }

    @Override
    public int[][] drawPath(int start_x, int start_y, int final_x, int final_y) {
        int pairs = Math.abs(final_x - start_x);
        int x_dir=0,y_dir=0;
        if(final_x-start_x<0)
        {
            if(final_y-start_y<0) {
                x_dir = -1;
                y_dir = -1;
            }
            else
            {
                x_dir = -1;
                y_dir = 1;
            }
        }
        else
        {
            if(final_y-start_y<0) {
                x_dir = 1;
                y_dir = -1;
            }
            else
            {
                x_dir = 1;
                y_dir = 1;
            }
        }
        int [][] path = new int[2][pairs-1];
        if(pairs-1>0)
        {
            for(int i=0;i<pairs-1;i++)
            {
                path[0][i]=start_x+x_dir;
                path[1][i]=start_y+y_dir;
            }
        }
        return path;
    }

    @Override
    public Type getType() {
        return Type.BISHOP;
    }
}
