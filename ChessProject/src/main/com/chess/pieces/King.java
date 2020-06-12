package com.chess.pieces;

import com.chess.game.Color;
import com.chess.game.Player;

public class King extends Piece {

    Type type;

    public King(int x, int y, Player player) {
        super(x, y, player);
        this.type = Type.KING;
    }

    @Override
    public boolean isValidPath(int final_x, int final_y) {
        Piece[][] board = this.player.myGame.gameBoard.boardArray;
        if(this.player.playerColor== Color.BLACK)
        {
                if((final_y==0&&final_x==2&&board[0][0].getType()==Type.ROOK&&board[1][0]==null&&board[2][0]==null&&board[3][0]==null)
                ||(final_y==0&&final_x==6&&board[7][0].getType()==Type.ROOK&&board[6][0]==null&&board[5][0]==null))
                {
                    roszada=true;
                    return true;
                }

        }
        if(this.player.playerColor== Color.WHITE)
        {
            if((final_y==7&&final_x==2&&board[0][7].getType()==Type.ROOK&&board[1][7]==null&&board[2][7]==null&&board[3][7]==null)
                    ||(final_y==7&&final_x==6&&board[7][7].getType()==Type.ROOK&&board[5][7]==null&&board[6][7]==null)) {
                roszada=true;
                return true;
            }
        }

        return (Math.abs(final_x - this.x) == 1 && Math.abs(final_y - this.y) == 1)||(Math.abs(final_x - this.x) == 1 && Math.abs(final_y - this.y) == 0)||(Math.abs(final_x - this.x) == 0 && Math.abs(final_y - this.y) == 1);
    }

    @Override
    public int[][] drawPath(int start_x, int start_y, int final_x, int final_y) {
        int pairs = 0;

        return new int[2][pairs];
    }

    @Override
    public Type getType() {
        return Type.KING;
    }

    @Override
    public boolean getIsMovedTwo() {
        return false;
    }

}
