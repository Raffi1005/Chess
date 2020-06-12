package com.chess.pieces;

import com.chess.game.Player;

public class Rook extends Piece {

    Type type;

    public Rook(int x, int y, Player player) {
        super(x, y, player);
        type = Type.ROOK;
    }


    public boolean isValidPath(int final_x, int final_y) {
        return (this.x == final_x) || (this.y == final_y);
    }


    public int[][] drawPath(int start_x, int start_y, int final_x, int final_y) {
        int pairs;
        int x_dir = 0, y_dir = 0;

        //Horizonatlly
        if (final_x - start_x != 0 && final_y - start_y == 0) {
            pairs = Math.abs(final_x - start_x);
            if (final_x - start_x < 0)
                x_dir = -1;
            else
                x_dir = 1;
        } else //Vertically
        {
            pairs = Math.abs(final_y - start_y);
            if (final_y - start_y < 0)
                y_dir = -1;
            else
                y_dir = 1;
        }

        int[][] path = new int[2][pairs - 1];
        if (pairs - 1 > 0) {
            for (int i = 0; i < pairs - 1; i++) {
                path[0][i] = start_x + x_dir;
                path[1][i] = start_y + y_dir;
            }
        }
        return path;
    }


    public Type getType() {
        return Type.ROOK;
    }

    @Override
    public boolean getIsMovedTwo() {
        return false;
    }


}
