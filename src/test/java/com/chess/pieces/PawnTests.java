package com.chess.pieces;

import com.chess.exceptions.InvalidMovementException;
import com.chess.game.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class PawnTests {

    @Test
    public void canMoveTwo() {
        Game game = new Game();
        Piece pawn = new Pawn(1,1,game.blackPlayer);

        game.gameBoard.movePiece(pawn, 1, 3); //move two spaces forward
        assertEquals(pawn, game.gameBoard.boardArray[1][3]);
    }

    @Test
    public void canCaptureBlack()
    {
        Game game = new Game();
        Piece pawn = new Pawn(3,3,game.whitePlayer);
        new Pawn(2, 2, game.blackPlayer);

        game.gameBoard.movePiece(pawn, 2, 2);
        assertEquals(pawn, game.gameBoard.boardArray[2][2]);
        assertNull(game.gameBoard.boardArray[3][3]);
    }
    @Test
    public void canCaptureWhite()
    {
        Game game = new Game();
        Piece pawn = new Pawn(1,1,game.blackPlayer);
        new Pawn(2, 2, game.whitePlayer);

        game.gameBoard.movePiece(pawn, 2, 2);
        assertEquals(pawn, game.gameBoard.boardArray[2][2]);
        assertNull(game.gameBoard.boardArray[1][1]);
    }

    @Test
    public void canNotCapture() {
        Game game = new Game();
        Piece pawn = new Pawn(1,1,game.blackPlayer);
        new Pawn(2, 2, game.blackPlayer);

        game.gameBoard.movePiece(pawn, 2, 2);

    }

    @Test
    public void canMoveForwardBlack() {
        Game game = new Game();
        Piece pawn = new Pawn(4,4,game.blackPlayer);

        game.gameBoard.movePiece(pawn, 4, 5);
        assertEquals(pawn, game.gameBoard.boardArray[4][5]);
        assertNull(game.gameBoard.boardArray[4][4]);
    }

    @Test
    public void canMoveForwardWhite() {
        Game game = new Game();
        Piece pawn = new Pawn(3,3,game.whitePlayer);

        game.gameBoard.movePiece(pawn, 3, 2);
        assertEquals(pawn, game.gameBoard.boardArray[3][2]);
        assertNull(game.gameBoard.boardArray[3][3]);
    }

    @Test(expected = InvalidMovementException.class)
    public void canNotMoveForwardBlack() {
        Game game = new Game();
        Piece pawn = new Pawn(1, 1, game.blackPlayer);
        new Pawn(1, 2, game.blackPlayer);

        game.gameBoard.movePiece(pawn, 1, 2);
    }

    @Test(expected = InvalidMovementException.class)
    public void canNotMoveForwardWhite() {
        Game game = new Game();
        Piece pawn = game.gameBoard.boardArray[1][1];
        Piece pawn2 = new Pawn(1, 2, game.blackPlayer);
        game.gameBoard.boardArray[1][2] = pawn2;

        game.gameBoard.movePiece(pawn, 1, 2);
    }

    @Test
    public void outOfBounds() {
        Game game = new Game();
        Piece pawn = new Pawn(4, 4, game.blackPlayer);

        game.gameBoard.movePiece(pawn, 40,21);
        assertEquals(pawn,game.gameBoard.boardArray[4][4]);
    }

    @Test(expected = InvalidMovementException.class)
    public void invalidOrigin() {
        Game game = new Game();
        Piece pawn = new Pawn(4, 4, game.blackPlayer);

        game.gameBoard.movePiece(pawn, 4, 4);
    }
}
