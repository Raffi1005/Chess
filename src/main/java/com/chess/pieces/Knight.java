package com.chess.pieces;

import com.chess.game.Player;

public class Knight extends Piece {
    Type type;

    public Knight(int x, int y, Player player) {
        super(x, y, player);
        this.type = Type.KNIGHT;
    }

    @Override
    public boolean isValidPath(int final_x, int final_y) {
        return (Math.abs(final_x - this.x) == 1 && Math.abs(final_y - this.y) == 2) || (Math.abs(final_x - this.x) == 2 && Math.abs(final_y - this.y) == 1);
    }

    @Override
    public int[][] drawPath(int start_x, int start_y, int final_x, int final_y) {
        int pairs = 0; //knights can leap, so a path is not necessary
        int[][] path = new int[2][pairs];

        return path;
    }

    @Override
    public Type getType() {
        return Type.KNIGHT;
    }
}
