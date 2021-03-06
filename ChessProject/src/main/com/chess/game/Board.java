package com.chess.game;

import com.chess.exceptions.InvalidMovementException;

import com.chess.pieces.*;

import java.util.Vector;


public class Board {
    public int width, height;
    private final Game game;
    public Piece[][] boardArray;
    protected Vector<Piece> whitePieces = new Vector<>(16);
    protected Vector<Piece> blackPieces = new Vector<>(16);
    private boolean isWPrzelocieBiale;
    private boolean isWPrzelocieCzarne;

    /**
     * Board constructor
     *
     * @param game Game on board
     */
    Board(Game game) {
        this.width = Game.standardWidth;
        this.height = Game.standardHeight;
        boardArray = new Piece[width][height];
        this.game = game;
    }

    /**
     * Setting up pieces in game
     */
    public void setPieces() {
        setWhitePlayerPieces();
        setBlackPlayerPieces();
        setPieceVectors();
    }

    private void setPieceVectors() {
        for (int i = 0; i < 8; i++) {
            whitePieces.add(this.boardArray[i][6]);
            whitePieces.add(this.boardArray[i][7]);
            blackPieces.add(this.boardArray[i][0]);
            blackPieces.add(this.boardArray[i][1]);
        }
    }

    private void setBlackPlayerPieces() {
        for (int i = 0; i < 8; i++) {
            new Pawn(i, 1, this.game.blackPlayer);
        }

        new Rook(0, 0, this.game.blackPlayer);
        new Rook(7, 0, this.game.blackPlayer);

        new Knight(1, 0, this.game.blackPlayer);
        new Knight(6, 0, this.game.blackPlayer);

        new Bishop(2, 0, this.game.blackPlayer);
        new Bishop(5, 0, this.game.blackPlayer);

        new Queen(3, 0, this.game.blackPlayer);
        new King(4, 0, this.game.blackPlayer);

    }

    private void setWhitePlayerPieces() {
        for (int i = 0; i < 8; i++) {
            new Pawn(i, 6, this.game.whitePlayer);
        }

        new Rook(0, 7, this.game.whitePlayer);
        new Rook(7, 7, this.game.whitePlayer);

        new Knight(1, 7, this.game.whitePlayer);
        new Knight(6, 7, this.game.whitePlayer);

        new Bishop(2, 7, this.game.whitePlayer);
        new Bishop(5, 7, this.game.whitePlayer);

        new Queen(3, 7, this.game.whitePlayer);
        new King(4, 7, this.game.whitePlayer);

    }

    /**
     * Checking if piece can be moved
     *
     * @param piece
     * @param final_x
     * @param final_y
     */
    public void movePiece(Piece piece, int final_x, int final_y) {

        if (piece.isValidPath(final_x, final_y) && isValidMove(piece, final_x, final_y)) {
            if (isCapture(piece, final_x, final_y)) {
                game.capture = true;
                if(isWPrzelocieBiale)
                {
                    boardArray[final_x][final_y+1] = null;
                    isWPrzelocieBiale=false;
                    game.wPrzelocie=true;
                    game.capture=false;
                }
                else if(isWPrzelocieCzarne)
                {
                    boardArray[final_x][final_y-1] = null;
                    isWPrzelocieCzarne=false;
                    game.wPrzelocie=true;
                    game.capture=false;
                }
                else
                boardArray[final_x][final_y] = null;
            }
            if(piece.roszada)
            {
                if(final_y==0)
                {
                    if(final_x==2)
                    {
                        setNewPieceLocation(boardArray[0][0],3,0);
                    }
                    if(final_x==6)
                    {
                        setNewPieceLocation(boardArray[7][0],5,0);
                    }
                }
                if(final_y==7)
                {
                    if(final_x==2)
                    {
                        setNewPieceLocation(boardArray[0][7],3,7);
                    }
                    if(final_x==6)
                    {
                        setNewPieceLocation(boardArray[7][7],5,7);
                    }
                }
                game.roszada=true;
            }
            setNewPieceLocation(piece, final_x, final_y);

        } else {
            try {
                throw new InvalidMovementException();
            } catch (InvalidMovementException e) {
                e.printStackTrace();
                game.invalid = true;
            }
        }

    }

    /**
     * @param piece
     * @param final_x
     * @param final_y
     */
    private void setNewPieceLocation(Piece piece, int final_x, int final_y) {
        int start_x = piece.x;
        int start_y = piece.y;

        piece.x = final_x;
        piece.y = final_y;

        boardArray[final_x][final_y] = piece;
        boardArray[start_x][start_y] = null;
    }

    /**
     * @param piece
     * @param final_x
     * @param final_y
     * @return
     */
    public boolean isValidMove(Piece piece, int final_x, int final_y) {
        int[][] path = piece.drawPath(piece.x, piece.y, final_x, final_y);

        return isWithinBounds(final_x, final_y) && (isValidLeap(piece, path)) && (isNotOrigin(piece, final_x, final_y))
                && (isValidEndPoint(piece, final_x, final_y));
    }

    /**
     * @param final_x
     * @param final_y
     * @return
     */
    protected boolean isWithinBounds(int final_x, int final_y) {
        return (final_x < width && final_x >= 0 && final_y >= 0 && final_y < width);
    }

    /**
     * @param piece
     * @param movePath
     * @return
     */
    protected boolean isValidLeap(Piece piece, int[][] movePath) {
        if (piece.getType() == Type.KNIGHT)
            return true;

        if (piece.getType() == Type.PAWN || piece.getType() == Type.KING)
            return true;

        int pairs = movePath[0].length;

        for (int i = 0; i < pairs - 1; i++) {
            if (boardArray[movePath[0][i]][movePath[1][i]] != null)
                return false;
        }
        return true;
    }

    /**
     * @param piece
     * @param final_x
     * @param final_y
     * @return
     */
    protected boolean isNotOrigin(Piece piece, int final_x, int final_y) {
        return (piece.x != final_x || piece.y != final_y);
    }

    /**
     * @param piece
     * @param final_x
     * @param final_y
     * @return
     */
    protected boolean isValidEndPoint(Piece piece, int final_x, int final_y) {
        return ((boardArray[final_x][final_y] == null) ||
                (boardArray[final_x][final_y] != null &&
                        boardArray[final_x][final_y].player.playerColor != piece.player.playerColor));
    }

    /**
     * @param piece
     * @param final_x
     * @param final_y
     * @return
     */
    protected boolean isCapture(Piece piece, int final_x, int final_y) {

        if(piece.getType()==Type.PAWN)
        {
            if(piece.player.playerColor == Color.WHITE&&boardArray[final_x][final_y+1]!=null){
            if(boardArray[final_x][final_y] == null
                    &&boardArray[final_x][final_y+1].player.playerColor == Color.BLACK
                    &&boardArray[final_x][final_y+1].getIsMovedTwo()) {
                    isWPrzelocieBiale = true;
                    return true;
                }
            }
            if(piece.player.playerColor == Color.BLACK&&boardArray[final_x][final_y-1]!=null){
            if(boardArray[final_x][final_y] == null
                    &&boardArray[final_x][final_y-1].player.playerColor == Color.WHITE
                    &&boardArray[final_x][final_y-1].getIsMovedTwo()) {
                    isWPrzelocieCzarne = true;
                    return true;
                }
            }
        }
        if (boardArray[final_x][final_y] != null &&
                boardArray[final_x][final_y].player != piece.player) {
            if (boardArray[final_x][final_y].getType() == Type.KING) {
                boardArray[final_x][final_y].player.isLoser = true;
                return true;
            }
        }
        return false;
    }
}
