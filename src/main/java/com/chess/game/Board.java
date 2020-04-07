package com.chess.game;

import com.chess.pieces.Piece;
import com.chess.pieces.Rook;

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
        Piece topLeftRook = new Rook(0,0,game.blackPlayer);
        Piece topRightRook = new Rook(7,0,game.blackPlayer);
    }

    private void setWhitePlayerPieces() {
        Piece[][] board = this.boardArray;
        Piece bottomLeftRook = new Rook(0,7,game.whitePlayer);
        Piece bottomRightRook = new Rook(7,7,game.whitePlayer);

    }

    public void movePiece(int final_x,int final_y,Piece piece)
    {
        setNewPieceLocation(piece,final_x,final_y);
    }

    private void setNewPieceLocation(Piece piece, int final_x, int final_y) {
        int start_x=piece.x;
        int start_y=piece.y;

        piece.x=final_x;
        piece.y=final_y;

        boardArray[final_x][final_y]=piece;
        boardArray[start_x][start_y]=null;
    }
}
