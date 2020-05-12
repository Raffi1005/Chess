package com.chess.game;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MyTimer extends JPanel {

    private final JLabel timeLabel;

    // Properties of Program.
    private byte centiseconds = 0;
    private byte seconds = 0;
    private short minutes = 10;

    private Runnable timeTask;
    private Runnable incrementTimeTask;
    private Runnable setTimeTask;
    private DecimalFormat timeFormatter;
    private boolean timerIsRunning = true;

    private ExecutorService executor = Executors.newFixedThreadPool(2);

    public MyTimer() {
        setLayout(new BorderLayout());

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Consolas", Font.PLAIN, 13));
        add(timeLabel,BorderLayout.NORTH);

        timeFormatter = new DecimalFormat("00");

        timeTask = new Runnable() {
            public void run() {
                while (timerIsRunning) {
                    executor.execute(incrementTimeTask);

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };

        incrementTimeTask = new Runnable() {
            public void run() {
                if (centiseconds > 0)
                    centiseconds--;
                else {
                    if (seconds == 0 && minutes == 0)
                        timerIsRunning = false;
                    else if (seconds > 0) {
                        seconds--;
                        centiseconds = 99;
                    } else if (minutes > 0) {
                        minutes--;
                        seconds = 59;
                        centiseconds = 99;
                    }
                }

                executor.execute(setTimeTask);
            }
        };

        setTimeTask = new Runnable() {
            public void run() {
                timeLabel.setText(timeFormatter.format(minutes) + ":"
                        + timeFormatter.format(seconds) + "."
                        + timeFormatter.format(centiseconds));
            }
        };

        timeLabel.setText(timeFormatter.format(minutes) + ":"
                + timeFormatter.format(seconds) + "."
                + timeFormatter.format(centiseconds));


    }


    public void start()
    {
        if (!timerIsRunning)
            timerIsRunning = true;

        executor.execute(timeTask);
    }

    public void pause()
    {
        timerIsRunning=false;
    }

    public boolean isDone()
    {
        return minutes == 0 && seconds == 0 && centiseconds == 0;
    }
}

