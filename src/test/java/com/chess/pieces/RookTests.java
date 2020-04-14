package com.chess.pieces;

import com.chess.game.Game;
import org.junit.Assert;
import org.junit.Test;

public class RookTests {


    @Test
    public void moveVerticallyUp() throws Exception
    {
        Game game = new Game();
        Piece rook = game.gameBoard.boardArray[1][1];
        game.gameBoard.movePiece(rook,1,2);
        Assert.assertEquals(rook,game.gameBoard.boardArray[1][2]);
        Assert.assertNotEquals(rook,game.gameBoard.boardArray[1][1]);
    }
}
