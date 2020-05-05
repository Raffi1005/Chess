package com.chess.game;

import com.chess.pieces.*;
import com.chess.exceptions.*;

import java.util.Vector;


public class Board {
    public int width,height;
    public Game game;
    public Piece[][] boardArray;
    protected Vector<Piece> whitePieces = new Vector<Piece>(16);
    protected Vector<Piece> blackPieces = new Vector<Piece>(16);
    Board(int width,int height,Game game)
    {
        this.width=width;
        this.height=height;
        boardArray=new Piece[width][height];
        this.game=game;
    }

    public void setPieces() {
        setWhitePlayerPieces();
        setBlackPlayerPieces();
        setPieceVectors();
    }

    private void setPieceVectors() {
        for(int i = 0; i < 8; i++)
        {
            whitePieces.add(this.boardArray[i][6]);
            whitePieces.add(this.boardArray[i][7]);
            blackPieces.add(this.boardArray[i][0]);
            blackPieces.add(this.boardArray[i][1]);
        }
    }

    private void setBlackPlayerPieces() {
        Piece[][] board = this.boardArray;
        for(int i=0;i<8;i++) {
            Piece pawn = new Pawn(i, 1, this.game.blackPlayer);
        }

        Piece blackLeftRook = new Rook(0,0,this.game.blackPlayer);
        Piece blackRightRook = new Rook(7,0,this.game.blackPlayer);

        Piece blackLeftKnight = new Knight(1,0,this.game.blackPlayer);
        Piece blackRightKnight = new Knight(6,0,this.game.blackPlayer);

        Piece blackLeftBishop = new Bishop(2,0,this.game.blackPlayer);
        Piece blackRightBishop = new Bishop(5,0,this.game.blackPlayer);

        Piece blackQueen = new Queen(3,0,this.game.blackPlayer);
        Piece blackKing = new King(4,0,this.game.blackPlayer);

    }

    private void setWhitePlayerPieces() {
        Piece[][] board = this.boardArray;
        for(int i=0;i<8;i++) {
            Piece pawn = new Pawn(i, 6, this.game.whitePlayer);
        }

        Piece whiteLeftRook = new Rook(0,7,this.game.whitePlayer);
        Piece whiteRightRook = new Rook(7,7,this.game.whitePlayer);

        Piece whiteLeftKnight = new Knight(1,7,this.game.whitePlayer);
        Piece whiteRightKnight = new Knight(6,7,this.game.whitePlayer);

        Piece whiteLeftBishop = new Bishop(2,7,this.game.whitePlayer);
        Piece whiteRightBishop = new Bishop(5,7,this.game.whitePlayer);

        Piece whiteQueen = new Queen(3,7,this.game.whitePlayer);
        Piece whiteKing = new King(4,7,this.game.whitePlayer);

    }

    public void movePiece(Piece piece,int final_x,int final_y)
    {

        if(piece.isValidPath(final_x,final_y)&&isValidMove(piece,final_x,final_y)) {
            if (isCapture(piece, final_x, final_y)) {
                game.capture = true;
                boardArray[final_x][final_y] = null;
            }
            setNewPieceLocation(piece, final_x, final_y);
        }
        else {
            try {
                throw new InvalidMovementException();
            } catch (InvalidMovementException e) {
                e.printStackTrace();
                game.invalid = true;
            }
        }

    }

    private void setNewPieceLocation(Piece piece, int final_x, int final_y) {
        int start_x=piece.x;
        int start_y=piece.y;

        piece.x=final_x;
        piece.y=final_y;

        boardArray[final_x][final_y]=piece;
        boardArray[start_x][start_y]=null;
    }

    public boolean isValidMove(Piece piece,int final_x,int final_y)
    {
        int[][] path = piece.drawPath(piece.x,piece.y,final_x,final_y);

        if(isWithinBounds(final_x,final_y)&& (isValidLeap(piece, path))&& (isNotOrigin(piece, final_x, final_y))
                && (isValidEndPoint(piece, final_x, final_y)))
        {
            return true;
        }

        return false;


    }

    protected boolean isWithinBounds(int final_x,int final_y)
    {
        return (final_x <width && final_x >= 0 && final_y >= 0 && final_y < width);
    }

    protected boolean isValidLeap(Piece piece,int[][] movePath)
    {
        if(piece.getType()==Type.KNIGHT)
            return true;

        if(piece.getType()==Type.PAWN||piece.getType()==Type.KING)
            return true;

        int pairs = movePath[0].length;

        for(int i=0;i<pairs-1;i++)
        {
            if(boardArray[movePath[0][i]][movePath[1][i]]!=null)
                return false;
        }
        return true;
    }

    protected boolean isNotOrigin(Piece piece,int final_x,int final_y)
    {
        return (piece.x != final_x || piece.y != final_y);
    }

    protected boolean isValidEndPoint(Piece piece, int final_x, int final_y)
    {
        return ((boardArray[final_x][final_y] == null) ||
                (boardArray[final_x][final_y] != null &&
                        boardArray[final_x][final_y].player.playerColor != piece.player.playerColor));
    }

    protected boolean isCapture(Piece piece,int final_x,int final_y)
    {
        if(boardArray[final_x][final_y] != null &&
                boardArray[final_x][final_y].player != piece.player)
        {
            if(boardArray[final_x][final_y].getType()==Type.KING)
            {
                boardArray[final_x][final_y].player.isLoser = true;
                return true;
            }
        }
        return false;
    }
}
