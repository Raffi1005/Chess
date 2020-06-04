package com.chess.game;

public class BlackTurns extends Thread {

    private final Turns turns;

    public BlackTurns(Turns turns) {
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
