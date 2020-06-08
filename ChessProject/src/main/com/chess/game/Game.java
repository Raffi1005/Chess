package com.chess.game;

import java.util.Random;

public class Game {
    public Board gameBoard;
    public Player whitePlayer, blackPlayer;
    public static int turn;
    public static int whiteTurn;
    public static int blackTurn;
    final static int standardHeight = 8, standardWidth = 8;
    public boolean invalid = false, capture = false;

    /**
     * Starting game
     */
    public Game() {
        StartGame();
    }

    /**
     * Creates game, setting up board, players and pieces
     */
    private void StartGame() {
        gameBoard = new Board(this);
        setPlayers();
        gameBoard.setPieces();
        turn = 0;
    }

    private void setPlayers() {
        this.whitePlayer = new Player(Color.WHITE, false);
        this.blackPlayer = new Player(Color.BLACK, false);
        isFirst();
        whitePlayer.myGame = this;
        blackPlayer.myGame = this;
    }


    /**
     * 50% chance to be first move
     */
    private void isFirst() {
        Random rand = new Random();
        if (rand.nextBoolean())
            whitePlayer.goesFirst = true;
        else
            blackPlayer.goesFirst = true;
    }
}
