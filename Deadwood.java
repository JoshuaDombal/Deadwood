
import view.*;


import java.util.*;
import java.util.Scanner;
import java.lang.*;
import java.util.concurrent.ThreadLocalRandom;

//View
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.ImageIcon;

public class Deadwood {
    public static boolean displayGame = false;
    private static model.Board m;
    //private static view.Board v;
    private static controller.Board c;


    //bools for the loops in main and play game

    private static class Closer extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    public void setDisplayGame(boolean v) {
        this.displayGame = v;
    }

    public static void main(String[] args) throws Exception {


        model.Board.startGame();

        m = new model.Board();
        //v = new view.Board(m);

        c = new controller.Board(m);


        getNumPlayers();



        while(!displayGame){
            System.out.print("");
        }

        BoardLayersListener board = new BoardLayersListener(m);
        board.setVisible(true);




        //frame.addWindowListener(new Closer());


        while (m.daysRemaining != 0) {
            m.playGame();
            m.startDay();
        }
        m.endGame();







    }


    public static void getNumPlayers() throws Exception{

        JFrame frame = new JFrame("Player Menu");


        JButton button2 = new JButton("2 Players --> (Play 3 days)");
        JButton button3 = new JButton("3 Players --> (Play 3 days)");
        JButton button4 = new JButton("4 Players --> (Play 4 days)");
        JButton button5 = new JButton("5 Players --> (Start with 2 credits)");
        JButton button6 = new JButton("6 Players --> (Start with 4 credits)");
        JButton button7 = new JButton("7 Players --> (Start at rank 2)");
        JButton button8 = new JButton("8 Players --> (Start at rank 2)");


        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        JPanel panel = new JPanel();
        JLabel title = new JLabel("How Many Players?");
        title.setBounds(233, 40, 400, 30);
        panel.setLayout(null);
        panel.add(title);

        button2.setBounds(100, 80, 400, 30);
        panel.add(button2);
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                m.setNumPlayers(2);
                displayGame = true;
                m.daysRemaining = 3;
                //setting the playflag to 0 means that there are no changes besides number of days

                int playFlag = 0;
                m.createPlayers(playFlag);
                frame.setVisible(false);
                frame.dispose();
            }
        });


        button3.setBounds(100, 120, 400, 30);
        panel.add(button3);
        button3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                m.setNumPlayers(3);
                displayGame = true;
                m.daysRemaining = 3;
                //setting the playflag to 0 means that there are no changes besides number of days
                int playFlag = 0;
                m.createPlayers(playFlag);
                frame.setVisible(false);
                frame.dispose();
            }
        });


        button4.setBounds(100, 160, 400, 30);
        panel.add(button4);
        button4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                m.setNumPlayers(4);
                displayGame = true;
                //setting the playflag to 0 means that there are no changes besides number of days
                int playFlag = 0;
                m.createPlayers(playFlag);
                frame.setVisible(false);
                frame.dispose();
            }
        });


        button5.setBounds(100, 200, 400, 30);
        panel.add(button5);
        button5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                m.setNumPlayers(5);
                displayGame = true;
                //setting the playflag to 1 means the players will get 2 credits on creation
                int playFlag = 1;
                m.createPlayers(playFlag);
                frame.setVisible(false);
                frame.dispose();
            }
        });


        button6.setBounds(100, 240, 400, 30);
        panel.add(button6);
        button6.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                m.setNumPlayers(6);
                displayGame = true;
                //setting the playflag to 2 means the players will get 4 credits on creation
                int playFlag = 2;
                m.createPlayers(playFlag);
                frame.setVisible(false);
                frame.dispose();
            }
        });


        button7.setBounds(100, 280, 400, 30);
        panel.add(button7);
        button7.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                m.setNumPlayers(7);
                displayGame = true;
                //setting the playflag to 3 means the players will start at rank 2 on creation
                int playFlag = 3;
                m.createPlayers(playFlag);
                frame.setVisible(false);
                frame.dispose();
            }
        });


        button8.setBounds(100, 320, 400, 30);
        panel.add(button8);
        button8.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                m.setNumPlayers(8);
                displayGame = true;
                //setting the playflag to 3 means the players will start at rank 2 on creation
                int playFlag = 3;
                m.createPlayers(playFlag);
                frame.setVisible(false);
                frame.dispose();
            }
        });


        frame.add(panel);
        frame.setVisible(true);


    }



}
