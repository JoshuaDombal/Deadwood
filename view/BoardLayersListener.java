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

    public static void displayTakes(model.Set set) throws Exception{
        model.Take[] takes = set.getTakes();
        int takesLeft = set.getNumTokens();

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
