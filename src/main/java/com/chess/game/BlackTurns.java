package com.chess.game;

import java.util.concurrent.Semaphore;

public class BlackTurns implements Runnable {
    private Semaphore blackSem;
    private Semaphore whiteSem;

    public BlackTurns(Semaphore blackSem, Semaphore whiteSem) {
        this.blackSem = blackSem;
        this.whiteSem = whiteSem;
    }

    @Override
    public void run()
    {
        while(true) {
            try {
                whiteSem.acquire();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Game.turn++;
            Game.blackTurn++;
            //Game.blackTurn++;
            //blackSem.release();
        }
    }
}
