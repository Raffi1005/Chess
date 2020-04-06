package com.chess.game;

public class Player {
    public Game myGame;
    public boolean goesFirst;
    public Color playerColor;

    public Player(Color color,boolean goesFirst) {
        this.playerColor=color;
        this.goesFirst=goesFirst;
    }
}
