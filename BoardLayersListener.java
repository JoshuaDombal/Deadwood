package view;

import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import static model.Board.getSet;


public class BoardLayersListener extends JFrame {


    private static model.Player currentPlayer;

    private static model.Board board;


    // JLabels
    JLabel boardlabel;

    // Side panel text
    static JLabel curPlayer;
    static JLabel curCredits;
    static JLabel curCash;
    static JLabel curRank;
    static JLabel curLocation;
    static JLabel curRole;
    static JLabel curRehearsePoints;
    static JLabel cardLabel;
    //take jlabels
    static JLabal take1M;
    static JLabal take2M;
    static JLabal take3M;
    static JLabal takeS1;
    static JLabal takeS2;
    static JLabal takeB1;
    static JLabal takeC1;
    static JLabal takeC2;
    static JLabal takeH1;
    static JLabal takeH2;
    static JLabal takeH3;
    static JLabal takeJ1;
    static JLabal takeT1;
    static JLabal takeT2;
    static JLabal takeT3;
    static JLabal takeG1;
    static JLabal takeG2;
    static JLabal takeR1;
    static JLabal takeR2;
    static JLabal take1S;
    static JLabal take2S;
    static JLabal take3S;


    private static int layerCount = 1;



    // JButtons
    JButton bAct;
    JButton bRehearse;
    JButton bMove;
    JButton bTakeRole;
    JButton bUpgrade;
    JButton bEnd;


    // JLayeredPane
    static JLayeredPane bPane;



    // Constructor

    public BoardLayersListener(Board model) throws IOException {


        // Set the title of the JFrame
        super("Deadwood");
        // Set the exit option for the JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the JLAyeredPan to hold the diplay, cards, role dice and buttons


        this.board = model;

        bPane = getLayeredPane();
        // Create the deadwood board
        boardlabel = new JLabel();

        Class cls = getClass();
        cls.getResourceAsStream("board.jpg");

        ImageIcon icon = new ImageIcon(ImageIO.read(cls.getResourceAsStream("board.jpg")));
        boardlabel.setIcon(icon);
        boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

        // Add the board to the lower layer
        bPane.add(boardlabel, new Integer(0));

        // Set the size of the GUI
        setSize(icon.getIconWidth() +400, icon.getIconHeight());

        // Add a scene card to this room




        // Act Button
        bAct = new JButton("ACT");
        bAct.setBackground(Color.cyan);
        bAct.setBounds(icon.getIconWidth()+10, 250, 140, 20);
        bAct.addMouseListener(new boardMouseListener());

        bPane.add(bAct, new Integer(2));


        // Rehearse Button
        bRehearse = new JButton("REHEARSE");
        bRehearse.setBackground(Color.yellow);
        bRehearse.setBounds(icon.getIconWidth()+10, 300, 140, 20);
        bRehearse.addMouseListener(new boardMouseListener());

        bPane.add(bRehearse, new Integer(2));


        // Move Button
        bMove = new JButton("MOVE");
        bMove.setBackground(Color.green);
        bMove.setBounds(icon.getIconWidth()+10, 350, 140, 20);
        bMove.addMouseListener(new boardMouseListener());

        bPane.add(bMove, new Integer(2));

        // Take Role Button
        bTakeRole = new JButton("TAKE ROLE");
        bTakeRole.setBackground(Color.red);
        bTakeRole.setBounds(icon.getIconWidth()+10, 400, 140, 20);
        bTakeRole.addMouseListener(new boardMouseListener());

        bPane.add(bTakeRole, new Integer(2));

        // Upgrade Button
        bUpgrade = new JButton("UPGRADE");
        bUpgrade.setBackground(Color.cyan);
        bUpgrade.setBounds(icon.getIconWidth()+10, 450, 140, 20);
        bUpgrade.addMouseListener(new boardMouseListener());

        bPane.add(bUpgrade, new Integer(2));

        // End Turn Button
        bEnd = new JButton("END TURN");
        bEnd.setBackground(Color.green);
        bEnd.setBounds(icon.getIconWidth()+10, 500, 140, 20);
        bEnd.addMouseListener(new boardMouseListener());

        bPane.add(bEnd, new Integer(2));




        //String playerName =

        curPlayer = new JLabel("Current Player : " + model.getCurPlayer().getName() );
        curPlayer.setBounds(1210, 0, 300, 50);
        bPane.add(curPlayer, new Integer(1));

        curCredits = new JLabel("Credits : ");
        curCredits.setBounds(1210, 35, 300, 50);
        bPane.add(curCredits, new Integer(1));

        curCash = new JLabel("Cash : ");
        curCash.setBounds(1210, 70, 300, 50);
        bPane.add(curCash, new Integer(1));

        curRank = new JLabel("Rank : ");
        curRank.setBounds(1210, 105, 300, 50);
        bPane.add(curRank, new Integer(1));

        curLocation = new JLabel("Location : ");
        curLocation.setBounds(1210, 140, 300, 50);
        bPane.add(curLocation, new Integer(1));

        curRole = new JLabel("Location : ");
        curRole.setBounds(1210, 175, 300, 50);
        bPane.add(curRole, new Integer(1));

        curRehearsePoints = new JLabel("Rehearse Points : 0");
        curRehearsePoints.setBounds(1210, 210, 300, 50);
        bPane.add(curRehearsePoints, new Integer(1));

    }


    public static void displaySceneCard(model.Set set) throws Exception{
        model.SceneCard card = set.getScene();
        int[] cardArea = set.getArea();
        int sceneNumber = card.getSceneNumber();
        String fileName;

        if(card.checkFacedown()){
            fileName = "faceDown.png";
        }else{
            if(sceneNumber < 10){
                fileName = "0" + sceneNumber + ".png";
            }else{
                fileName = sceneNumber + ".png";
            }
        }
        System.out.println("Image: " + fileName);

        cardLabel = new JLabel();
        ImageIcon cIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream(fileName)));
        //ImageIcon cIcon = new ImageIcon(ImageIO.read(new File(fileName)));
        //ImageIcon cIcon = new ImageIcon(fileName);
        cardLabel.setIcon(cIcon);
        cardLabel.setBounds(cardArea[0],cardArea[1],cardArea[3],cardArea[2]);
        if(!card.checkIfDone()){
            //cardLabel.setOpaque(false);
            cardLabel.setVisible(true);
        }else{
            cardLabel.setVisible(false);
        }

        bPane.add(cardLabel, new Integer(layerCount));
        layerCount++;
    }

    public static void updateTakes(model.Set set) throws Exception{
        model.Take[] takes = set.getTakes();
        int takesNum = set.getNumTokens() + 1;
        String setName = set.getName();
        if(setName.equals("Main Street")){
            if(takeNum == 3){
                take3M.setVisible(false);
            }else if(takeNum == 2){
                take2M.setVisible(false);
            }else{
                take1M.setVisible(false);
            }
        }else if(setName.equals("Saloon")){
            if(takeNum == 2){
                takeS2.setVisible(false);
            }else{
                takeS1.setVisible(false);
            }
        }else if(setName.equals("Ranch")){
            if(takeNum == 2){
                takeR2.setVisible(false);
            }else{
                takeR1.setVisible(false);
            }
        }else if(setName.equals("General Store")){
            if(takeNum == 2){
                takeG2.setVisible(false);
            }else{
                takeG1.setVisible(false);
            }
        }else if(setName.equals("Church")){
            if(takeNum == 2){
                takeC2.setVisible(false);
            }else{
                takeC1.setVisible(false);
            }
        }else if(setName.equals("Bank")){
            takeB1.setVisible(false);
        }else if(setName.equals("Jail")){
            takeJ1.setVisible(false);
        }else if(setName.equals("Hotel")){
            if(takeNum == 3){
                takeH3.setVisible(false);
            }else if(takeNum == 2){
                takeH2.setVisible(false);
            }else{
                takeH1.setVisible(false);
            }
        }else if(setName.equals("Train Station")){
            if(takeNum == 3){
                takeT3.setVisible(false);
            }else if(takeNum == 2){
                takeT2.setVisible(false);
            }else{
                takeT1.setVisible(false);
            }
        }else if(setName.equals("Secret Hideout")){
            if(takeNum == 3){
                take3S.setVisible(false);
            }else if(takeNum == 2){
                take2S.setVisible(false);
            }else{
                take1S.setVisible(false);
            }
        }
    }

    public static void setAllTakes(boolean bool){
        take1M.setVisible(bool);
        take2M.setVisible(bool);
        take3M.setVisible(bool);
        takeS1.setVisible(bool);
        takeS2.setVisible(bool);
        takeB1.setVisible(bool);
        takeC1.setVisible(bool);
        takeC2.setVisible(bool);
        takeH1.setVisible(bool);
        takeH2.setVisible(bool);
        takeH3.setVisible(bool);
        takeJ1.setVisible(bool);
        takeT1.setVisible(bool);
        takeT2.setVisible(bool);
        takeT3.setVisible(bool);
        takeG1.setVisible(bool);
        takeG2.setVisible(bool);
        takeR1.setVisible(bool);
        takeR2.setVisible(bool);
        take1S.setVisible(bool);
        take2S.setVisible(bool);
        take3S.setVisible(bool);
    }

    public static void loadTakes(ArrayList<model.Set> sets){
        take1M = new JLabel();
        take2M = new JLabel();
        take3M = new JLabel();
        takeS1 = new JLabel();
        takeS2 = new JLabel();
        takeB1 = new JLabel();
        takeC1 = new JLabel();
        takeC2 = new JLabel();
        takeH1 = new JLabel();
        takeH2 = new JLabel();
        takeH3 = new JLabel();
        takeJ1 = new JLabel();
        takeT1 = new JLabel();
        takeT2 = new JLabel();
        takeT3 = new JLabel();
        takeG1 = new JLabel();
        takeG2 = new JLabel();
        takeR1 = new JLabel();
        takeR2 = new JLabel();
        take1S = new JLabel();
        take2S = new JLabel();
        take3S = new JLabel();

        ImageIcon cIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream("shot.png")));

        take1M.setIcon(cIcon);
        take2M.setIcon(cIcon);
        take3M.setIcon(cIcon);
        takeS1.setIcon(cIcon);
        takeS2.setIcon(cIcon);
        takeB1.setIcon(cIcon);
        takeC1.setIcon(cIcon);
        takeC2.setIcon(cIcon);
        takeH1.setIcon(cIcon);
        takeH2.setIcon(cIcon);
        takeH3.setIcon(cIcon);
        takeJ1.setIcon(cIcon);
        takeT1.setIcon(cIcon);
        takeT2.setIcon(cIcon);
        takeT3.setIcon(cIcon);
        takeG1.setIcon(cIcon);
        takeG2.setIcon(cIcon);
        takeR1.setIcon(cIcon);
        takeR2.setIcon(cIcon);
        take1S.setIcon(cIcon);
        take2S.setIcon(cIcon);
        take3S.setIcon(cIcon);

        take1M.setBounds(804,23,47,47);
        take2M.setBounds(858,23,47,47);
        take3M.setBounds(912,23,47,47);
        takeS1.setBounds(679,216,47,47);
        takeS2.setBounds(626,216,47,47);
        takeB1.setBounds(840,549,47,47);
        takeC1.setBounds(682,675,47,47);
        takeC2.setBounds(623,675,47,47);
        takeH1.setBounds(1111,683,47,47);
        takeH2.setBounds(1058,683,47,47);
        takeH3.setBounds(1005,683,47,47);
        takeJ1.setBounds(442,156,47,47);
        takeT1.setBounds(141,11,47,47);
        takeT2.setBounds(89,11,47,47);
        takeT3.setBounds(36,11,47,47);
        takeG1.setBounds(313,330,47,47);
        takeG2.setBounds(313,277,47,47);
        takeR1.setBounds(525,473,47,47);
        takeR2.setBounds(472,473,47,47);
        take1S.setBounds(354,764,47,47);
        take2S.setBounds(299,764,47,47);
        take3S.setBounds(244,764,47,47);

        bPane.add(take1M, new Integer(1));
        bPane.add(take2M, new Integer(1));
        bPane.add(take3M, new Integer(1));
        bPane.add(takeS1, new Integer(1));
        bPane.add(takeS2, new Integer(1));
        bPane.add(takeB1, new Integer(1));
        bPane.add(takeC1, new Integer(1));
        bPane.add(takeC2, new Integer(1));
        bPane.add(takeH1, new Integer(1));
        bPane.add(takeH2, new Integer(1));
        bPane.add(takeH3, new Integer(1));
        bPane.add(takeJ1, new Integer(1));
        bPane.add(takeT1, new Integer(1));
        bPane.add(takeT2, new Integer(1));
        bPane.add(takeT3, new Integer(1));
        bPane.add(takeG2, new Integer(1));
        bPane.add(takeR1, new Integer(1));
        bPane.add(takeR2, new Integer(1));
        bPane.add(take1S, new Integer(1));
        bPane.add(take2S, new Integer(1));
        bPane.add(take3S, new Integer(1));

        //BoardLayersListener.loadTakes(sets);
        //BoardLayersListener.setAllTakes(true);

    }


    class boardMouseListener implements MouseListener {

        // Code for the different button clicks
        public void mouseClicked(MouseEvent e) {

            if (e.getSource() == bAct) {
                System.out.println("Acting is selected\n");
                board.setCommand("Act");
                board.setChoiceMade(true);


            }
            else if (e.getSource() == bRehearse) {
                System.out.println("Rehearse is selected\n");
                board.setCommand("Rehearse");
                board.setChoiceMade(true);
            }
            else  if (e.getSource() == bMove) {
                System.out.println("Move is selected\n");
                board.setCommand("move");
                try {
                    if (currentPlayer.getRole() == null) {
                        displayRoomChoices(board);
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            else  if (e.getSource() == bTakeRole) {
                System.out.println("Take role is selected\n");
                board.setCommand("work");
                try {
                    if (currentPlayer.getRole() == null) {
                        displayRoleChoices(board);
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            else  if (e.getSource() == bUpgrade) {
                System.out.println("Upgrade is selected\n");
            }
            else  if (e.getSource() == bEnd) {
                System.out.println("End is selected\n");
                board.setCommand("end");
                board.setChoiceMade(true);
            } else {
                System.out.println("No action");
            }
        }


        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        String color;
        BoardLayersListener.currentPlayer = currentPlayer;
        if (currentPlayer.getColor() == 'b') {
            color  = "blue";
            //'b', 'c', 'g', 'o', 'p', 'r', 'v', 'y'};
        } else if (currentPlayer.getColor() == 'c') {
            color = "cyan";
        } else if (currentPlayer.getColor() == 'g') {
            color = "green";
        } else if (currentPlayer.getColor() == 'o') {
            color = "orange";
        } else if (currentPlayer.getColor() == 'p') {
            color = "purple";
        } else if (currentPlayer.getColor() == 'r') {
            color = "red";
        } else if (currentPlayer.getColor() == 'v') {
            color = "violet";
        } else if (currentPlayer.getColor() == 'y') {
            color = "yellow";
        } else {
            color = "   ";
        }
        curPlayer.setText(String.format("Current Player : %s", color));
    }


    public static void displayRoomChoices(model.Board model) throws Exception{

        JFrame frame = new JFrame("Room Choices");

        JPanel panel = new JPanel();
        JLabel title = new JLabel("Which Room Would You Like To Move To");

        String[] neighbors = currentPlayer.getRoom().getNeighbors();

        //int i = 0;
        //while (neighbors[i] != null && i < neighbors.length) {
            JButton button1 = new JButton(neighbors[0]);

            button1.setBounds(100, 40, 400, 30);
            panel.add(button1);
            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Player curPlayer = Board.getCurPlayer();
                    curPlayer.updateRoom(Board.getRoom(neighbors[0]));
                    board.setChoice(neighbors[0]);
                    System.out.println(neighbors[0] + " selected");
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
            //i++;

            JButton button2 = new JButton(neighbors[1]);

            button2.setBounds(100, 80, 400, 30);
            panel.add(button2);
            button2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Player curPlayer = Board.getCurPlayer();
                    curPlayer.updateRoom(Board.getRoom(neighbors[1]));
                    board.setChoice(neighbors[1]);
                    System.out.println(neighbors[1] + " selected");
                    frame.setVisible(false);
                    frame.dispose();
                }
            });


            JButton button3 = new JButton(neighbors[2]);

            button3.setBounds(100, 120, 400, 30);
            panel.add(button3);
            button3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Player curPlayer = Board.getCurPlayer();
                    curPlayer.updateRoom(Board.getRoom(neighbors[2]));
                    board.setChoice(neighbors[2]);
                    System.out.println(neighbors[2] + " selected");
                    frame.setVisible(false);
                    frame.dispose();
                }
            });


            if (neighbors.length == 4) {
                JButton button4 = new JButton(neighbors[3]);

                button4.setBounds(100, 120, 400, 30);
                panel.add(button4);
                button4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Player curPlayer = Board.getCurPlayer();
                        curPlayer.updateRoom(Board.getRoom(neighbors[3]));
                        model.setChoice(neighbors[3]);
                        System.out.println(neighbors[3] + " selected");
                        frame.setVisible(false);
                        frame.dispose();
                    }
                });
            }





        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        title.setBounds(190, 10, 400, 30);
        panel.setLayout(null);
        panel.add(title);



        frame.add(panel);
        frame.setVisible(true);


    }




    public static void displayRoleChoices(model.Board model) throws Exception{

        Set set = getSet(currentPlayer.getRoom().getName());
        SceneCard scene = set.getScene();

        if (!(scene.checkIfDone())) {
            JFrame frame = new JFrame("Role Choices");

            JPanel panel = new JPanel();
            JLabel title = new JLabel("Which Role Would You Like To Take");

            //String[] neighbors = currentPlayer.getRoom().getNeighbors();


            model.Role[] setRoles = set.getRoles();

            //look through the set roles to see if their choice was valid
            for(int i = 0; i < setRoles.length; i++) {

                if (setRoles[i] == null) {
                    break;
                }
            }


            //int i = 0;
            //while (neighbors[i] != null && i < neighbors.length) {

            if (setRoles[0] != null) {
                if (setRoles[0].getRank() <= currentPlayer.getRank()) {
                    JButton button1 = new JButton(setRoles[0].getName());

                    button1.setBounds(100, 40, 400, 30);
                    panel.add(button1);
                    button1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Player curPlayer = Board.getCurPlayer();
                            curPlayer.setRole(setRoles[0]);
                            board.setChoice(setRoles[0].getName());
                            System.out.println(setRoles[0].getName() + " selected");
                            frame.setVisible(false);
                            frame.dispose();
                        }
                    });
                }
            }

            if (setRoles[1] != null) {
                if (setRoles[1].getRank() <= currentPlayer.getRank()) {

                    JButton button2 = new JButton(setRoles[1].getName());

                    button2.setBounds(100, 80, 400, 30);
                    panel.add(button2);
                    button2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Player curPlayer = Board.getCurPlayer();
                            curPlayer.setRole(setRoles[1]);
                            board.setChoice(setRoles[1].getName());
                            System.out.println(setRoles[1].getName() + " selected");
                            frame.setVisible(false);
                            frame.dispose();
                        }
                    });
                }
            }

            if (setRoles[2] != null) {
                if (setRoles[2].getRank() <= currentPlayer.getRank()) {
                    JButton button3 = new JButton(setRoles[2].getName());

                    button3.setBounds(100, 120, 400, 30);
                    panel.add(button3);
                    button3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Player curPlayer = Board.getCurPlayer();
                            curPlayer.setRole(setRoles[2]);
                            board.setChoice(setRoles[2].getName());
                            System.out.println(setRoles[2].getName() + " selected");
                            frame.setVisible(false);
                            frame.dispose();
                        }
                    });
                }
            }


            if (setRoles[3] != null) {
                if (setRoles[3].getRank() <= currentPlayer.getRank()) {
                    JButton button3 = new JButton(setRoles[2].getName());

                    button3.setBounds(100, 120, 400, 30);
                    panel.add(button3);
                    button3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Player curPlayer = Board.getCurPlayer();
                            curPlayer.setRole(setRoles[2]);
                            board.setChoice(setRoles[2].getName());
                            System.out.println(setRoles[2].getName() + " selected");
                            frame.setVisible(false);
                            frame.dispose();
                        }
                    });
                }
            }



            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            title.setBounds(190, 10, 400, 30);
            panel.setLayout(null);
            panel.add(title);



            frame.add(panel);
            frame.setVisible(true);

        }




    }






    public static void setCurrentCredits(Player currentPlayer) {
        curCredits.setText(String.format("Credits : %s", currentPlayer.getCredits()));
    }

    public static void setCurrentCash(Player currentPlayer) {
        curCash.setText(String.format("Cash : %s", currentPlayer.getCash()));
    }

    public static void setCurrentRank(Player currentPlayer) {
        curRank.setText(String.format("Rank : %s", currentPlayer.getRank()));
    }

    public static void setCurrentLocation(Player currentPlayer) {
        curLocation.setText(String.format("Location : %s", currentPlayer.getRoom().getName()));
    }

    public static void setCurrentRole(Player currentPlayer) {
        if (currentPlayer.getRole() != null) {
            curRole.setText(String.format("Role : %s", currentPlayer.getRole().getName()));
        } else {
            curRole.setText(String.format( "Not on a role"));
        }

    }

    public static void setCurrentRehearsePoints(Player currentPlayer) {
        if (currentPlayer.getRole() != null) {
            curRehearsePoints.setText(String.format( "Rehearse Points: %s", currentPlayer.getRole().getRehearseBonus()));
        } else {
            curRehearsePoints.setText(String.format( "Rehearse Points: 0"));
        }

    }



}
