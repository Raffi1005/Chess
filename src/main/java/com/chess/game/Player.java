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
            allyPieces = myGame.gameboard.whitePieces;
        else
            allyPieces = myGame.gameboard.blackPieces;

        return allyPieces;
    }

    public Vector<Piece> getEnemyPieces(Color playerColor)
    {
        Vector<Piece> enemyPieces;

        if(playerColor==Color.WHITE)
            enemyPieces = myGame.gameboard.blackPieces;
        else
            enemyPieces = myGame.gameboard.whitePieces;

        return enemyPieces;
    }




}
