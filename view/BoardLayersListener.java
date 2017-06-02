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
import java.util.ArrayList;

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
    static JLabel curSet;
    static JLabel daysR;
    static JLabel MessageHeader;
    static JLabel Message1;
    static JLabel Message2;
    static JLabel Message3;
    static JLabel Message4;
    static JLabel Message5;
    static JLabel Message6;
    static JLabel Message7;
    static JLabel Message8;

    private static int layerCount = 1;



    // JButtons
    JButton bAct;
    JButton bRehearse;
    JButton bMove;
    JButton bTakeRole;
    JButton bUpgrade;
    JButton bEnd;

    //take jlabels
    static JLabel take1M;
    static JLabel take2M;
    static JLabel take3M;
    static JLabel takeS1;
    static JLabel takeS2;
    static JLabel takeB1;
    static JLabel takeC1;
    static JLabel takeC2;
    static JLabel takeH1;
    static JLabel takeH2;
    static JLabel takeH3;
    static JLabel takeJ1;
    static JLabel takeT1;
    static JLabel takeT2;
    static JLabel takeT3;
    static JLabel takeG1;
    static JLabel takeG2;
    static JLabel takeR1;
    static JLabel takeR2;
    static JLabel take1S;
    static JLabel take2S;
    static JLabel take3S;

    //player JLabels
    static JLabel Player1;
    static JLabel Player2;
    static JLabel Player3;
    static JLabel Player4;
    static JLabel Player5;
    static JLabel Player6;
    static JLabel Player7;
    static JLabel Player8;


    // JLayeredPane
    static JLayeredPane bPane;

    //starting locations for rooms
    private static int[] TSstart = {21,184,40,40};
    private static int[] SHstart = {27,847,40,40};
    private static int[] Cstart = {623,849,40,40};
    private static int[] Hstart = {969,855,40,40};
    private static int[] MSstart = {969,143,40,40};
    private static int[] Jstart = {281,142,40,40};
    private static int[] GSstart = {370,397,40,40};
    private static int[] Rstart = {252,593,40,40};
    private static int[] Bstart = {623,590,40,40};
    private static int[] Sstart = {632,395,40,40};
    private static int[] Tstart = {1000,270,40,40};
    private static int[] Ostart = {15,470,40,40};



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
        bAct.setBounds(icon.getIconWidth()+10, 300, 140, 20);
        bAct.addMouseListener(new boardMouseListener());

        bPane.add(bAct, new Integer(2));


        // Rehearse Button
        bRehearse = new JButton("REHEARSE");
        bRehearse.setBackground(Color.yellow);
        bRehearse.setBounds(icon.getIconWidth()+10, 350, 140, 20);
        bRehearse.addMouseListener(new boardMouseListener());

        bPane.add(bRehearse, new Integer(2));


        // Move Button
        bMove = new JButton("MOVE");
        bMove.setBackground(Color.green);
        bMove.setBounds(icon.getIconWidth()+10, 400, 140, 20);
        bMove.addMouseListener(new boardMouseListener());

        bPane.add(bMove, new Integer(2));

        // Take Role Button
        bTakeRole = new JButton("TAKE ROLE");
        bTakeRole.setBackground(Color.red);
        bTakeRole.setBounds(icon.getIconWidth()+10, 450, 140, 20);
        bTakeRole.addMouseListener(new boardMouseListener());

        bPane.add(bTakeRole, new Integer(2));

        // Upgrade Button
        bUpgrade = new JButton("UPGRADE");
        bUpgrade.setBackground(Color.cyan);
        bUpgrade.setBounds(icon.getIconWidth()+10, 500, 140, 20);
        bUpgrade.addMouseListener(new boardMouseListener());

        bPane.add(bUpgrade, new Integer(2));

        // End Turn Button
        bEnd = new JButton("END TURN");
        bEnd.setBackground(Color.green);
        bEnd.setBounds(icon.getIconWidth()+10, 550, 140, 20);
        bEnd.addMouseListener(new boardMouseListener());

        bPane.add(bEnd, new Integer(2));




        //String playerName =

        daysR = new JLabel("Days Remaining : " + model.daysRemaining);
        daysR.setBounds(1210, 0, 300, 50);
        bPane.add(daysR, new Integer(1));

        curPlayer = new JLabel("Current Player : " + model.getCurPlayer().getName() );
        curPlayer.setBounds(1210, 50, 300, 50);
        bPane.add(curPlayer, new Integer(1));

        curCredits = new JLabel("Credits : ");
        curCredits.setBounds(1210, 85, 300, 50);
        bPane.add(curCredits, new Integer(1));

        curCash = new JLabel("Cash : ");
        curCash.setBounds(1210, 120, 300, 50);
        bPane.add(curCash, new Integer(1));

        curRank = new JLabel("Rank : ");
        curRank.setBounds(1210, 155, 300, 50);
        bPane.add(curRank, new Integer(1));

        curLocation = new JLabel("Location : ");
        curLocation.setBounds(1210, 190, 300, 50);
        bPane.add(curLocation, new Integer(1));

        curRole = new JLabel("Location : ");
        curRole.setBounds(1210, 225, 300, 50);
        bPane.add(curRole, new Integer(1));

        curRehearsePoints = new JLabel("Rehearse Points : 0");
        curRehearsePoints.setBounds(1210, 260, 300, 50);
        bPane.add(curRehearsePoints, new Integer(1));

        MessageHeader = new JLabel("Message:");
        MessageHeader.setBounds(1210,580,300,50);
        bPane.add(MessageHeader, new Integer(1));

        Message1 = new JLabel("");
        Message1.setBounds(1210,600,350,75);
        bPane.add(Message1, new Integer(1));

        Message2 = new JLabel("");
        Message2.setBounds(1210,620,350,75);
        bPane.add(Message2, new Integer(1));

        Message3 = new JLabel("");
        Message3.setBounds(1210,640,350,75);
        bPane.add(Message3, new Integer(1));

        Message4 = new JLabel("");
        Message4.setBounds(1210,660,350,75);
        bPane.add(Message4, new Integer(1));

        Message5 = new JLabel("");
        Message5.setBounds(1210,680,350,75);
        bPane.add(Message5, new Integer(1));

        Message6 = new JLabel("");
        Message6.setBounds(1210,700,350,75);
        bPane.add(Message6, new Integer(1));

        Message7 = new JLabel("");
        Message7.setBounds(1210,720,350,75);
        bPane.add(Message7, new Integer(1));

        Message8 = new JLabel("");
        Message8.setBounds(1210,740,350,75);
        bPane.add(Message8, new Integer(1));

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
                if (!(board.getCommand().equals("move"))) {


                    try {
                        if (currentPlayer.getRole() == null) {
                            displayRoomChoices(board);
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
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
                if (currentPlayer.getRoom().getName().equals("office")) {

                    try {
                        displayUpgradeOptions();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

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


    public static void loadPlayers() throws Exception{

        int numPlayers = Board.getNumPlayers();

        Player1 = new JLabel();
        Player2 = new JLabel();
        Player3 = new JLabel();
        Player4 = new JLabel();
        Player5 = new JLabel();
        Player6 = new JLabel();
        Player7 = new JLabel();
        Player8 = new JLabel();

        ImageIcon bIcon;
        ImageIcon cIcon;
        ImageIcon gIcon;
        ImageIcon oIcon;
        ImageIcon pIcon;
        ImageIcon rIcon;
        ImageIcon vIcon;
        ImageIcon yIcon;

        if(numPlayers < 7){
            bIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream("b1.png")));
            cIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream("c1.png")));
            gIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream("g1.png")));
            oIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream("o1.png")));
            pIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream("p1.png")));
            rIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream("r1.png")));
            vIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream("v1.png")));
            yIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream("y1.png")));
        }else{
            bIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream("b2.png")));
            cIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream("c2.png")));
            gIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream("g2.png")));
            oIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream("o2.png")));
            pIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream("p2.png")));
            rIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream("r2.png")));
            vIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream("v2.png")));
            yIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream("y2.png")));
        }


        Player1.setIcon(bIcon);
        Player2.setIcon(cIcon);
        Player3.setIcon(gIcon);
        Player4.setIcon(oIcon);
        Player5.setIcon(pIcon);
        Player6.setIcon(rIcon);
        Player7.setIcon(vIcon);
        Player8.setIcon(yIcon);

        Player1.setBounds(1000,270,40,40);
        Player2.setBounds(1020,270,40,40);
        Player3.setBounds(1040,270,40,40);
        Player4.setBounds(1060,270,40,40);
        Player5.setBounds(1080,270,40,40);
        Player6.setBounds(1100,270,40,40);
        Player7.setBounds(1120,270,40,40);
        Player8.setBounds(1140,270,40,40);


        if(numPlayers < 8){
            Player8.setVisible(false);
            if(numPlayers < 7){
                Player7.setVisible(false);
                if(numPlayers < 6){
                    Player6.setVisible(false);
                    if(numPlayers < 5){
                        Player5.setVisible(false);
                        if(numPlayers < 4){
                            Player4.setVisible(false);
                            if(numPlayers < 3){
                                Player3.setVisible(false);
                            }
                        }
                    }
                }
            }
        }
        bPane.add(Player1, new Integer(1000));
        bPane.add(Player2, new Integer(1000));
        bPane.add(Player3, new Integer(1000));
        bPane.add(Player4, new Integer(1000));
        bPane.add(Player5, new Integer(1000));
        bPane.add(Player6, new Integer(1000));
        bPane.add(Player7, new Integer(1000));
        bPane.add(Player8, new Integer(1000));

    }

    public static void updateDieRank(model.Player player, int rank) throws Exception{
        String playerName = player.getName();
        Character color = player.getColor();
        String fileName = color + Integer.toString(rank) + ".png";
        if(playerName.equals("Player 1")){
            ImageIcon cIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream(fileName)));
            Player1.setIcon(cIcon);
        }else if(playerName.equals("Player 2")){
            ImageIcon cIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream(fileName)));
            Player2.setIcon(cIcon);
        }else if(playerName.equals("Player 3")){
            ImageIcon cIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream(fileName)));
            Player3.setIcon(cIcon);
        }else if(playerName.equals("Player 4")){
            ImageIcon cIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream(fileName)));
            Player4.setIcon(cIcon);
        }else if(playerName.equals("Player 5")){
            ImageIcon cIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream(fileName)));
            Player5.setIcon(cIcon);
        }else if(playerName.equals("Player 6")){
            ImageIcon cIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream(fileName)));
            Player6.setIcon(cIcon);
        }else if(playerName.equals("Player 7")){
            ImageIcon cIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream(fileName)));
            Player7.setIcon(cIcon);
        }else if(playerName.equals("Player 8")){
            ImageIcon cIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream(fileName)));
            Player8.setIcon(cIcon);
        }
    }

    public static void finishScene(model.Player player){
        model.Room room = player.getRoom();
        model.PlayerQueue players = Board.getPlayers();

        Player curr;
        int count = 0;
        do{
            curr = players.remove();
            if(curr.getRoom().getName().equals(room.getName())){
                movePlayer(curr.getName(), room.getName());
            }
            count++;
        }while(count < 8);


    }

    public static void moveToRole(model.Player player, model.Set set, model.Role role){

        String playerName = player.getName();
        int x = role.getX();
        int y = role.getY();
        int h = role.getH();
        int w = role.getW();
        int[] cardArea = set.getArea();

        if(playerName.equals("Player 1")){
            if(role.checkOnCard()){
                Player1.setBounds(x+cardArea[0],y+cardArea[1],h,w);
            }else{
                Player1.setBounds(x,y,h,w);
            }
        }else if(playerName.equals("Player 2")){
            if(role.checkOnCard()){
                Player2.setBounds(x+cardArea[0],y+cardArea[1],h,w);
            }else{
                Player2.setBounds(x,y,h,w);
            }
        }else if(playerName.equals("Player 3")){
            if(role.checkOnCard()){
                Player3.setBounds(x+cardArea[0],y+cardArea[1],h,w);
            }else{
                Player3.setBounds(x,y,h,w);
            }
        }else if(playerName.equals("Player 4")){
            if(role.checkOnCard()){
                Player4.setBounds(x+cardArea[0],y+cardArea[1],h,w);
            }else{
                Player4.setBounds(x,y,h,w);
            }
        }else if(playerName.equals("Player 5")){
            if(role.checkOnCard()){
                Player5.setBounds(x+cardArea[0],y+cardArea[1],h,w);
            }else{
                Player5.setBounds(x,y,h,w);
            }
        }else if(playerName.equals("Player 6")){
            if(role.checkOnCard()){
                Player6.setBounds(x+cardArea[0],y+cardArea[1],h,w);
            }else{
                Player6.setBounds(x,y,h,w);
            };
        }else if(playerName.equals("Player 7")){
            if(role.checkOnCard()){
                Player7.setBounds(x+cardArea[0],y+cardArea[1],h,w);
            }else{
                Player7.setBounds(x,y,h,w);
            }
        }else if(playerName.equals("Player 8")){
            if(role.checkOnCard()){
                Player8.setBounds(x+cardArea[0],y+cardArea[1],h,w);
            }else{
                Player8.setBounds(x,y,h,w);
            }
        }
    }

    public static void movePlayer(String playerName, String roomName){

        System.out.println("roomName: " + roomName);
        System.out.println("playerName: " + playerName);


        int[] bounds = {0,0,40,40};
        if(roomName.equals("Train Station")){
            bounds = TSstart;
        }else if(roomName.equals("Secret Hideout")){
            bounds = SHstart;
        }else if(roomName.equals("Church")){
            bounds = Cstart;
        }else if(roomName.equals("Hotel")){
            bounds = Hstart;
        }else if(roomName.equals("Main Street")){
            bounds = MSstart;
        }else if(roomName.equals("Jail")){
            bounds = Jstart;
        }else if(roomName.equals("General Store")){
            bounds = GSstart;
        }else if(roomName.equals("Ranch")){
            bounds = Rstart;
        }else if(roomName.equals("Bank")){
            bounds = Bstart;
        }else if(roomName.equals("Saloon")){
            bounds = Sstart;
        }else if(roomName.equals("Trailer")){
            bounds = Tstart;
        }else if(roomName.equals("office")){
            bounds = Ostart;
        }

        JLabel die;
        int offset = 0;
        if(playerName.equals("Player 1")){
            Player1.setBounds(bounds[0]+offset,bounds[1],40,40);
        }else if(playerName.equals("Player 2")){
            offset = 20;
            Player2.setBounds(bounds[0]+offset,bounds[1],40,40);
        }else if(playerName.equals("Player 3")){
            offset = 40;
            Player3.setBounds(bounds[0]+offset,bounds[1],40,40);
        }else if(playerName.equals("Player 4")){
            offset = 60;
            Player4.setBounds(bounds[0]+offset,bounds[1],40,40);
        }else if(playerName.equals("Player 5")){
            offset = 80;
            Player5.setBounds(bounds[0]+offset,bounds[1],40,40);
        }else if(playerName.equals("Player 6")){
            offset = 100;
            Player6.setBounds(bounds[0]+offset,bounds[1],40,40);
        }else if(playerName.equals("Player 7")){
            offset = 120;
            Player6.setBounds(bounds[0]+offset,bounds[1],40,40);
        }else if(playerName.equals("Player 8")){
            offset = 140;
            Player8.setBounds(bounds[0]+offset,bounds[1],40,40);
        }




    }


    public static void displayRoomChoices(model.Board model) throws Exception{


        JFrame frame = new JFrame("Room Choices");

        JPanel panel = new JPanel();
        JLabel title = new JLabel("Which Room Would You Like To Move To");

        String[] neighbors = currentPlayer.getRoom().getNeighbors();


        JButton button1 = new JButton(neighbors[0]);

        button1.setBounds(100, 40, 400, 30);
        panel.add(button1);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Player curPlayer = Board.getCurPlayer();
                //curPlayer.updateRoom(Board.getRoom(neighbors[0]));
                board.setCommand("move");
                board.setChoice(neighbors[0]);
                System.out.println(neighbors[0] + " selected");
                frame.setVisible(false);
                frame.dispose();
            }
        });


        JButton button2 = new JButton(neighbors[1]);

        button2.setBounds(100, 80, 400, 30);
        panel.add(button2);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Player curPlayer = Board.getCurPlayer();
                //curPlayer.updateRoom(Board.getRoom(neighbors[1]));
                board.setCommand("move");
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
                //Player curPlayer = Board.getCurPlayer();
                //curPlayer.updateRoom(Board.getRoom(neighbors[2]));
                board.setCommand("move");
                board.setChoice(neighbors[2]);
                System.out.println(neighbors[2] + " selected");
                frame.setVisible(false);
                frame.dispose();
            }
        });


        if (neighbors.length == 4) {
            if(neighbors[3] != null){
                JButton button4 = new JButton(neighbors[3]);
                System.out.println("neighbor: " + neighbors[3]);

                button4.setBounds(100, 160, 400, 30);
                panel.add(button4);
                button4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //Player curPlayer = Board.getCurPlayer();
                        //curPlayer.updateRoom(Board.getRoom(neighbors[3]));
                        board.setCommand("move");
                        model.setChoice(neighbors[3]);
                        System.out.println(neighbors[3] + " selected");
                        frame.setVisible(false);
                        frame.dispose();
                    }
                });
            }

        }


        JButton abortButton = new JButton("EXIT");

        abortButton.setBounds(200, 275, 200, 30);
        panel.add(abortButton);
        abortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                board.setChoice("");
                frame.setVisible(false);
                frame.dispose();
            }
        });


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
            JLabel title = new JLabel("Off Card Roles");
            JLabel title2 = new JLabel("On Card Roles");

            //String[] neighbors = currentPlayer.getRoom().getNeighbors();


            model.Role[] setRoles = set.getRoles();
            model.Role[] sceneRoles = scene.getRoles();

            //look through the set roles to see if their choice was valid
            for(int i = 0; i < setRoles.length; i++) {

                if (setRoles[i] == null) {
                    break;
                }
            }


            //int i = 0;
            //while (neighbors[i] != null && i < neighbors.length) {

            if (setRoles[0] != null) {
                if (setRoles[0].getRank() <= currentPlayer.getRank() && (setRoles[0].checkForPlayer()) == false) {
                    JButton button1 = new JButton(setRoles[0].getName());

                    button1.setBounds(65, 40, 200, 30);
                    panel.add(button1);
                    button1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            //Player curPlayer = Board.getCurPlayer();
                            //curPlayer.setRole(setRoles[0]);
                            board.setChoice(setRoles[0].getName());
                            System.out.println(setRoles[0].getName() + " selected");
                            frame.setVisible(false);
                            frame.dispose();
                        }
                    });
                }
            }

            if (setRoles[1] != null) {
                if (setRoles[1].getRank() <= currentPlayer.getRank() && (setRoles[1].checkForPlayer()) == false) {

                    JButton button2 = new JButton(setRoles[1].getName());

                    button2.setBounds(65, 80, 200, 30);
                    panel.add(button2);
                    button2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            //Player curPlayer = Board.getCurPlayer();
                            //curPlayer.setRole(setRoles[1]);
                            board.setChoice(setRoles[1].getName());
                            System.out.println(setRoles[1].getName() + " selected");
                            frame.setVisible(false);
                            frame.dispose();
                        }
                    });
                }
            }

            if (setRoles[2] != null) {
                if (setRoles[2].getRank() <= currentPlayer.getRank() && (setRoles[2].checkForPlayer()) == false) {
                    JButton button3 = new JButton(setRoles[2].getName());

                    button3.setBounds(65, 120, 200, 30);
                    panel.add(button3);
                    button3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            //Player curPlayer = Board.getCurPlayer();
                            //curPlayer.setRole(setRoles[2]);
                            board.setChoice(setRoles[2].getName());
                            System.out.println(setRoles[2].getName() + " selected");
                            frame.setVisible(false);
                            frame.dispose();
                        }
                    });
                }
            }


            if (setRoles[3] != null) {
                if (setRoles[3].getRank() <= currentPlayer.getRank() && (setRoles[3].checkForPlayer()) == false) {
                    JButton button3 = new JButton(setRoles[3].getName());

                    button3.setBounds(65, 120, 200, 30);
                    panel.add(button3);
                    button3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                           // Player curPlayer = Board.getCurPlayer();
                            //curPlayer.setRole(setRoles[2]);
                            board.setChoice(setRoles[2].getName());
                            System.out.println(setRoles[2].getName() + " selected");
                            frame.setVisible(false);
                            frame.dispose();
                        }
                    });
                }
            }


            if (sceneRoles[0] != null) {
                if (sceneRoles[0].getRank() <= currentPlayer.getRank() && (sceneRoles[0].checkForPlayer()) == false) {
                    JButton buttonA = new JButton(sceneRoles[0].getName());

                    buttonA.setBounds(345, 40, 200, 30);
                    panel.add(buttonA);
                    buttonA.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            //Player curPlayer = Board.getCurPlayer();
                            //curPlayer.setRole(setRoles[0]);
                            board.setChoice(sceneRoles[0].getName());
                            System.out.println(setRoles[0].getName() + " selected");
                            frame.setVisible(false);
                            frame.dispose();
                        }
                    });
                }
            }

            if (sceneRoles[1] != null) {
                if (sceneRoles[1].getRank() <= currentPlayer.getRank() && (sceneRoles[1].checkForPlayer()) == false) {
                    JButton buttonB = new JButton(sceneRoles[1].getName());

                    buttonB.setBounds(345, 80, 200, 30);
                    panel.add(buttonB);
                    buttonB.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            //Player curPlayer = Board.getCurPlayer();
                            //curPlayer.setRole(setRoles[0]);
                            board.setChoice(sceneRoles[1].getName());
                            System.out.println(setRoles[1].getName() + " selected");
                            frame.setVisible(false);
                            frame.dispose();
                        }
                    });
                }
            }

            if (sceneRoles[2] != null) {
                if (sceneRoles[2].getRank() <= currentPlayer.getRank() && (sceneRoles[2].checkForPlayer()) == false) {
                    JButton buttonC = new JButton(sceneRoles[2].getName());

                    buttonC.setBounds(345, 80, 200, 30);
                    panel.add(buttonC);
                    buttonC.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            //Player curPlayer = Board.getCurPlayer();
                            //curPlayer.setRole(setRoles[0]);
                            board.setChoice(sceneRoles[1].getName());
                            System.out.println(setRoles[1].getName() + " selected");
                            frame.setVisible(false);
                            frame.dispose();
                        }
                    });
                }
            }


            JButton abortButton = new JButton("EXIT");

            abortButton.setBounds(200, 275, 200, 30);
            panel.add(abortButton);
            abortButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    board.setChoice("");
                    frame.setVisible(false);
                    frame.dispose();
                }
            });


            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            title.setBounds(125, 10, 400, 30);
            title2.setBounds(400, 10, 400, 30);
            panel.setLayout(null);
            panel.add(title);
            panel.add(title2);



            frame.add(panel);
            frame.setVisible(true);

        }




    }


    public static void displayUpgradeOptions() throws Exception{

        JFrame frame = new JFrame("Upgrade: ");

        JPanel panel = new JPanel();
        JLabel title = new JLabel("Cash");
        JLabel title2 = new JLabel("Credits");

        if (currentPlayer.getCash() >= 4 && currentPlayer.getRank() < 2) {
            JButton button1 = new JButton("Rank 2 (4 Dollars)");

            button1.setBounds(50, 40, 200, 30);
            panel.add(button1);
            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {


                    board.setChoice("$ 2");
                    board.setCommand("upgrade");
                    //System.out.println(neighbors[0] + " selected");
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
        }

        if (currentPlayer.getCash() >= 10 && currentPlayer.getRank() < 3) {
            JButton button2 = new JButton("Rank 3 (10 Dollars)");

            button2.setBounds(50, 80, 200, 30);
            panel.add(button2);
            button2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    board.setChoice("$ 3");
                    board.setCommand("upgrade");
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
        }

        if (currentPlayer.getCash() >= 18 && currentPlayer.getRank() < 4) {
            JButton button3 = new JButton("Rank 4 (18 Dollars)");

            button3.setBounds(50, 120, 200, 30);
            panel.add(button3);
            button3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    board.setChoice("$ 4");
                    board.setCommand("upgrade");
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
        }

        if (currentPlayer.getCash() >= 28 && currentPlayer.getRank() < 5) {

            JButton button4 = new JButton("Rank 5 (28 Dollars)");

            button4.setBounds(50, 160, 200, 30);
            panel.add(button4);
            button4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    board.setChoice("$ 5");
                    board.setCommand("upgrade");
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
        }


        if (currentPlayer.getCash() >= 40 && currentPlayer.getRank() < 6) {
            JButton button5 = new JButton("Rank 6 (40 Dollars)");

            button5.setBounds(50, 200, 200, 30);
            panel.add(button5);
            button5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    board.setChoice("$ 6");
                    board.setCommand("upgrade");
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
        }


        if (currentPlayer.getCredits() >= 5 && currentPlayer.getRank() < 2) {
            JButton bRank2 = new JButton("Rank 2 (5 Credits)");

            bRank2.setBounds(350, 40, 200, 30);
            panel.add(bRank2);
            bRank2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    board.setChoice("cr 2");
                    board.setCommand("upgrade");
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
        }


        if (currentPlayer.getCredits() >= 10 && currentPlayer.getRank() < 3) {
            JButton bRank3 = new JButton("Rank 3 (10 Credits)");

            bRank3.setBounds(350, 80, 200, 30);
            panel.add(bRank3);
            bRank3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    board.setChoice("cr 3");
                    board.setCommand("upgrade");
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
        }


        if (currentPlayer.getCredits() >= 15 && currentPlayer.getRank() < 4) {
            JButton bRank4 = new JButton("Rank 4 (15 Credits)");

            bRank4.setBounds(350, 120, 200, 30);
            panel.add(bRank4);
            bRank4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    board.setChoice("cr 4");
                    board.setCommand("upgrade");
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
        }


        if (currentPlayer.getCredits() >= 15 && currentPlayer.getRank() < 5) {
            JButton bRank5 = new JButton("Rank 5 (20 Credits)");

            bRank5.setBounds(350, 160, 200, 30);
            panel.add(bRank5);
            bRank5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    board.setChoice("cr 5");
                    board.setCommand("upgrade");
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
        }

        if (currentPlayer.getCredits() >= 20 && currentPlayer.getRank() < 6) {
            JButton bRank6 = new JButton("Rank 6 (25 Credits)");

            bRank6.setBounds(350, 200, 200, 30);
            panel.add(bRank6);
            bRank6.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    board.setChoice("cr 6");
                    board.setCommand("upgrade");
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
        }


        JButton abortButton = new JButton("Don't Upgrade");

        abortButton.setBounds(250, 275, 200, 30);
        panel.add(abortButton);
        abortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                board.setChoice("cr 0");
                board.setCommand("upgrade");
                frame.setVisible(false);
                frame.dispose();
            }
        });






        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        title.setBounds(125, 10, 400, 30);
        title2.setBounds(425, 10, 400, 30);
        panel.setLayout(null);
        panel.add(title);
        panel.add(title2);



        frame.add(panel);
        frame.setVisible(true);


    }




    public static void displaySceneCard(model.Set set) throws Exception{
        model.SceneCard card = set.getScene();
        int[] cardArea = set.getArea();

        String fileName = "";
        if(card.checkIfDone()){
            if(set.getName().equals("Main Street")){
                fileName = "MS.png";
            }else if(set.getName().equals("Hotel")){
                fileName = "H.png";
            }else if(set.getName().equals("Saloon")){
                fileName = "S.png";
            }else if(set.getName().equals("Bank")){
                fileName = "B.png";
            }else if(set.getName().equals("Church")){
                fileName = "C.png";
            }else if(set.getName().equals("Jail")){
                fileName = "J.png";
            }else if(set.getName().equals("General Store")){
                fileName = "GS.png";
            }else if(set.getName().equals("Train Station")){
                fileName = "TS.png";
            }else if(set.getName().equals("Ranch")){
                fileName = "R.png";
            }else if(set.getName().equals("Secret Hideout")){
                fileName = "SH.png";
            }
        }else if(card.checkFacedown()){
            fileName = "faceDown.png";
        }else{
            fileName = card.getImage();
        }
        System.out.println("Image: " + fileName);

        cardLabel = new JLabel();
        ImageIcon cIcon = new ImageIcon(ImageIO.read(BoardLayersListener.class.getResourceAsStream(fileName)));
        //ImageIcon cIcon = new ImageIcon(ImageIO.read(new File(fileName)));
        //ImageIcon cIcon = new ImageIcon(fileName);
        cardLabel.setIcon(cIcon);
        cardLabel.setBounds(cardArea[0],cardArea[1],cardArea[3],cardArea[2]);
        /*
        if(!card.checkIfDone()){
            //cardLabel.setOpaque(false);
            cardLabel.setVisible(true);
        }else{
            cardLabel.setVisible(false);
        }
        */
        bPane.add(cardLabel, new Integer(layerCount));
        layerCount++;
    }





    public static void setCurDays(Player currentPlayer) {
        curCredits.setText(String.format("Days Remaining : %d", board.daysRemaining));
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

    public static void setMessage1(String message){
        Message1.setText(String.format(message));
    }

    public static void setMessage2(String message){
        Message2.setText(message);
    }

    public static void setMessage3(String message){
        Message3.setText(message);
    }

    public static void setMessage4(String message){
        Message4.setText(message);
    }

    public static void setMessage5(String message){
        Message5.setText(message);
    }

    public static void setMessage6(String message){
        Message6.setText(message);
    }

    public static void setMessage7(String message){
        Message7.setText(message);
    }

    public static void setMessage8(String message){
        Message8.setText(message);
    }

    public static void clearMessages(){
        Message1.setText("");
        Message2.setText("");
        Message3.setText("");
        Message4.setText("");
        Message5.setText("");
        Message6.setText("");
        Message7.setText("");
        Message8.setText("");
    }

/*
    public static void setCurrentScene(Player currentPlayer) {
        if (getSet(currentPlayer.getRoom().getName()).getScene() != null) {
            curRole.setText(String.format("Current Scene : %s", getSet(currentPlayer.getRoom().getName()).getScene().getName()));
        } else {
            curRole.setText(String.format( "No Scene"));
        }

    }

*/

    public static void updateTakes(model.Set set) throws Exception{
        model.Take[] takes = set.getTakes();
        int takeNum = set.getNumTokens() + 1;
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

    public static void loadTakes(ArrayList<Set> sets) throws Exception{
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
        bPane.add(takeG1, new Integer(1));
        bPane.add(takeG2, new Integer(1));
        bPane.add(takeR1, new Integer(1));
        bPane.add(takeR2, new Integer(1));
        bPane.add(take1S, new Integer(1));
        bPane.add(take2S, new Integer(1));
        bPane.add(take3S, new Integer(1));

    }

}
