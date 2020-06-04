package com.chess.pieces;

import com.chess.game.Game;
import org.junit.Assert;
import org.junit.Test;


public class KnightTests {

    @Test
    public void moveRightDown() {
        Game game = new Game();
        Piece knight = new Knight(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(knight, 5, 4);
        Assert.assertEquals(knight, game.gameBoard.boardArray[5][4]);
        Assert.assertNotEquals(knight, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveRightUp() {
        Game game = new Game();
        Piece knight = new Knight(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(knight, 5, 2);
        Assert.assertEquals(knight, game.gameBoard.boardArray[5][2]);
        Assert.assertNotEquals(knight, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveDownRight() {
        Game game = new Game();
        Piece knight = new Knight(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(knight, 4, 5);
        Assert.assertEquals(knight, game.gameBoard.boardArray[4][5]);
        Assert.assertNotEquals(knight, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveUpRight() {
        Game game = new Game();
        Piece knight = new Knight(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(knight, 4, 1);
        Assert.assertEquals(knight, game.gameBoard.boardArray[4][1]);
        Assert.assertNotEquals(knight, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveLeftUp() {
        Game game = new Game();
        Piece knight = new Knight(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(knight, 1, 2);
        Assert.assertEquals(knight, game.gameBoard.boardArray[1][2]);
        Assert.assertNotEquals(knight, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveLeftDown() {
        Game game = new Game();
        Piece knight = new Knight(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(knight, 1, 4);
        Assert.assertEquals(knight, game.gameBoard.boardArray[1][4]);
        Assert.assertNotEquals(knight, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveUpLeft() {
        Game game = new Game();
        Piece knight = new Knight(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(knight, 2, 1);
        Assert.assertEquals(knight, game.gameBoard.boardArray[2][1]);
        Assert.assertNotEquals(knight, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveDownLeft() {
        Game game = new Game();
        Piece knight = new Knight(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(knight, 2, 5);
        Assert.assertEquals(knight, game.gameBoard.boardArray[2][5]);
        Assert.assertNotEquals(knight, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void canCapture() {
        Game game = new Game();
        Piece knight = new Knight(3, 3, game.whitePlayer);
        Piece enemyPawn = new Pawn(2, 5, game.blackPlayer);

        game.gameBoard.movePiece(knight, 2, 5);
        Assert.assertEquals(knight, game.gameBoard.boardArray[2][5]);
    }

}
