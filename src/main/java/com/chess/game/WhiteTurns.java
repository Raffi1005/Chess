package com.chess.game;

import java.util.concurrent.Semaphore;

public class WhiteTurns implements Runnable{
    private Semaphore whiteSem;
    private Semaphore blackSem;

    public WhiteTurns(Semaphore whiteSem, Semaphore blackSem) {
        this.whiteSem = whiteSem;
        this.blackSem = blackSem;
    }

    @Override
    public void run()
    {
        while(true) {
            try {
                blackSem.acquire();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Game.whiteTurn++;
            Game.turn++;
            //Game.turn++;
           // Game.whiteTurn++;
           // whiteSem.release();
        }
    }
}

