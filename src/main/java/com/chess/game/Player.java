package com.chess.game;

import com.chess.pieces.*;

import java.util.Vector;

public class Player {
    public Game myGame;
    public boolean goesFirst;
    public Color playerColor;
    public boolean isLoser = false;
    public int score = 0;
    public boolean isTurn = false;

    public Player(Color color,boolean goesFirst) {
        this.playerColor=color;
        this.goesFirst=goesFirst;
    }

    public Vector<Piece> getPlayerPieces(Color playerColor)
    {
        Vector<Piece> allyPieces;

        if(playerColor==Color.WHITE)
            allyPieces = myGame.gameBoard.whitePieces;
        else
            allyPieces = myGame.gameBoard.blackPieces;

        return allyPieces;
    }

    public Vector<Piece> getEnemyPieces(Color playerColor)
    {
        Vector<Piece> enemyPieces;

        if(playerColor==Color.WHITE)
            enemyPieces = myGame.gameBoard.blackPieces;
        else
            enemyPieces = myGame.gameBoard.whitePieces;

        return enemyPieces;
    }




}
