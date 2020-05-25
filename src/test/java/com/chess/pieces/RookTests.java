package com.chess.pieces;

import com.chess.game.Game;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;


@RunWith(value = BlockJUnit4ClassRunner.class)
public class RookTests {

    
    @Test
    public void moveVerticallyUp()
    {
        Game game = new Game();
        Piece rook = new Rook(3,3,game.whitePlayer);
        game.gameBoard.movePiece(rook,3,2);
        Assert.assertEquals(rook,game.gameBoard.boardArray[3][2]);
        Assert.assertNotEquals(rook,game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveVerticallyDown()
    {
        Game game = new Game();
        Piece rook = new Rook(3,3,game.whitePlayer);
        game.gameBoard.movePiece(rook,3,4);
        Assert.assertEquals(rook,game.gameBoard.boardArray[3][4]);
        Assert.assertNotEquals(rook,game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveHorizontallyLeft()
    {
        Game game = new Game();
        Piece rook = new Rook(3,3,game.whitePlayer);
        game.gameBoard.movePiece(rook,2,3);
        Assert.assertEquals(rook,game.gameBoard.boardArray[2][3]);
        Assert.assertNotEquals(rook,game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveHorizontallyRight()
    {
        Game game = new Game();
        Piece rook = new Rook(3,3,game.whitePlayer);
        game.gameBoard.movePiece(rook,4,3);
        Assert.assertEquals(rook,game.gameBoard.boardArray[4][3]);
        Assert.assertNotEquals(rook,game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveDiagonallyDownRight()
    {
        Game game = new Game();
        Piece rook = new Rook(3,3,game.whitePlayer);
        game.gameBoard.movePiece(rook,4,4);
    }

    @Test
    public void moveDiagonallyDownLeft()
    {
        Game game = new Game();
        Piece rook = new Rook(3,3,game.whitePlayer);
        game.gameBoard.movePiece(rook,2,4);
    }

    @Test
    public void moveDiagonallyUpRight()
    {
        Game game = new Game();
        Piece rook = new Rook(3,3,game.whitePlayer);
        game.gameBoard.movePiece(rook,4,2);
    }

    @Test
    public void moveDiagonallyUpLeft()
    {
        Game game = new Game();
        Piece rook = new Rook(3,3,game.whitePlayer);
        game.gameBoard.movePiece(rook,2,2);
    }


    @Test
    public void canCapture()
    {
        Game game = new Game();
        Piece rook = new Rook(2,1,game.whitePlayer);
        Piece enemyPawn = new Pawn(2, 2, game.blackPlayer);

        game.gameBoard.movePiece(rook, 2, 2);
        Assert.assertEquals(rook, game.gameBoard.boardArray[2][2]);
    }
}
