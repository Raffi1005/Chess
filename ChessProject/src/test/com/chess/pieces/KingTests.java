package com.chess.pieces;

import com.chess.game.Game;
import org.junit.Assert;
import org.junit.Test;


public class KingTests {

    @Test
    public void moveVerticallyUp() {
        Game game = new Game();
        Piece king = new King(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(king, 3, 2);
        Assert.assertEquals(king, game.gameBoard.boardArray[3][2]);
        Assert.assertNotEquals(king, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveVerticallyDown() {
        Game game = new Game();
        Piece king = new King(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(king, 3, 4);
        Assert.assertEquals(king, game.gameBoard.boardArray[3][4]);
        Assert.assertNotEquals(king, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveHorizontallyLeft() {
        Game game = new Game();
        Piece king = new King(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(king, 2, 3);
        Assert.assertEquals(king, game.gameBoard.boardArray[2][3]);
        Assert.assertNotEquals(king, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveHorizontallyRight() {
        Game game = new Game();
        Piece king = new King(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(king, 4, 3);
        Assert.assertEquals(king, game.gameBoard.boardArray[4][3]);
        Assert.assertNotEquals(king, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveDiagonallyDownRight() {
        Game game = new Game();
        Piece king = new King(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(king, 4, 4);
        Assert.assertEquals(king, game.gameBoard.boardArray[4][4]);
        Assert.assertNotEquals(king, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveDiagonallyDownLeft() {
        Game game = new Game();
        Piece king = new King(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(king, 2, 4);
        Assert.assertEquals(king, game.gameBoard.boardArray[2][4]);
        Assert.assertNotEquals(king, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveDiagonallyUpRight() {
        Game game = new Game();
        Piece king = new King(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(king, 4, 2);
        Assert.assertEquals(king, game.gameBoard.boardArray[4][2]);
        Assert.assertNotEquals(king, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveDiagonallyUpLeft() {
        Game game = new Game();
        Piece king = new King(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(king, 2, 2);
        Assert.assertEquals(king, game.gameBoard.boardArray[2][2]);
        Assert.assertNotEquals(king, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void canCapture() {
        Game game = new Game();
        Piece king = new King(2, 1, game.whitePlayer);
        Piece enemyPawn = new Pawn(2, 2, game.blackPlayer);

        game.gameBoard.movePiece(king, 2, 2);
        Assert.assertEquals(king, game.gameBoard.boardArray[2][2]);
    }

    @Test
    public void isGameEndingForWhite() {
        Game game = new Game();
        Piece king = new King(2, 2, game.whitePlayer);
        Piece enemyPawn = new Pawn(1, 1, game.blackPlayer);

        game.gameBoard.movePiece(enemyPawn, 2, 2);
        Assert.assertEquals(enemyPawn, game.gameBoard.boardArray[2][2]);
        Assert.assertEquals(true, game.whitePlayer.isLoser);
    }



    @Test
    public void isGameEndingForBlack() {
        Game game = new Game();
        Piece king = new King(2, 2, game.blackPlayer);
        Piece enemyPawn = new Pawn(3, 3, game.whitePlayer);

        game.gameBoard.movePiece(enemyPawn, 2, 2);
        Assert.assertEquals(enemyPawn, game.gameBoard.boardArray[2][2]);
        Assert.assertEquals(true, game.blackPlayer.isLoser);
    }
}
