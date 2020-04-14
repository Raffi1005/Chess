package com.chess.game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.concurrent.*;

public class MyTimer extends JFrame {

    // GUI Components
    private JPanel panel;
    private JLabel timeLabel;

    private JPanel buttonPanel;
    private JButton startButton;
    private JButton resetButton;
    private JButton stopButton;

    // Properties of Program.
    private byte centiseconds = 0;
    private byte seconds = 0;
    private short minutes = 10;

    private Runnable timeTask;
    private Runnable incrementTimeTask;
    private Runnable setTimeTask;
    private DecimalFormat timeFormatter;
    private boolean timerIsRunning = true;

    private ExecutorService executor = Executors.newCachedThreadPool();

    public MyTimer(Color color) {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Consolas", Font.PLAIN, 13));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(timeLabel);


        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!timerIsRunning)
                    timerIsRunning = true;

                executor.execute(timeTask);
            }
        });
        buttonPanel.add(startButton);

        stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timerIsRunning = false;
            }
        });

        buttonPanel.add(stopButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

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

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle(color.toString());

        pack();
        setVisible(true);
    }
}

