package com.chess.pieces;

import com.chess.game.Game;
import com.chess.game.Player;
import org.junit.Assert;
import org.junit.Test;

public class RookTests {


    @Test
    public void moveVerticallyUp()
    {
        Game game = new Game();
        Piece rook = new Rook(5,5,game.whitePlayer);
        game.gameboard.movePiece(5,7,rook);
        Assert.assertNotEquals(rook,game.gameboard.boardArray[5][5]);
        Assert.assertEquals(rook,game.gameboard.boardArray[5][7]);
    }
}
