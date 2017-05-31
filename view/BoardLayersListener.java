package view;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class BoardLayersListener extends JFrame {


    // JLabels
    JLabel boardlabel;



    // JButtons


    // JLayeredPane
    JLayeredPane bPane;



    // Constructor

    public BoardLayersListener(model.Board model) {
        // Set the title of the JFrame
        super("Deadwood");
        // Set the exit option for the JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the JLAyeredPan to hold the diplay, cards, role dice and buttons

        bPane = getLayeredPane();
        // Create the deadwood board
        boardlabel = new JLabel();
        ImageIcon icon = new ImageIcon("board.jpg");
        boardlabel.setIcon(icon);
        boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

        // Add the board to the lower layer
        bPane.add(boardlabel, new Integer(0));

        // Set the size of the GUI
        setSize(icon.getIconWidth() +200, icon.getIconHeight());

        // Add a scene card to this room

    }




}
