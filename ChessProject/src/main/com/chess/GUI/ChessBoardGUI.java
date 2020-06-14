package com.chess.GUI;

import com.chess.game.Game;
import com.chess.game.Player;
import com.chess.pieces.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class ChessBoardGUI extends JPanel {

    Game game;
    Player currPlayer;
    Piece selectedPiece;
    private JButton selectedSquare;
    boolean endTurn = false;
    private JFrame frame;
    private JPanel menu;
    private JPanel gamePanel;
    private JPanel blackTimerPanel;
    private JPanel whiteTimerPanel;
    MyTimer whiteTimer;
    MyTimer blackTimer;
    private Chess inst;
    private JLabel turn;
    private JLabel player;
    private JLabel whiteTurn;
    private JLabel blackTurn;
    public JButton[][] buttonsArray;

    public synchronized void waitForInput() {
        while (!endTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized void notifyInput() {
        endTurn = true;
        notifyAll();
    }

    /**
     * Initialize GUI
     */
    public ChessBoardGUI() {
        EventQueue.invokeLater(new Runnable() {


            @Override
            public void run() {
                createFrame();
                createGame();
            }
        });
    }

    /**
     * Starting game
     */
    public void startGame() {
        String[] buttons2 = {"15:00", "10:00", "5:00"};
            int time = JOptionPane.showOptionDialog(null, "Choose game length", "MENU",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons2, buttons2[0]);
            if (time == -1) {
                System.exit(0);
            }
        createBlackTimer(time);
        createWhiteTimer(time);
        inst = new Chess(this);
        gamePanel.setVisible(true);
        whiteTimerPanel.setVisible(true);
        blackTimerPanel.setVisible(true);
        menu.setVisible(true);
        inst.gameLoop();

    }

    public void createGame() {
        createChessBoard();
        createButton();
    }

    public void createFrame() {
        frame = new JFrame("Chess");
        ImageIcon imageIcon = new ImageIcon("E:\\Studia\\Rok 2\\Semestr 4\\JTP\\Chess\\src\\main\\java\\com\\chess\\GUI\\Assets\\BlackRook.png");
        frame.setIconImage(imageIcon.getImage());
        frame.setBackground(Color.blue);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(900, 840);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public void createButton() {
        menu = new JPanel();
        menu.setLayout(new GridBagLayout());
        GridBagConstraints gbcMenu = new GridBagConstraints();
        gbcMenu.insets = new Insets(5, 0, 5, 20);
        final JButton reset = new JButton("Reset");
        reset.setPreferredSize(new Dimension(70, 30));
        gbcMenu.gridx = 0;
        gbcMenu.gridy = 0;
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String[] buttons = {"Keep Playing", "RESET"};
                int returnValue = JOptionPane.showOptionDialog(null, "Do you want to RESET?", "EXIT",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
                if (returnValue == 0||returnValue==-1) {

                }
                if (returnValue == 1) {
                    inst.setRestarted();
                    notifyInput();
                }

            }
        });
        menu.add(reset, gbcMenu);


        JButton exit = new JButton("EXIT");
        exit.setPreferredSize(new Dimension(70, 30));
        gbcMenu.gridx = 0;
        gbcMenu.gridy = 1;
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String[] buttons = {"Keep Playing", "EXIT"};
                int returnValue = JOptionPane.showOptionDialog(null, "Do you want to EXIT?", "EXIT",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
                if (returnValue == 0||returnValue==-1) {
                }
                if (returnValue == 1) {
                    System.exit(0);
                }
            }
        });
        menu.add(exit, gbcMenu);
        player = new JLabel();
        gbcMenu.gridx = 0;
        gbcMenu.gridy = 2;
        menu.add(player, gbcMenu);
        turn = new JLabel();
        gbcMenu.gridx = 0;
        gbcMenu.gridy = 3;
        menu.add(turn, gbcMenu);


        reset.setEnabled(true);
        exit.setEnabled(true);
        frame.add(menu, BorderLayout.EAST);
        menu.setVisible(false);
    }

    public void addCurrPlayer() {
        player.setText("Current player: " + inst.getCurrPlayer());
        turn.setText("Total moves " + Game.turn);
        whiteTurn.setText("White moves " + Game.whiteTurn);
        blackTurn.setText("Black moves " + Game.blackTurn);
    }


    public void createChessBoard() {
        gamePanel = new JPanel();
        ChessWindow window = new ChessWindow();
        gamePanel.setEnabled(true);
        gamePanel.add(window);
        gamePanel.setVisible(true);
        frame.add(gamePanel, BorderLayout.WEST);
        gamePanel.setVisible(false);
    }

    public void createBlackTimer(int time) {
        blackTimerPanel = new JPanel();
        blackTimerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 50, 5, 0);
        blackTimer = new MyTimer(time);
        blackTimerPanel.setEnabled(true);
        gbc.gridx = 0;
        gbc.gridy = 0;
        blackTimerPanel.add(blackTimer, gbc);
        blackTurn = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        blackTimerPanel.add(blackTurn, gbc);
        frame.add(blackTimerPanel, BorderLayout.NORTH);
        blackTimerPanel.setVisible(false);
    }


    public void createWhiteTimer(int time) {
        whiteTimerPanel = new JPanel();
        whiteTimerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 50, 10, 0);
        whiteTimer = new MyTimer(time);
        whiteTimerPanel.setEnabled(true);
        gbc.gridx = 0;
        gbc.gridy = 0;
        whiteTimerPanel.add(whiteTimer, gbc);
        whiteTurn = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        whiteTimerPanel.add(whiteTurn, gbc);
        frame.add(whiteTimerPanel, BorderLayout.SOUTH);
        whiteTimerPanel.setVisible(false);
    }

    public void resetGrid() {
        frame.setVisible(false);
        createFrame();
        createGame();
        startGame();
    }

    public void changeType(JButton button) {
        int newX, newY;
        Player player;
        newX = selectedPiece.x;
        newY = selectedPiece.y;
        player = selectedPiece.player;
        Image newPawn;
        String[] buttons = {"Queen", "Rook", "Bishop", "Knight"};
        int returnValue;
        do {
            returnValue = JOptionPane.showOptionDialog(null, "Choose new type.", "Upgrade",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
            switch (returnValue) {
                case 0:
                    if (selectedPiece.player.playerColor == com.chess.game.Color.BLACK) {
                        try {
                            newPawn = ImageIO.read(getClass().getResource("Assets/BlackQueen.png"));
                            button.setIcon(new ImageIcon(newPawn));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    } else {
                        try {
                            newPawn = ImageIO.read(getClass().getResource("Assets/WhiteQueen.png"));
                            button.setIcon(new ImageIcon(newPawn));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    }

                    selectedPiece = new Queen(newX, newY, player);
                    break;
                case 1:
                    if (selectedPiece.player.playerColor == com.chess.game.Color.BLACK) {
                        try {
                            newPawn = ImageIO.read(getClass().getResource("Assets/BlackRook.png"));
                            button.setIcon(new ImageIcon(newPawn));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    } else {
                        try {
                            newPawn = ImageIO.read(getClass().getResource("Assets/WhiteRook.png"));
                            button.setIcon(new ImageIcon(newPawn));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    }
                    selectedPiece = new Rook(newX, newY, player);
                    break;
                case 2:
                    if (selectedPiece.player.playerColor == com.chess.game.Color.BLACK) {
                        try {
                            newPawn = ImageIO.read(getClass().getResource("Assets/BlackBishop.png"));
                            button.setIcon(new ImageIcon(newPawn));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    } else {
                        try {
                            newPawn = ImageIO.read(getClass().getResource("Assets/WhiteBishop.png"));
                            button.setIcon(new ImageIcon(newPawn));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    }
                    selectedPiece = new Bishop(newX, newY, player);
                    break;
                case 3:
                    if (selectedPiece.player.playerColor == com.chess.game.Color.BLACK) {
                        try {
                            newPawn = ImageIO.read(getClass().getResource("Assets/BlackKnight.png"));
                            button.setIcon(new ImageIcon(newPawn));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    } else {
                        try {
                            newPawn = ImageIO.read(getClass().getResource("Assets/WhiteKnight.png"));
                            button.setIcon(new ImageIcon(newPawn));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    }
                    selectedPiece = new Knight(newX, newY, player);
                    break;
                default:
                    break;
            }
        }while(returnValue==-1);
    }


    class MyActionListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            Point rv = new Point();
            Piece selection = game.gameBoard.boardArray[button.getLocation(rv).x / 90][button.getLocation(rv).y / 90];

            if (selection == null || (selectedPiece != null && selection.player != selectedPiece.player && selectedPiece.player == currPlayer)) {
                if (selectedPiece == null) {
                } else {
                    selectedPiece.player.myGame.gameBoard.movePiece(selectedPiece, button.getLocation(rv).x / 90, button.getLocation(rv).y / 90);
                    if (game.capture) {
                        button.setIcon(null);
                        Icon img = selectedSquare.getIcon();
                        button.setIcon(img);
                        selectedSquare.setIcon(null);
                    } else if (game.invalid) {
                        JOptionPane.showMessageDialog(null, "Invalid movement! Please try another move.");
                        game.capture = false;
                        selectedSquare = null;
                        selectedPiece = null;
                        game.invalid = false;
                        return;
                    } else if(game.roszada) {
                        Icon img = selectedSquare.getIcon();
                        button.setIcon(img);
                        selectedSquare.setIcon(null);
                        System.out.println((button.getLocation(rv).x / 90));
                        System.out.println((button.getLocation(rv).y / 90));
                        if((button.getLocation(rv).y / 90)==0)
                        {
                            if((button.getLocation(rv).x / 90)==2)
                            {
                                JButton button1 = buttonsArray[0][0];
                                img = button1.getIcon();
                                button1.setIcon(null);
                                buttonsArray[3][0].setIcon(img);
                            }
                            else
                            {
                                JButton button1 = buttonsArray[7][0];
                                img = button1.getIcon();
                                button1.setIcon(null);
                                buttonsArray[5][0].setIcon(img);
                            }
                        }
                        else
                        {
                            if((button.getLocation(rv).x / 90)==2)
                            {
                                JButton button1 = buttonsArray[0][7];
                                img = button1.getIcon();
                                button1.setIcon(null);
                                buttonsArray[3][7].setIcon(img);
                            }
                            else
                            {
                                JButton button1 = buttonsArray[7][7];
                                img = button1.getIcon();
                                button1.setIcon(null);
                                buttonsArray[5][7].setIcon(img);
                            }
                        }
                        game.roszada=false;
                    }
                    else if(game.wPrzelocie){
                        button.setIcon(null);
                        Icon img = selectedSquare.getIcon();
                        button.setIcon(img);
                        selectedSquare.setIcon(null);
                        if(selectedPiece.player.playerColor== com.chess.game.Color.WHITE) {
                            JButton button1 = buttonsArray[button.getLocation(rv).x / 90][(button.getLocation(rv).y / 90) + 1];
                            button1.setIcon(null);
                        }
                        else{
                            JButton button1 = buttonsArray[button.getLocation(rv).x / 90][(button.getLocation(rv).y / 90) - 1];
                            button1.setIcon(null);
                        }
                        game.wPrzelocie=false;
                    }
                    else{
                        Icon img = selectedSquare.getIcon();
                        button.setIcon(img);
                        selectedSquare.setIcon(null);
                        if (selectedPiece.getType() == Type.PAWN) {
                            if (selectedPiece.player.playerColor == com.chess.game.Color.WHITE && selectedPiece.y == 0) {
                                changeType(button);
                            }
                            if (selectedPiece.player.playerColor == com.chess.game.Color.BLACK && selectedPiece.y == 7) {
                                changeType(button);
                            }
                        }
                    }
                    game.capture = false;
                    selectedSquare = null;
                    selectedPiece = null;
                    game.invalid = false;
                    notifyInput();
                }
            } else {
                if (selection.player == currPlayer) {
                    selectedPiece = game.gameBoard.boardArray[button.getLocation(rv).x / 90][button.getLocation(rv).y / 90];
                    selectedSquare = button;
                }
            }
        }
    }

    /**
     * ChessWindow class that creates the grid layout and populates the grid with buttons
     * that have piece icons on them.
     */
    public class ChessWindow extends JPanel {

        public ChessWindow() {
            setLayout(new GridLayout(8, 8));
            buttonsArray = new JButton[8][8];
            GridBagConstraints gbc = new GridBagConstraints();

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    gbc.gridx = col;
                    gbc.gridy = row;

                    new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    JButton button = new JButton();
                    if (row == 1) {
                        Image blackPawn;
                        try {
                            blackPawn = ImageIO.read(getClass().getResource("Assets/BlackPawn.png"));
                            button.setIcon(new ImageIcon(blackPawn));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    }

                    if (row == 6) {
                        Image whitePawn;
                        try {
                            whitePawn = ImageIO.read(getClass().getResource("Assets/WhitePawn.png"));
                            button.setIcon(new ImageIcon(whitePawn));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    }

                    if (row == 0 && (col == 0 || col == 7)) {
                        Image blackRook;
                        try {
                            blackRook = ImageIO.read(getClass().getResource("Assets/BlackRook.png"));
                            button.setIcon(new ImageIcon(blackRook));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    }

                    if (row == 7 && (col == 0 || col == 7)) {
                        Image whiteRook;
                        try {
                            whiteRook = ImageIO.read(getClass().getResource("Assets/WhiteRook.png"));
                            button.setIcon(new ImageIcon(whiteRook));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    }

                    if (row == 0 && (col == 1 || col == 6)) {
                        Image blackKnight;
                        try {
                            blackKnight = ImageIO.read(getClass().getResource("Assets/BlackKnight.png"));
                            button.setIcon(new ImageIcon(blackKnight));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    }

                    if (row == 7 && (col == 1 || col == 6)) {
                        Image whiteKnight;
                        try {
                            whiteKnight = ImageIO.read(getClass().getResource("Assets/WhiteKnight.png"));
                            button.setIcon(new ImageIcon(whiteKnight));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    }

                    if (row == 0 && (col == 2 || col == 5)) {
                        Image blackBishop;
                        try {
                            blackBishop = ImageIO.read(getClass().getResource("Assets/BlackBishop.png"));
                            button.setIcon(new ImageIcon(blackBishop));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    }

                    if (row == 7 && (col == 2 || col == 5)) {
                        Image whiteBishop;
                        try {
                            whiteBishop = ImageIO.read(getClass().getResource("Assets/WhiteBishop.png"));
                            button.setIcon(new ImageIcon(whiteBishop));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    }

                    if (row == 0 && col == 3) {
                        Image blackQueen;
                        try {
                            blackQueen = ImageIO.read(getClass().getResource("Assets/BlackQueen.png"));
                            button.setIcon(new ImageIcon(blackQueen));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    }

                    if (row == 7 && col == 3) {
                        Image whiteQueen;
                        try {
                            whiteQueen = ImageIO.read(getClass().getResource("Assets/WhiteQueen.png"));
                            button.setIcon(new ImageIcon(whiteQueen));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    }

                    if (row == 0 && col == 4) {
                        Image blackKing;
                        try {
                            blackKing = ImageIO.read(getClass().getResource("Assets/BlackKing.png"));
                            button.setIcon(new ImageIcon(blackKing));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    }

                    if (row == 7 && col == 4) {
                        Image whiteKing;
                        try {
                            whiteKing = ImageIO.read(getClass().getResource("Assets/WhiteKing.png"));
                            button.setIcon(new ImageIcon(whiteKing));
                        } catch (IOException e) {
                            System.out.println("Could not find file.");
                        }
                    }
                    buttonsArray[col][row] = button;

                    button.setBorderPainted(true);
                    button.setPreferredSize(new Dimension(90, 90));
                    if ((row + col) % 2 == 0) {
                        button.setBackground(Color.WHITE);
                    } else
                        button.setBackground(Color.PINK);
                    MyActionListener mal = new MyActionListener();
                    button.addActionListener(mal);
                    add(button, gbc);

                }
            }
        }


    }

}
