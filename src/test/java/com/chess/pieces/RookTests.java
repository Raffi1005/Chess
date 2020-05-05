package com.chess.pieces;

import com.chess.game.Game;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void canCapture() throws Exception
    {
        Game game = new Game();
        Piece rook = new Rook(2,1,game.whitePlayer);
        Piece enemyPawn = new Pawn(2, 2, game.blackPlayer);

        game.gameBoard.movePiece(rook, 2, 2);
        assertEquals(rook, game.gameBoard.boardArray[2][2]);
    }
}
