package com.chess.game;

import java.util.Random;

public class Game {
    public Board gameBoard;
    public Player whitePlayer, blackPlayer;
    public int turn;
    final static int standardHeight=8,standardWidth=8;
    public boolean invalid=false,capture=false;

    /**
     * Starting game
     */
    public Game()
    {
        StartGame();
    }


    private void StartGame() {
        gameBoard =new Board(standardHeight,standardWidth,this);
        setPlayers();
        gameBoard.setPieces();
        turn=1;
    }

    private void setPlayers() {
        this.whitePlayer=new Player(Color.WHITE,true);
        this.blackPlayer=new Player(Color.BLACK,true);
        isFirst();
        whitePlayer.myGame=this;
        blackPlayer.myGame=this;
    }


    private void isFirst() {
        Random rand = new Random();
        int randomNum1 = rand.nextInt(2) + 1;
        int randomNum2 = rand.nextInt(2) + 1;

        if(randomNum1 >= randomNum2)
            whitePlayer.goesFirst = false;
        else
            blackPlayer.goesFirst = false;
    }
}
