package com.chess.pieces;

import com.chess.game.Color;
import com.chess.game.Player;

public class Pawn extends Piece {
    Type type;

    public Pawn(int x, int y, Player player) {
        super(x, y, player);
        this.type = Type.PAWN;
        this.isMovedTwo=false;
    }

    @Override
    public boolean isValidPath(int final_x, int final_y) {


        if (pawnCanMoveTwo(final_x, final_y))
            return true;

        if (pawnCanCapture(final_x, final_y))
            return true;

        return pawnCanMoveForward(final_x, final_y);

    }

    private boolean pawnCanMoveForward(int final_x, int final_y) {
        int abs_Y_diff = Math.abs(final_y - this.y);
        int Y_diff = final_y - this.y;
        Piece[][] board = this.player.myGame.gameBoard.boardArray;
        isMovedTwo=false;
        return ((this.player.playerColor == Color.WHITE && Y_diff < 0 && abs_Y_diff == 1) ||
                (this.player.playerColor == Color.BLACK && Y_diff > 0 && abs_Y_diff == 1)) &&
                board[final_x][final_y] == null && this.x == final_x;
    }

    private boolean pawnCanCapture(int final_x, int final_y) {
        int AbsX_dif = Math.abs(final_x - this.x);
        int AbsY_dif = Math.abs(final_y - this.y);
        int Y_dif = final_y - this.y;
        Piece[][] board = this.player.myGame.gameBoard.boardArray;
        if (AbsX_dif == AbsY_dif && AbsX_dif == 1) {
            if (player.playerColor == Color.WHITE && board[final_x][final_y] != null &&
                    board[final_x][final_y].player.playerColor == Color.BLACK && Y_dif < 0) {
                return true;
            }
            if(player.playerColor == Color.WHITE&&board[final_x][final_y+1]!=null)
            {

                if(board[final_x][final_y] == null
                        &&board[final_x][final_y+1].player.playerColor == Color.BLACK
                        &&board[final_x][final_y+1].getIsMovedTwo()) {
                    return true;
                }
            }
            if(player.playerColor == Color.BLACK&&board[final_x][final_y-1]!=null)
            {
                if(board[final_x][final_y] == null
                        &&board[final_x][final_y-1].player.playerColor == Color.WHITE
                        &&board[final_x][final_y-1].getIsMovedTwo()) {
                    return true;
                }
            }

        if(player.playerColor == Color.BLACK && board[final_x][final_y] != null &&
        board[final_x][final_y].player.playerColor == Color.WHITE && Y_dif > 0)
        {
            return true;
        }

        }
        return false;
    }

    private boolean pawnCanMoveTwo(int final_x, int final_y) {
        int AbsY_dif = Math.abs(final_y - this.y);

        Piece[][] board = this.player.myGame.gameBoard.boardArray;
        isMovedTwo=true;
        return (final_x == this.x && AbsY_dif == 2 && board[final_x][final_y] == null) &&
                ((this.player.playerColor == Color.WHITE && board[this.x][this.y-1] == null&&this.y==6) ||
                        (this.player.playerColor == Color.BLACK && board[this.x][this.y+1] == null&&this.y==1));

    }

    @Override
    public int[][] drawPath(int start_x, int start_y, int final_x, int final_y) {
        int pairs = 0;
        return new int[2][pairs];
    }

    @Override
    public Type getType() {
        return Type.PAWN;
    }

    @Override
    public boolean getIsMovedTwo(){
        return isMovedTwo;
    }
}
