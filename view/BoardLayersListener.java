package view;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;



public class BoardLayersListener extends JFrame {


    // JLabels
    JLabel boardlabel;
    private static JLabel cardLabel;

    // Side panel text
    JLabel curPlayer;
    JLabel curCredits;
    JLabel curCash;
    JLabel curRank;
    JLabel curLocation;



    // JButtons
    JButton bAct;
    JButton bRehearse;
    JButton bMove;


    // JLayeredPane
    private static JLayeredPane bPane;



    // Constructor

    public BoardLayersListener(model.Board model) throws IOException {
        // Set the title of the JFrame
        super("Deadwood");
        // Set the exit option for the JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the JLAyeredPan to hold the diplay, cards, role dice and buttons

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
        bAct.setBackground(Color.blue);
        bAct.setBounds(icon.getIconWidth()+10, 250, 140, 20);
        bAct.addMouseListener(new boardMouseListener());

        bPane.add(bAct, new Integer(2));


        // Rehearse Button
        bRehearse = new JButton("Rehearse");
        bRehearse.setBackground(Color.yellow);
        bRehearse.setBounds(icon.getIconWidth()+10, 300, 140, 20);
        bRehearse.addMouseListener(new boardMouseListener());

        bPane.add(bRehearse, new Integer(2));


        // Move Button
        bMove = new JButton("Move");
        bMove.setBackground(Color.green);
        bMove.setBounds(icon.getIconWidth()+10, 350, 140, 20);
        bMove.addMouseListener(new boardMouseListener());

        bPane.add(bMove, new Integer(2));





        curPlayer = new JLabel("Current Player : " );
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

    }

    class boardMouseListener implements MouseListener {

        // Code for the different button clicks
        public void mouseClicked(MouseEvent e) {

            if (e.getSource() == bAct) {
                System.out.println("Acting is selected\n");
            }
            else if (e.getSource() == bRehearse) {
                System.out.println("Rehearse is selected\n");
            }
            else /* (e.getSource() == bMove)*/ {
                System.out.println("Move is selected\n");
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

        bPane.add(cardLabel, new Integer(1));
    }

}
