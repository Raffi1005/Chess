package com.chess.pieces;

import com.chess.game.Game;
import org.junit.Assert;
import org.junit.Test;


public class QueenTests {

    @Test
    public void moveVerticallyUp()
    {
        Game game = new Game();
        Piece queen = new Queen(3,3,game.whitePlayer);
        game.gameBoard.movePiece(queen,3,2);
        Assert.assertEquals(queen,game.gameBoard.boardArray[3][2]);
        Assert.assertNotEquals(queen,game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveVerticallyDown()
    {
        Game game = new Game();
        Piece queen = new Queen(3,3,game.whitePlayer);
        game.gameBoard.movePiece(queen,3,4);
        Assert.assertEquals(queen,game.gameBoard.boardArray[3][4]);
        Assert.assertNotEquals(queen,game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveHorizontallyLeft()
    {
        Game game = new Game();
        Piece queen = new Queen(3,3,game.whitePlayer);
        game.gameBoard.movePiece(queen,2,3);
        Assert.assertEquals(queen,game.gameBoard.boardArray[2][3]);
        Assert.assertNotEquals(queen,game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveHorizontallyRight()
    {
        Game game = new Game();
        Piece queen = new Queen(3,3,game.whitePlayer);
        game.gameBoard.movePiece(queen,4,3);
        Assert.assertEquals(queen,game.gameBoard.boardArray[4][3]);
        Assert.assertNotEquals(queen,game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveDiagonallyDownRight()
    {
        Game game = new Game();
        Piece queen = new Queen(3,3,game.whitePlayer);
        game.gameBoard.movePiece(queen,4,4);
        Assert.assertEquals(queen,game.gameBoard.boardArray[4][4]);
        Assert.assertNotEquals(queen,game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveDiagonallyDownLeft()
    {
        Game game = new Game();
        Piece queen = new Queen(3,3,game.whitePlayer);
        game.gameBoard.movePiece(queen,2,4);
        Assert.assertEquals(queen,game.gameBoard.boardArray[2][4]);
        Assert.assertNotEquals(queen,game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveDiagonallyUpRight()
    {
        Game game = new Game();
        Piece queen = new Queen(3,3,game.whitePlayer);
        game.gameBoard.movePiece(queen,4,2);
        Assert.assertEquals(queen,game.gameBoard.boardArray[4][2]);
        Assert.assertNotEquals(queen,game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveDiagonallyUpLeft()
    {
        Game game = new Game();
        Piece queen = new Queen(3,3,game.whitePlayer);
        game.gameBoard.movePiece(queen,2,2);
        Assert.assertEquals(queen,game.gameBoard.boardArray[2][2]);
        Assert.assertNotEquals(queen,game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void canCapture()
    {
        Game game = new Game();
        Piece queen = new Queen(2,1,game.whitePlayer);
        Piece enemyPawn = new Pawn(2, 2, game.blackPlayer);

        game.gameBoard.movePiece(queen, 2, 2);
        Assert.assertEquals(queen, game.gameBoard.boardArray[2][2]);
    }
}
