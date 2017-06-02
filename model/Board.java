package model;

import view.BoardLayersListener;

import java.util.*;
import java.util.Scanner;
import java.lang.*;
import java.util.concurrent.ThreadLocalRandom;


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

import static view.BoardLayersListener.*;


public class Board {
    //bools for the loops in main and play game
    public static int daysRemaining = 4;
    private static int scenesRemaining = 10;
    //initialized to 1 for print statements in start game, exectly represents number of players
    private static int numPlayers = 1;
    //bool is true when players start game and when they day they would like to play again
    private static boolean wantToPlay = true;
    private static boolean displayGame = false;
    private static char[] colors = new char[] {'b', 'c', 'g', 'o', 'p', 'r', 'v', 'y'};

    //holds a first in first out queue of players in game
    private static PlayerQueue players = new PlayerQueue(8);
    //arrayLists of cards, rooms, and sets
    private static ArrayList<SceneCard> sceneCards;
    private static ArrayList<Room> rooms;
    private static ArrayList<Set> sets;

    private static Player curPlayer = new Player("null");
    private static String command = "";
    private static String choice = "";
    private static boolean choiceMade;



    //Outlines the basic loop of gameplay
    //Called once everyday
    /*
        while the day is not over...
            -pop a player from the PlayerQueue
            -present them with appropriate options and information for their turn
            -take input of there choice, check if it is valid, call corresponding method for choice
    */
    public static void playGame() throws Exception {

        for(int i = 0; i < sets.size(); i++){
            BoardLayersListener.displaySceneCard(sets.get(i));
        }

        BoardLayersListener.loadTakes(sets);
        BoardLayersListener.setAllTakes(true);
        BoardLayersListener.loadPlayers();


        while(scenesRemaining > 1){

            //pop player from queue
            Player current = players.remove();
            BoardLayersListener.setCurrentPlayer(current);
            BoardLayersListener.setCurrentCredits(current);
            BoardLayersListener.setCurrentCash(current);
            BoardLayersListener.setCurrentRank(current);
            BoardLayersListener.setCurrentLocation(current);
            BoardLayersListener.setCurrentRole(current);
            BoardLayersListener.setCurrentRehearsePoints(current);
            BoardLayersListener.setCurDays(current);

            System.out.println(current.getName());

            //establish input
           Scanner console = new Scanner(System.in);

            //booleans for checking the state of the players location and turn
            boolean moving = false;
            boolean inRole, inCO, mov, inTrl;
            inRole = inCO = mov = inTrl = false;
            boolean moveChoice = false;

            //initial variables for choice loop
            //String choice = null;
            boolean choiceNotValid = true;


            while(choiceNotValid){
                choiceMade = false;
                BoardLayersListener.setCurrentPlayer(current);
                BoardLayersListener.setCurrentCredits(current);
                BoardLayersListener.setCurrentCash(current);
                BoardLayersListener.setCurrentRank(current);
                BoardLayersListener.setCurrentLocation(current);
                BoardLayersListener.setCurrentRole(current);
                BoardLayersListener.setCurrentRehearsePoints(current);
                //BoardLayersListener.setCurrentScene(current);

                System.out.println("Available actions: \n");
                if(!(current.getRole() == (null))){
                    inRole = true;
                    System.out.println("    -who\n");
                    System.out.println("    -Where\n");
                    System.out.println("    -Act\n");
                    System.out.println("    -Rehearse\n");
                    System.out.println("    -end\n");
                    System.out.println(getSet(current.getRoom().getName()).getScene().getName());
                }else if((current.getRoom().getName().equals("office"))){
                    inCO = true;
                    System.out.println("    -who\n");
                    System.out.println("    -Where\n");
                    System.out.println("    -move\n");
                    if(current.getRank() != 6){
                        System.out.println("    -upgrade\n");
                    }
                    System.out.println("    -end\n");
                }else if((current.getRoom().getName().equals("Trailer")) && (!mov)){
                    inTrl = true;
                    System.out.println("    -who\n");
                    System.out.println("    -Where\n");
                    System.out.println("    -move\n");
                    System.out.println("    -end\n");
                }else if(!mov){
                    System.out.println("    -who\n");
                    System.out.println("    -Where\n");
                    System.out.println("    -move\n");
                    System.out.println("    -end\n");
                }else if((getSet(current.getRoom().getName()).getScene()) != null) {
                    System.out.println("    -who\n");
                    System.out.println("    -Where\n");
                    System.out.println("    -work\n");
                    System.out.println("    -end\n");
                }else{
                    System.out.println("    -end\n");
                }

                //ask the current player for input
                System.out.println(current.getName() + "'s turn: What would you like to do?\n");

                //get and save player input
                //String command = console.next();
                /*
                while (command.equals("") || choice.equals("")) {

                    System.out.println(command);
                    System.out.println(choice);


                }
                */
                while(!choiceMade){System.out.print("");}


                System.out.println(inRole);

                //choice = console.nextLine();
                if((command.equals("move")) && (mov)){
                    moveChoice = true;
                    BoardLayersListener.clearMessages();
                    BoardLayersListener.setMessage1("Cannot move twice in one turn");
                }else if((!inRole) && (command.equals("Act"))){
                    BoardLayersListener.clearMessages();
                    BoardLayersListener.setMessage1("Cannot act while not working");
                    moveChoice = true;
                }else if((command.equals("Act")) && (inRole)){

                    moveChoice = true;
                    Set set = getSet(current.getRoom().getName());
                    SceneCard card = set.getScene();
                    current.act(card, set);
                    if (set.getNumTokens() == 0) {
                        BoardLayersListener.finishScene(current);
                        card.setDone();
                        BoardLayersListener.displaySceneCard(set);
                        set.endScene(current);


                    }
                    choiceNotValid = false;

                //call rehearse if player types 'Rehearse'
                }else if((command.equals("Rehearse")) && (inRole)){
                    moveChoice = true;
                    //System.out.println("***********");
                    Set set = getSet(current.getRoom().getName());
                    SceneCard card = set.getScene();

                    //if the player rehearses successfully, pop out of the loop
                    if(current.rehearse(card)){
                        choiceNotValid = false;
                    }

                //if player types 'Move'...
                }else if((command.equals("move")) && (!inRole) && (!mov)){

                    //bool for location choice loop
                    boolean validLocation = false;


                    //grab the neighbors of the players current room
                    String[] adjacent = current.getRoom().getNeighbors();

                    //check adjacent to see if the room they chose is a neighboring room
                    for(int i = 0; i < adjacent.length; i++) {

                        if (adjacent[i] == null) {
                            break;
                        }

                        if (choice.contains(adjacent[i])) {

                            //set bool for location valid
                            validLocation = true;

                            //set bool indicating that player is moving and choice is valid
                            mov = true;

                            //System.out.println("Successful move!\n");

                            //update player room and rooms player
                            current.updateRoom(getRoom(adjacent[i]));

                            BoardLayersListener.movePlayer(current.getName(),current.getRoom().getName());

                            BoardLayersListener.clearMessages();
                            BoardLayersListener.setMessage1(current.getName() + " moved to " + current.getRoom().getName() + "!");

                            //set booleans to indicate if player is NOW in casting office or trialer
                            if (choice.contains("office")) {
                                inCO = true;
                            } else if (choice.contains("trailer")) {
                                inTrl = true;
                            }else{
                                Set set = getSet(current.getRoom().getName());
                                SceneCard card = set.getScene();
                                if(card.checkFacedown()){
                                    card.flipCard();
                                }
                                BoardLayersListener.displaySceneCard(getSet(choice));
                            }

                            System.out.print("\n");

                        }else if(choice.contains("tooffice")){
                            inCO = true;
                            current.updateRoom(getRoom("office"));
                            validLocation = true;
                            mov = true;
                            choiceNotValid = false;
                        }
                    }

                    if(!validLocation){
                        System.out.println("Error: Invalid location\n");
                    }

                //if choice equals upgrade, call upgrade

                }else if((command.equals("upgrade")) && (inCO)){

                    moveChoice = true;
                    //System.out.println("INNNNNNNNNNNN");

                    if(current.getRank() != 6){
                        if(current.upgrade(choice)){
                            BoardLayersListener.setMessage1("Player successfully upgraded to rank " + current.getRank() + "!\n");
                            //System.out.println("Player successfully upgraded to rank " + current.getRank() + "!\n");
                            choiceNotValid = false;
                        }

                    }else{
                        //System.out.println("\nCannot upgrade: Player already at max rank\n");
                        BoardLayersListener.clearMessages();
                        BoardLayersListener.setMessage1(current.getName() + " arleady at max rank!");
                    }

                    // Cheat code to give the player max $ and cr
                } else if(command.equals("tinkerbell")) {

                    current.updateCash(99999);
                    current.updateCredits(99999);

                //call who if the player types who
                }else if(command.equals("who")){
                    View.who(current);

                //call Where if the player types where
                }else if(command.equals("Where")){
                    View.where(current);

                //call work when the player types work and is on a set
                }else if((command.equals("work")) && (! ((inTrl) && (inCO)))){
                    //check if the play successfully took the role
                    if(work(current, choice)){
                        moveChoice = true;
                        choiceNotValid = false;

                    }

                //skip the current players turn if they type end
                }else if(command.equals("end")){
                    choiceNotValid = false;
                    //System.out.println(choice);
                    BoardLayersListener.clearMessages();
                    BoardLayersListener.setMessage1(current.getName() + " ended their turn");
                }

                //if the choice is not valid print an error and ask them to try again
                if((choiceNotValid) && (moveChoice) && (!mov)){
                    System.out.println("Woops! Please type a valid choice for your turn\n");
                }

            }

            //player turn ended, put player back in PlayerQueue
            System.out.println(current.getName() + "'s turn has ended...\n");
            System.out.println("\n------------------------------------------------------------------------\n");

        }

    }

    private static boolean work(Player current, String choice){

        boolean validRole = false;

        //grab the Room, Scene, Set
        Room currentRoom = current.getRoom();
        Set set = getSet(currentRoom.getName());
        SceneCard scene = set.getScene();

        //check if the scene is null
        if(scene == null){
            //System.out.println("Scene card is no longer in play\n");
            BoardLayersListener.clearMessages();
            BoardLayersListener.setMessage1("Scene card is no longer in play");
        }else{

            Role[] setRoles = set.getRoles();

            //look through the set roles to see if their choice was valid
            for(int i = 0; i < setRoles.length; i++){

                if(setRoles[i] == null){
                    break;
                }

                //if the role is in the set and it is not occupied, take role
                if(choice.contains(setRoles[i].getName())){

                    //if the role is unoccupied take the role
                    if((!setRoles[i].checkForPlayer()) && (setRoles[i].getRank() <= current.getRank())){
                        System.out.println("Role taken successfully\n");
                        current.updateRole(setRoles[i]);
                        setRoles[i].addPlayer(current);
                        validRole = true;
                        BoardLayersListener.moveToRole(current, getSet(current.getRoom().getName()), setRoles[i]);

                        BoardLayersListener.clearMessages();
                        BoardLayersListener.setMessage1(current.getName() + " is now working " + setRoles[i].getName());

                        //if the role is in the set but is occupied, print error and ask for input again via loop
                    }else if(!(setRoles[i].getRank() <= current.getRank())){
                        System.out.println("\nPlayer rank not high enough to take role\n");
                    }else{
                        System.out.println("\nRole already filled! Please choose again\n");
                    }
                }
            }

            //grab and save roles from the SceneCard
            Role[] cardRoles = scene.getRoles();

            //go through the roles on the scene card to check if player choice is valid
            for(int i = 0; i < cardRoles.length; i++){

                if(cardRoles[i] == null){
                    break;
                }

                //if the role is in the set and it is not occupied, take role
                if(choice.equals(cardRoles[i].getName())){

                    if((!cardRoles[i].checkForPlayer()) && (cardRoles[i].getRank() <= current.getRank())){
                        System.out.println("Role taken successfully\n");
                        set.getScene().occupiedStatus(true);
                        current.updateRole(cardRoles[i]);
                        cardRoles[i].addPlayer(current);
                        validRole = true;
                        BoardLayersListener.moveToRole(current, getSet(current.getRoom().getName()), cardRoles[i]);
                        BoardLayersListener.clearMessages();
                        BoardLayersListener.setMessage1(current.getName() + " is now working " + setRoles[i].getName());
                    }else if(!(cardRoles[i].getRank() <= current.getRank())){
                        System.out.println("\nPlayer rank not high enough to take role\n");
                    }else{
                        System.out.println("\nRole already filled! Please choose again\n");
                    }
                }
            }
        }


        if(!validRole){
            System.out.println("Invalid role! Please try again.\n");
        }


        return validRole;

    }

    //help function that is passed the name of a room and returns the corresponding room object
    public static Room getRoom(String roomName){
        //set equal to null incase room doesn't exist
        Room room = null;

        //check through the arrayList of rooms
        for(int i = 0; i < rooms.size(); i++){
            if(rooms.get(i).getName().equals(roomName)){
                room = rooms.get(i);
                return room;
            }
        }
        return room;
    }

    //help function that is passed the name of a set and returns the corresponding set object
    public static Set getSet(String setName){
        //set equal to null incase set doesn't exist
        Set set = null;

        //check through the arrayList of sets
        for(int i = 0; i < sets.size(); i++){
            if(sets.get(i).getName().equals(setName)){
                set = sets.get(i);
                return set;
            }
        }
        return set;
    }

    public static int getNumPlayers(){
        return numPlayers;
    }

    //called once at the end of a day
    //decrements the number of days left and reset the number of scenesRemaining
    //move all player back to the trailer
    public static void startDay() {

        daysRemaining--;
        scenesRemaining = 10;

        drawSceneCards();

        if(daysRemaining > 0){
            System.out.println("\n\n\n\n\n\n\n ***********STARTING NEW DAY**********\n\n\n\n\n\n\n\n");

            Room trailer = getRoom("trailer");

            for(int i = 0; i < numPlayers; i++){
                Player current = players.remove();
                current.updateRoom(trailer);
                current.updateRole(null);
            }
        }


    }

    //draws new sceneCards from the arrayList and RANDOMLY assigns them to sets
    private static void drawSceneCards(){

        //go through every set
        for(int i = 0; i < sets.size(); i++){
            //bool for valid draw loop
            boolean validDraw = false;

            //keeps drawing a card as long as the card has already been drawn
            while(!validDraw){

                //draw random card
                int random = ThreadLocalRandom.current().nextInt(0,40);
                SceneCard newCard = sceneCards.get(random);

                //assign the card to the given set if it hasn't been played yet in the current game
                if(!newCard.checkIfPlayed()){
                    sets.get(i).addScene(newCard);
                    newCard.setToPlayed();
                    validDraw = true;
                }
            }
        }
    }

    public static void startGame() throws Exception{

        //**********GAME INITIALIZATION**********//
        //read in cards and rooms

        sceneCards = Reader.readCards();

        Reader.readRooms();

        rooms = Reader.getRooms();
        sets = Reader.getSets();

        drawSceneCards();






        //Scanner console = new Scanner(System.in);

        //View.displayStartMSG();

        //bool for info getting loop

        /*
        boolean playInfoNotDone = true;
        boolean numProvided = false;
        if(numPlayers > 1){
            numProvided = true;
        }
        int numNames = 1;
        //while the program has not gotten all of the info from the player
        while(playInfoNotDone){
            //ask the player to type in a player name
            System.out.println("Please enter the name of player " + numNames  + " (Type 'done' when you have finished entering player names, ONLY IF YOU HAVE NOT ALREADY INPUT NUMBER OF PLAYERS)");
            String givenName = console.nextLine();
            numNames++;
            if(numProvided){
                Player player = new Player(givenName);
                players.add(player);
                if(numNames == numPlayers){
                    System.out.println("All player names entered\n");
                    playInfoNotDone = false;
                }
            //if they type 'done', check and make sure there are at least two players
            }else if((givenName.equals("done")) && (!numProvided)){
                if(numNames < 3){
                    System.out.println("Error: Not enough players");
                }else{
                    playInfoNotDone = false;
                }
            //if the user did not input 'done', create a player object with that name and add it to the PlayerQueue
            }else if(!numProvided){
                Player player = new Player(givenName);
                players.add(player);
                numPlayers++;
                //chcek to see if the player that was just added is the 8th player, if so, set bool to end loop
                if(numPlayers == 9){
                    System.out.println("Maximum number of players reached.");
                    System.out.println("Starting Game...\n");
                    playInfoNotDone = false;
                }
            }
        }
        //decrement to get actual number of players in game
        numPlayers--;
        //if there are 2-3 players, set daysRemaining to 3
        if(numPlayers <= 3){
            daysRemaining = 3;
        }
        //get a reference to the trailer room
        Room trailer = getRoom("trailer");
        //set every players location to the trailer and start them out with the
        // appropriate money/credits for the number of players in the game
        for(int i = 0; i < numPlayers; i++){
            Player current = players.remove();
            if(numPlayers == 5){
                current.updateCredits(2);
                current.updateRoom(trailer);
            }else if(numPlayers == 6){
                current.updateCredits(4);
                current.updateRoom(trailer);
            }else if((numPlayers == 8) || (numPlayers == 7)){
                current.updateRank(2);
                current.updateRoom(trailer);
            }else{
                current.updateRoom(trailer);
            }
        }
        */
    }

    //called when there are no more days remaining in the game
    //calculates the winner of the game and every players final scores
    //Determines if there was a tie
    public static void endGame() {

        //initialize variables for each players final score, winner score, and the names of the winner(s)
        int winnerScore = 0;
        String[] winnerNames = new String[8];
        int finalScore = 0;

        int index = 0;

        //print end game message
        System.out.println("That's a wrap!\n");
        System.out.println("Calculating final scores...\n");

        //go through every player in the PlayerQueue
        for(int i = 0; i < numPlayers; i++){

            //grab a player from the queue, calculate, and output their final score
            Player current = players.remove();
            finalScore = current.getCash() + current.getCredits() + (5 * current.getRank());
            System.out.println("Name: " + current.getName() + " Final Score: " + finalScore);

            //if the current player has a score higher than the current highest score (winnerScore)
            if(finalScore > winnerScore){

                //override the current winningscore with the current finalscore of the player
                winnerScore = finalScore;

                //if there were previous high scores, delete them
                if(winnerNames[0] != null){
                    for(int j =0; j < winnerNames.length; j++){
                        winnerNames[j] = null;
                    }
                    index = 0;
                }
                winnerNames[index] = current.getName();
                index++;

            //if the current players final score matches that of the winningscore, add them to the array of winning names
            }else if(finalScore == (winnerScore)){
                winnerNames[index] = current.getName();
                index++;
            }
        }

        //if after all players scores have been calculated and added to the winner names accordingly and there are multiple names print 'tie'
        if(winnerNames[1] != null){
            System.out.println("It was a tie!\n");
            System.out.println("Winners:\n");

            //print the names of all the players with scores matching the high score
            for(int k = 0; k < winnerNames.length; k++){
                System.out.println(winnerNames[k] + "\n");
            }
        }else{

            //otherwise just prin the name of the one winner
            System.out.println(winnerNames[0] + " is the winner!\n");
        }

        //ask the player if they would like to play again
        Scanner console = new Scanner(System.in);
        System.out.println("Game over!\n");

        boolean inValid = false;

        while(!inValid){
            System.out.print("Would you like to play again? ('Yes' or 'No')");

            //if they would not like to play again they type 'No' and the wantToPlay boolean is set accordingly
            if(console.nextLine().equals("No")){
                wantToPlay = false;
                inValid = true;
            }else if(console.nextLine().equals("Yes")){
                wantToPlay = true;
                inValid = true;
            }else{
                System.out.println("Invalid input\n");
            }
        }

    }

    //decrements the number of scenes remaining by 1
    public static void decrementScene() {
        scenesRemaining--;
    }




   public static void createPlayers(int playFlag){

        for(int i = 1; i <= numPlayers; i++){
            Player player = new Player("Player " + i);
            Room trailer = getRoom("trailer");


            System.out.println(player.getName());

            player.updateRoom(trailer);
            players.add(player);

        }


        for(int i = 0; i < numPlayers; i++){
            Player player = players.remove();
            player.setColor(colors[i]);

            if(playFlag == 1){
                player.updateCredits(2);
            }else if(playFlag == 2){
                player.updateCredits(4);
            }else if(playFlag == 3){
                player.updateRank(2);
            }

        }
    }


    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void setDaysRemaining(int daysRemaining) {
        this.daysRemaining = daysRemaining;
    }

    public ArrayList<Set> getSets(){
        return this.sets;
    }

    public static Player getCurPlayer() {
        return curPlayer;
    }

    public static void setCommand(String nCommand) {
        command = nCommand;
    }

    public static void setChoice(String nChoice) {
        choice = nChoice;
        choiceMade = true;
    }

    public static void setChoiceMade(boolean c) {
        choiceMade = c;
    }

    public static String getChoice() {
        return choice;
    }

    public static String getCommand() {
        return command;
    }

    public static PlayerQueue getPlayers(){
        return players;
    }




}
