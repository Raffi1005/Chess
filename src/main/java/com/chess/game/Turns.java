package com.chess.game;

public class Turns {
    private boolean blackTurn = false;
    private boolean whiteTurn  =false;

        public synchronized void add()
        {
            if(whiteTurn)
            {
                Game.whiteTurn++;
                Game.turn++;
                whiteTurn=false;
            }
            else if(blackTurn) {
                Game.blackTurn++;
                Game.turn++;
                blackTurn=false;
            }
        }

        public synchronized void notifyWhite()
        {
            whiteTurn=true;
            notifyAll();
        }

        public synchronized void notifyBlack()
        {
            blackTurn=true;
            notifyAll();
        }
}
