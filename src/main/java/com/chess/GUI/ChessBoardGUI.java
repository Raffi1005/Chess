package com.chess.GUI;
import com.chess.game.*;
import com.chess.pieces.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;


public class ChessBoardGUI extends JPanel {

    Game game;
    Player currPlayer;
    Piece selectedPiece;
    JButton selectedSquare;
    boolean endTurn = false;
    JFrame frame;
    JPanel menu;
    JPanel gamePanel;
    JPanel blackTimerPanel;
    JPanel whiteTimerPanel;
    MyTimer whiteTimer;
    MyTimer blackTimer;
    Chess inst;

    public synchronized void waitForInput()
    {
        while(!endTurn)
        {
            try{
                wait();
            } catch (InterruptedException e){ e.printStackTrace();}
        }
    }

    public synchronized  void notifyInput()
    {
        endTurn = true;
        notifyAll();
    }

    /**
     * Initializes the GUI.
     */
    public ChessBoardGUI() {
        EventQueue.invokeLater(new Runnable() {


            @Override
            public void run() {
                createFrame();
                //createMenu();

                createGame();

               // startGame();
            }
        });
    }

    public void createMenu(){
        menu = new JPanel();
        menu.setLayout(new GridBagLayout());
        menu.setBackground(Color.GRAY);
        GridBagConstraints gbc = new GridBagConstraints();


        gbc.insets = new Insets(20, 20, 0, 0);

        JLabel title = new JLabel("CHESS");
        title.setFont(new Font("Courier New", Font.ITALIC, 20));
        title.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        menu.add(title, gbc);

        JButton newGameButton = new JButton("New Game");
        gbc.gridx = 0;
        gbc.gridy = 1;
        menu.add(newGameButton, gbc);
        newGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                startGame();
            }
        });

        JButton exit = new JButton("Exit");
        gbc.gridx = 0;
        gbc.gridy = 2;
        menu.add(exit, gbc);
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frame.add(menu);
        //menu.setVisible(false);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }



    public void startGame()
    {
        if(this == null)
        {
            System.out.println("NULL");
        }
        //createGame();
        inst = new Chess();
        gamePanel.setVisible(true);
        whiteTimerPanel.setVisible(true);
        blackTimerPanel.setVisible(true);
        inst.gameLoop(this);

    }

    public void createGame()
    {
        createChessBoard();
        createBlackTimer();
        createWhiteTimer();
        //createButton();
    }

    public void createFrame()
    {
        frame = new JFrame("Chess");
        ImageIcon imageIcon = new ImageIcon("E:\\Studia\\Rok 2\\Semestr 4\\JTP\\Chess\\src\\main\\java\\com\\chess\\GUI\\Assets\\BlackRook.png");
        frame.setIconImage(imageIcon.getImage());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(840,840);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void createButton(){
        JButton button = new JButton("Start");
        button.setPreferredSize(new Dimension(180, 50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                inst.start(ChessBoardGUI.this);
            }
        });
        button.setEnabled(true);
        frame.add(button,BorderLayout.EAST);
        button.setVisible(false);

    }


    public void createChessBoard(){
        gamePanel = new JPanel();
        ChessWindow window = new ChessWindow();
        gamePanel.setEnabled(true);
        gamePanel.add(window);
        gamePanel.setVisible(true);
        frame.add(gamePanel,BorderLayout.WEST);
        gamePanel.setVisible(false);
    }

    public void createBlackTimer()
    {
        blackTimerPanel = new JPanel();
        blackTimerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,50,5,0);
        blackTimer = new MyTimer();
        blackTimerPanel.setEnabled(true);
        gbc.gridx=0;
        gbc.gridy=0;
        blackTimerPanel.add(blackTimer,gbc);
        frame.add(blackTimerPanel,BorderLayout.NORTH);
        blackTimerPanel.setVisible(false);
    }



    public void createWhiteTimer()
    {
        whiteTimerPanel = new JPanel();
        whiteTimerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,50,10,0);
        whiteTimer = new MyTimer();
        whiteTimerPanel.setEnabled(true);
        gbc.gridx=0;
        gbc.gridy=0;
        whiteTimerPanel.add(whiteTimer,gbc);
        frame.add(whiteTimerPanel,BorderLayout.SOUTH);
        whiteTimerPanel.setVisible(false);
    }


    public void resetGrid() {
        frame.setVisible(false);
        createFrame();
        createGame();
        startGame();
    }

    class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            JButton button = (JButton) e.getSource();
            Point rv = new Point();
            Piece selection = game.gameBoard.boardArray[button.getLocation(rv).x/90][button.getLocation(rv).y/90];

            if(selection == null || (selectedPiece != null && selection.player != selectedPiece.player && selectedPiece.player == currPlayer))
            {
                if(selectedPiece == null)
                    return;

                else
                {
                    selectedPiece.player.myGame.gameBoard.movePiece(selectedPiece, button.getLocation(rv).x / 90, button.getLocation(rv).y / 90);
                    if(game.capture)
                    {
                        button.setIcon(null);
                        Icon img = selectedSquare.getIcon();
                        button.setIcon(img);
                        selectedSquare.setIcon(null);
                    }
                    else if(game.invalid)
                    {
                        JOptionPane.showMessageDialog(null, "Invalid movement! Please try another move.");
                        game.capture = false;
                        selectedSquare = null;
                        selectedPiece = null;
                        game.invalid = false;
                        return;
                    }

                    else
                    {
                        Icon img = selectedSquare.getIcon();
                        button.setIcon(img);
                        selectedSquare.setIcon(null);
                    }

                    game.capture = false;
                    selectedSquare = null;
                    selectedPiece = null;
                    game.invalid = false;
                    notifyInput();


                }

            }


            else
            {
                if(selection.player == currPlayer) {
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

            GridBagConstraints gbc = new GridBagConstraints();

                for (int row = 0; row < 8; row++) {
                    for (int col = 0; col < 8; col++) {
                        gbc.gridx = col;
                        gbc.gridy = row;

                        Border border = null;
                        if (row < 8) {
                            if (col < 8) {
                                new MatteBorder(1, 1, 0, 0, Color.GRAY);
                            } else {
                                new MatteBorder(1, 1, 0, 1, Color.GRAY);
                            }
                        } else {
                            if (col < 8) {
                                new MatteBorder(1, 1, 1, 0, Color.GRAY);
                            } else {
                                new MatteBorder(1, 1, 1, 1, Color.GRAY);
                            }
                        }
                        JButton button = new JButton();
                        if (row == 1 && col < 8) {
                            Image blackPawn = null;
                            try {
                                blackPawn = ImageIO.read(getClass().getResource("Assets/BlackPawn.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(blackPawn));
                        }

                        if (row == 6 && col < 8) {
                            Image whitePawn = null;
                            try {
                                whitePawn = ImageIO.read(getClass().getResource("Assets/WhitePawn.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(whitePawn));
                        }

                        if (row == 0 && (col == 0 || col == 7)) {
                            Image blackRook = null;
                            try {
                                blackRook = ImageIO.read(getClass().getResource("Assets/BlackRook.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(blackRook));
                        }

                        if (row == 7 && (col == 0 || col == 7)) {
                            Image whiteRook = null;
                            try {
                                whiteRook = ImageIO.read(getClass().getResource("Assets/WhiteRook.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(whiteRook));
                        }

                        if (row == 0 && (col == 1 || col == 6)) {
                            Image blackKnight = null;
                            try {
                                blackKnight = ImageIO.read(getClass().getResource("Assets/BlackKnight.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(blackKnight));
                        }

                        if (row == 7 && (col == 1 || col == 6)) {
                            Image whiteKnight = null;
                            try {
                                whiteKnight = ImageIO.read(getClass().getResource("Assets/WhiteKnight.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(whiteKnight));
                        }

                        if (row == 0 && (col == 2 || col == 5)) {
                            Image blackBishop = null;
                            try {
                                blackBishop = ImageIO.read(getClass().getResource("Assets/BlackBishop.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(blackBishop));
                        }

                        if (row == 7 && (col == 2 || col == 5)) {
                            Image whiteBishop = null;
                            try {
                                whiteBishop = ImageIO.read(getClass().getResource("Assets/WhiteBishop.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(whiteBishop));
                        }

                        if (row == 0 && col == 3) {
                            Image blackQueen = null;
                            try {
                                blackQueen = ImageIO.read(getClass().getResource("Assets/BlackQueen.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(blackQueen));
                        }

                        if (row == 7 && col == 3) {
                            Image whiteQueen = null;
                            try {
                                whiteQueen = ImageIO.read(getClass().getResource("Assets/WhiteQueen.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(whiteQueen));
                        }

                        if (row == 0 && col == 4) {
                            Image blackKing = null;
                            try {
                                blackKing = ImageIO.read(getClass().getResource("Assets/BlackKing.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(blackKing));
                        }

                        if (row == 7 && col == 4) {
                            Image whiteKing = null;
                            try {
                                whiteKing = ImageIO.read(getClass().getResource("Assets/WhiteKing.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(whiteKing));
                        }
                        button.setBorderPainted(true);
                        button.setPreferredSize(new Dimension(90, 90));
                        if((row+col)%2==0) {
                            button.setBackground(Color.WHITE);
                        }
                        else
                            button.setBackground(Color.PINK);
                        MyActionListener mal = new MyActionListener();
                        button.addActionListener(mal);
                        add(button, gbc);

                    }
                }
            }
        }

}
