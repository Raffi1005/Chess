package com.chess.game;

public class WhiteTurns extends Thread {
    private final Turns turns;

    public WhiteTurns(Turns turns) {
        this.turns = turns;
    }

    @Override
    public void run() {
        while (true) {
            try {
                turns.add();
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

