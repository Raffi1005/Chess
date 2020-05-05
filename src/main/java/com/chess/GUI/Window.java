package com.chess.GUI;

import javax.swing.*;
import java.awt.*;

public class Window {

    public Window()
    {
        JFrame frame = new JFrame("Chess");
        ImageIcon imageIcon = new ImageIcon("E:\\Studia\\Rok 2\\Semestr 4\\JTP\\Chess\\src\\main\\java\\com\\chess\\GUI\\Assets\\BlackRook.png");
        frame.setIconImage(imageIcon.getImage());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(840,840);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
