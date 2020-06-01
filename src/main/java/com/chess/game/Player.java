package com.chess.game;

public class Player {
    public Game myGame;
    public boolean goesFirst;
    public Color playerColor;
    public boolean isLoser = false;
    public boolean isTurn = false;

    /**
     * @param color
     * @param goesFirst
     */
    public Player(Color color, boolean goesFirst) {
        this.playerColor = color;
        this.goesFirst = goesFirst;
    }

}
