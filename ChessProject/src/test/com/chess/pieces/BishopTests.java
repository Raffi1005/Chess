package com.chess.pieces;

import com.chess.game.Game;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;


@RunWith(value = BlockJUnit4ClassRunner.class)
public class BishopTests {

    @Test
    public void moveDiagonallyDownRight() {
        Game game = new Game();
        Piece bishop = new Bishop(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(bishop, 4, 4);
        Assert.assertEquals(bishop, game.gameBoard.boardArray[4][4]);
        Assert.assertNotEquals(bishop, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveDiagonallyDownLeft() {
        Game game = new Game();
        Piece bishop = new Bishop(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(bishop, 2, 4);
        Assert.assertEquals(bishop, game.gameBoard.boardArray[2][4]);
        Assert.assertNotEquals(bishop, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveDiagonallyUpRight() {
        Game game = new Game();
        Piece bishop = new Bishop(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(bishop, 4, 2);
        Assert.assertEquals(bishop, game.gameBoard.boardArray[4][2]);
        Assert.assertNotEquals(bishop, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveDiagonallyUpLeft() {
        Game game = new Game();
        Piece bishop = new Bishop(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(bishop, 2, 2);
        Assert.assertEquals(bishop, game.gameBoard.boardArray[2][2]);
        Assert.assertNotEquals(bishop, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void moveVerticallyUp() {
        Game game = new Game();
        Piece bishop = new Bishop(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(bishop, 3, 2);
    }

    @Test
    public void moveVerticallyDown() {
        Game game = new Game();
        Piece bishop = new Bishop(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(bishop, 3, 4);
    }

    @Test
    public void moveHorizontallyLeft() {
        Game game = new Game();
        Piece bishop = new Bishop(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(bishop, 2, 3);
    }

    @Test
    public void moveHorizontallyRight() {
        Game game = new Game();
        Piece bishop = new Bishop(3, 3, game.whitePlayer);
        game.gameBoard.movePiece(bishop, 4, 3);
    }

    @Test
    public void canCapture() {
        Game game = new Game();
        Piece bishop = new Bishop(2, 2, game.whitePlayer);
        Piece enemyPawn = new Pawn(3, 3, game.blackPlayer);

        game.gameBoard.movePiece(bishop, 3, 3);
        Assert.assertEquals(bishop, game.gameBoard.boardArray[3][3]);
    }

}
