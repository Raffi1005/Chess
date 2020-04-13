package com.chess.pieces;

import com.chess.game.Game;
import com.chess.game.Player;
import org.junit.Assert;
import org.junit.Test;

public class RookTests {


    @Test
    public void moveVerticallyUp() throws Exception
    {
        Game game = new Game();
        Piece rook = new Rook(4,4,game.whitePlayer);
        game.gameboard.movePiece(rook,4,5);
        Assert.assertEquals(rook,game.gameboard.boardArray[4][5]);
        Assert.assertNotEquals(rook,game.gameboard.boardArray[4][4]);
    }
}
