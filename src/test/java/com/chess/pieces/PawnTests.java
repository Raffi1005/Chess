package com.chess.pieces;

import com.chess.game.Game;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;


@RunWith(value = BlockJUnit4ClassRunner.class)
public class PawnTests {

    @Test
    public void canMoveTwo() {
        Game game = new Game();
        Piece pawn = new Pawn(1, 1, game.blackPlayer);

        game.gameBoard.movePiece(pawn, 1, 3); //move two spaces forward
        Assert.assertEquals(pawn, game.gameBoard.boardArray[1][3]);
    }

    @Test
    public void canCaptureBlack() {
        Game game = new Game();
        Piece pawn = new Pawn(3, 3, game.whitePlayer);
        new Pawn(2, 2, game.blackPlayer);

        game.gameBoard.movePiece(pawn, 2, 2);
        Assert.assertEquals(pawn, game.gameBoard.boardArray[2][2]);
        Assert.assertNull(game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void canCaptureWhite() {
        Game game = new Game();
        Piece pawn = new Pawn(1, 1, game.blackPlayer);
        new Pawn(2, 2, game.whitePlayer);

        game.gameBoard.movePiece(pawn, 2, 2);
        Assert.assertEquals(pawn, game.gameBoard.boardArray[2][2]);
        Assert.assertNull(game.gameBoard.boardArray[1][1]);
    }

    @Test
    public void canNotCapture() {
        Game game = new Game();
        Piece pawn = new Pawn(1, 1, game.blackPlayer);
        new Pawn(2, 2, game.blackPlayer);

        game.gameBoard.movePiece(pawn, 2, 2);

    }

    @Test
    public void canMoveForwardBlack() {
        Game game = new Game();
        Piece pawn = new Pawn(4, 4, game.blackPlayer);

        game.gameBoard.movePiece(pawn, 4, 5);
        Assert.assertEquals(pawn, game.gameBoard.boardArray[4][5]);
        Assert.assertNull(game.gameBoard.boardArray[4][4]);
    }

    @Test
    public void canMoveForwardWhite() {
        Game game = new Game();
        Piece pawn = new Pawn(3, 3, game.whitePlayer);

        game.gameBoard.movePiece(pawn, 3, 2);
        Assert.assertEquals(pawn, game.gameBoard.boardArray[3][2]);
        Assert.assertNull(game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void outOfBounds() {
        Game game = new Game();
        Piece pawn = new Pawn(4, 4, game.blackPlayer);

        game.gameBoard.movePiece(pawn, 40, 21);
        Assert.assertEquals(pawn, game.gameBoard.boardArray[4][4]);
    }

}
