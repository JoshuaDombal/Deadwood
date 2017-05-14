import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;
import java.lang.Math.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class Deadwood {
    //bools for the loops in main and play game
    private static int daysRemaining = 4;
    private static int scenesRemaining = 10;
    //initialized to 1 for print statements in start game, exectly represents number of players
    private static int numPlayers = 1;
    //bool is true when players start game and when they day they would like to play again
    private static boolean wantToPlay = true;

    //holds a first in first out queue of players in game
    private static PlayerQueue players = new PlayerQueue(8);
    //arrayLists of cards, rooms, and sets
    private static ArrayList<SceneCard> sceneCards;
    private static ArrayList<Room> rooms;
    private static ArrayList<Set> sets;

    //Outlines the basic loop of gameplay
    //Called once everyday
    /*
        while the day is not over...
            -pop a player from the PlayerQueue
            -present them with appropriate options and information for their turn
            -take input of there choice, check if it is valid, call corresponding method for choice
    */
    private static void playGame() {

        while(scenesRemaining > 1){

            //pop player from queue
            Player current = players.remove();

            //establish input
            Scanner console = new Scanner(System.in);

            //booleans for chceking the state of the players location and turn
            boolean moving = false;
            boolean inRole, inCO, mov, inTrl;
            inRole = inCO = mov = inTrl = false;

            //display info about location and current role if applicable
            displayTurnInfo(current);

            //dispaly possible actions for the current player based on their location
            //set booleans accordingly

            System.out.println("Available actions: \n");
            if(!(current.getRole() == (null))){
                inRole = true;
                System.out.println("    -Act\n");
                System.out.println("    -Rehearse\n");
            }else if(current.getRoom().getName().equals("Casting Office")){
                inCO = true;
                System.out.println("    -Move\n");
                System.out.println("    -Upgrade\n");
            }else if(current.getRoom().getName().equals("Trailer")){
                inTrl = true;
                System.out.println("    -Move\n");
            }else{
                System.out.println("    -Move\n");
            }

            //initiale variables for choice loop
            String choice;
            boolean choiceNotValid = true;

            while(choiceNotValid){

                //ask the current player for input
                System.out.println(current.getName() + "'s turn: What would you like to do?\n");

                //get and save player input
                choice = console.nextLine();

                //call act if player types 'Act'
                if((choice.equals("Act")) &&(inRole)){
                    Set set = getSet(current.getRoom().getName());
                    SceneCard card = set.getScene();
                    current.act(card, set);

                    if (set.getNumTokens() == 0) {
                        set.endScene(current);
                    }
                    choiceNotValid = false;

                //call rehearse if player types 'Rehearse'
                }else if((choice.equals("Rehearse")) && (inRole)){
                    Set set = getSet(current.getRoom().getName());
                    SceneCard card = set.getScene();

                    //if the player rehearses successfully, pop out of the loop
                    if(current.rehearse(card)){
                        choiceNotValid = false;
                    }

                //if player types 'Move'...
                }else if((choice.equals("Move")) && (!inRole) && (!mov)){

                    //set bool indicating that player is moving and choice is valid
                    mov = true;
                    choiceNotValid = false;

                    //bool for location choice loop
                    boolean validLocation = false;

                    //grab the nieghbors of the players current room
                    String[] adjacent = current.getRoom().getNeighbors();

                    while(!validLocation){

                        //get the players input for where they would like to move
                        System.out.print("Where would you like to move? ");
                        choice = console.nextLine();

                        //check adjacent to see if the room they chose is a nieghboring room
                        for(int i = 0; i < adjacent.length; i++){

                            if(adjacent[i] == null){
                                break;
                            }

                            //if their choice was valid...
                            if(adjacent[i].equals(choice)){

                                //set bool for location valid
                                validLocation = true;

                                System.out.println("Successful move!\n");

                                System.out.println("-----------------------------------------------------------------------\n");

                                //set booleans to indicate if player is NOW in casting office or trialer
                                if(choice.equals("Casting Office")){
                                    inCO = true;
                                }else if(choice.equals("trailer")){
                                    inTrl = true;
                                }else{
                                    System.out.println("New Location Info:\n");
                                }

                                //update player room and rooms player
                                current.updateRoom(getRoom(choice));


                                //current.getRoom().addPlayer(current);

                                System.out.print("\n");

                                break;
                            }
                        }

                        //if the location is not valid, print error message and go through loop again
                        if(!validLocation){
                            System.out.println("Woops! You can't move to that room. Try again...\n");
                        }

                    }

                //if choice equals upgrade, call upgrade
                }else if((choice.equals("Upgrade")) && (inCO)){

                    if(current.upgrade()){
                        choiceNotValid = false;
                    }

                }

                //if the player is moving and they're not in the casting office or the trailer...
                if((mov) && ((!current.getRoom().getName().equals("Casting Office")) && (!current.getRoom().getName().equals("trailer")))){


                    //get the set and SceneCard of the player's new location
                    Set set = getSet(choice);
                    SceneCard card = set.getScene();

                    //there is both a scene and a set
                    if((set != null) && (card != null)){

                        //get and save the roles from the set
                        Role[] setRoles = set.getRoles();

                        //Display name of the set and the scene cards within
                        System.out.println(current.getRoom().getName() + ":\n");
                        displaySceneCard(card);

                        //flip the scene card if it needs to be done
                        set.flipSceneCard();

                        //display roles on the set and all pertinant info
                        System.out.println("Roles on set:\n");
                        for(int i = 0; i < setRoles.length; i++){

                            if(setRoles[i] == null){
                                break;
                            }

                            System.out.println("    " + setRoles[i].getName());
                            System.out.println("    \"" + setRoles[i].getLine() + "\"\n");
                            System.out.println("    -Rank: " + setRoles[i].getRank());
                            System.out.println("    -Budget: " + set.getScene().getBudget());
                            System.out.println("    -Rehearsal Bonus: " + setRoles[i].getRehearseBonus() + "\n");
                        }

                        //create bool for if they typed yes or no for wanting a role or not
                        boolean roleChoiceValid = false;
                        //create bool for is player wants role
                        boolean wantRole = false;

                        while(!roleChoiceValid){
                            //get the palyers input for if they want to take a role
                            System.out.print("Would you like to take a role? ('Yes' or 'No') ");
                            choice = console.nextLine();

                            //set wantRole to true if the player wants to take a role
                            if(choice.equals("Yes")){
                                wantRole = true;
                                roleChoiceValid = true;
                            }else if(!choice.equals("No")){
                                System.out.println("Please input either 'Yes' or 'No'\n");
                            }else{
                                roleChoiceValid = true;
                            }
                        }
                        if(wantRole){

                            //if the player wants to take a role set bool for taking role loop
                            boolean validRole = false;
                            while(!validRole){

                                //get and save player input for which role they would like to take
                                System.out.println("What role would you like to take? (Input the name of the role you want exactly as you see it)");
                                System.out.print("(or type 'done' if you do not want to take a role)  ");
                                choice = console.nextLine();

                                if(choice.equals("done")){
                                    validRole = true;
                                }else{

                                    //look through the set roles to see if their choice was valid
                                    for(int i = 0; i < setRoles.length; i++){

                                        if(setRoles[i] == null){
                                            break;
                                        }

                                        //if the role is in the set and it is not occupied, take role
                                        if(setRoles[i].getName().equals(choice)){
                                            if((!setRoles[i].checkForPlayer()) && (setRoles[i].getRank() <= current.getRank())){
                                                System.out.println("Role taken successfully\n");
                                                current.updateRole(setRoles[i]);
                                                setRoles[i].addPlayer(current);
                                                validRole = true;

                                            //if the role is in the set but is occupied, print error and ask for input again via loop
                                            }else{
                                                System.out.println("Role already filled! Please choose again\n");
                                            }
                                        }
                                    }

                                    //grab and save roles from the SceneCard
                                    Role[] cardRoles = set.getScene().getRoles();

                                    //go through the roles on the scene card to check if player choice is valid
                                    for(int i = 0; i < cardRoles.length; i++){

                                        if(cardRoles[i] == null){
                                            break;
                                        }

                                        //if the role is in the set and it is not occupied, take role
                                        if(cardRoles[i].getName().equals(choice)){
                                            if(!cardRoles[i].checkForPlayer()){
                                                System.out.println("Role taken successfully\n");
                                                current.updateRole(cardRoles[i]);
                                                cardRoles[i].addPlayer(current);
                                                validRole = true;

                                            //if the role is in the set but is occupied, print error and ask for input again via loop
                                            }else{
                                                System.out.println("Role already filled! Please choose again\n");
                                            }
                                        }
                                    }
                                }

                                if(!validRole){
                                    System.out.println("Woops! Invalid role! Please try again.\n");
                                }

                            }

                        }
                    }
                }

                //if the choice is not valid print an error and ask them to try again
                if(choiceNotValid){
                    System.out.println("Woops! Please type a valid choice for your turn\n");
                }

            }

            //player turn ended, put player back in PlayerQueue
            System.out.println(current.getName() + "'s turn has ended...\n");
            System.out.println("\n------------------------------------------------------------------------\n");

        }

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
    private static Set getSet(String setName){
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

    //called once at the end of a day
    //decrements the number of days left and reset the number of scenesRemaining
    //move all player back to the trailer
    private static void startDay() {
        daysRemaining--;
        scenesRemaining = 10;

        Room trailer = getRoom("Trailer");

        for(int i = 0; i < numPlayers; i++){
            Player current = players.remove();
            current.updateRoom(trailer);
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

    private static void startGame() {

        //**********GAME INITIALIZATION**********//
        //read in cards and rooms
        sceneCards = Reader.readCards();

        Reader.readRooms();

        rooms = Reader.getRooms();
        sets = Reader.getSets();





        drawSceneCards();

        Scanner console = new Scanner(System.in);
        //print greeting message
        System.out.println("DEADWOOD:\n");
        System.out.println("Welcome to Deadwood Studios, home of the million-movie month. You’re a bit actor with a simple dream.");
        System.out.println("The dream of getting paid. You and your cohorts will spend the next four days dressing up as cowboys,");
        System.out.println("working on terrible films, and pretending you can act.");
        System.out.println("So strap on your chaps and mosey up to the roof. Your line is “Aaaiiigggghh!\"");
        System.out.println("DEADWOOD is a fast-paced board game about actors, acting, and the thrill-filled life of a wandering ");
        System.out.println("bit player. It’s best with 2 to 6 players, but still decent with 7 or 8. Play time is about 60 minutes.\n");

        //ask user for info about the set up
        System.out.println("Please enter player information (must be at least 2 players and at most 8):\n");
        System.out.println("   -If there are 2 or 3 players, play only 3 days");
        System.out.println("   -If there are 4 players, play 4 days");
        System.out.println("   -If there are 5 players,35 start each player with 2 credits");
        System.out.println("   -If there are 6 players, start each player with 4 credits");
        System.out.println("   -If there are 7 or 8 players, start each player with the rank of 2\n");

        //bool for info getting loop
        boolean playInfoNotDone = true;

        //while the program has not gotten all of the info from the player
        while(playInfoNotDone){

            //ask the player to type in a player name
            System.out.println("Please enter the name of player " + numPlayers  + " (Type 'done' when you have finished entering player names)");
            String givenName = console.nextLine();

            //if they type 'done', check and make sure there are at least two players
            if(givenName.equals("done")){
                if(numPlayers < 3){
                    System.out.println("Error: Not enough players");
                }else{
                    playInfoNotDone = false;
                }

            //if the user did not input 'done', create a player object with that name and add it to the PlayerQueue
            }else{
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

        //set every players location to the trialer and start them out with the
        // appropriate money/credits for the number of players in the game
        for(int i = 0; i < numPlayers; i++){
            Player current = players.remove();

            if(numPlayers == 5){
                current.updateCredits(2);
                current.updateRoom(trailer);
            }else if(numPlayers == 6){
                current.updateCredits(4);
                current.updateRoom(trailer);
            }else if(numPlayers <= 8){
                current.updateRank(2);
                current.updateRoom(trailer);
            }else{
                current.updateRoom(trailer);
            }
        }
    }

    //called when there are no more days remaining in the game
    //calculates the winner of the game and every players final scores
    //Determines if there was a tie
    private static void endGame() {

        //initialize varibales for each players final score, winner score, and the names of the winner(s)
        int winnerScore = 0;
        String[] winnerNames = null;
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
        System.out.println("Game over!");
        System.out.print("Would you like to play again? ('Yes' or 'No')");

        //if they would not like to play again they type 'No' and the wantToPlay boolean is set accordingly
        if(console.nextLine().equals("No")){
            wantToPlay = false;
        }
    }

    //decrements the number of scenes remaining by 1
    public static void decrementScene() {
        scenesRemaining--;
    }

    //print the info for each players turn
    private static void displayTurnInfo(Player currentPlayer) {

        //get the room the player is currently in
        Room currentRoom = currentPlayer.getRoom();

        //print the player's info
        System.out.println(currentPlayer.getName() + "'s turn:\n");
        System.out.println("    -Money: " + currentPlayer.getCash());
        System.out.println("    -Credits: " + currentPlayer.getCredits());
        System.out.println("    -rank: " + currentPlayer.getRank() + "\n");
        System.out.println("-----------------------------------------------------------------------------------------\n");
        System.out.println("Room: " + currentRoom.getName() + "\n");

        //if the player is in the casting office display the upgrade menu and the adjacent rooms
        if(currentRoom.getName().equals("Casting Office")){

            CastingOffice.displayUpgradeOptions();

            displayAdjacentRooms(currentRoom);

        //if the player is in the trailer display the adjacent rooms
        }else if(currentRoom.getName().equals("trailer")){

            displayAdjacentRooms(currentRoom);

        //if the player is in a role display the info about the role they're in
        }else if(currentPlayer.getRole() != null){
            Role role = currentPlayer.getRole();
            Set set = getSet(currentRoom.getName());
            System.out.println("Current Role:\n");
            System.out.println("    " + role.getName());
            System.out.println("    \"" + role.getLine() + "\"\n");
            System.out.println("    -Rank: " + role.getRank());
            System.out.println("    -Budget: " + set.getScene().getBudget());
            System.out.println("    -Rehearsal Bonus: " + role.getRehearseBonus() + "\n");

        //otherwise the player must be in a set. In which case display the scene card and adjacent rooms
        }else{

            Set set = getSet(currentRoom.getName());

            displaySceneCard(set.getScene());

            displayAdjacentRooms(currentRoom);
        }

        System.out.println("");
    }

    //takes a room object as an argument and displays it's adjacent rooms
    private static void displayAdjacentRooms(Room room){

        String[] adjacent = room.getNeighbors();
        System.out.print("Adjacent Rooms:\n\n");
        for(int i = 0; i < adjacent.length; i++){

            if(adjacent[i] == null){
                break;
            }

            System.out.print("  -" + adjacent[i] + "\n");
        }
    }

    //takes a sceneCard object and displays it's info like roles and if they're open
    private static void displaySceneCard(SceneCard sceneCard){

        Role[] roles = sceneCard.getRoles();
        System.out.println("Name of scene: " + sceneCard.getName() + "\n");
        System.out.println("Budget: " + sceneCard.getBudget() + "\n");
        System.out.println("On Card Roles:\n");
        for(int i = 0; i < roles.length; i++){
            String status = "Open";

            if(roles[i] == null){
                break;
            }

            if(roles[i].checkForPlayer()){
                status = "Taken";
            }
            System.out.println("Name: " + roles[i].getName() + "\n  -Rank: " + roles[i].getRank() + "\n  -Status: " + status + "\n");
        }
    }

    public static void main(String[] args) {

        while(wantToPlay){
            startGame();
            while (daysRemaining != 0) {
                playGame();
                startDay();
            }
            endGame();
        }
    }
}
