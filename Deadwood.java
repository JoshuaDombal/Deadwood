
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;
import java.lang.Math.*;


public class Deadwood {
    private static int daysRemaining = 4;
    private static int scenesRemaining = 12;
    private static int numPlayers = 1;
    private static boolean wantToPlay = true;

    static PlayerQueue players = new PlayerQueue(numPlayers);
    static ArrayList<SceneCard> sceneCards;
    static ArrayList<Room> rooms;
    static ArrayList<Set> sets;
    /*
    public Deadwood(int daysRemaining, int scenesRemaining, int numPlayers) {
    this.daysRemaining = daysRemaining;
    this.scenesRemaining = scenesRemaining;
    this.numPlayers = numPlayers;
    */




    private static void playGame() {

        while(scenesRemaining > 1){
            Player current = players.remove();
            boolean moving = false;
            Scanner console = new Scanner(System.in);

            //booleans for chceking if choice matches Room
            boolean inRole, inCO, mov;
            inRole = inCO = mov = false;
            System.out.println(current.getName() + "'s turn: What would you like to do?\n");

            if(!(current.getRole().equals(null))){
                inRole = true;
                System.out.println("    -Act\n");
                System.out.println("    -Rehearse\n");
            }else if(current.getRoom().getName().equals("Casting Office")){
                inCO = true;
                System.out.println("    -Move\n");
                System.out.println("    -Upgrade\n");
            }else{
                System.out.println("    -Move\n");
            }

            String choice;
            boolean choiceNotValid = true;

            while(choiceNotValid){
                choice = console.next();

                if((choice.equals("Act")) &&(inRole)){
                    current.act();
                }else if((choice.equals("Rehearse")) && (inRole)){
                    current.rehearse();
                }else if((choice.equals("Move")) && (!inRole) && (!mov)){
                    mov = true;
                    boolean validLocation = false;
                    Room[] adjacent = current.getRoom().getAdjacent();
                    while(!validLocation){
                        System.out.print("Where would you like to move? ");
                        choice = console.next();
                        for(int i = 0; i < adjacent.length; i++){
                            if(adjacent[i].getName().equals(choice)){
                                validLocation = true;
                                current.updateRoom(getRoom(choice));
                                current.getRoom().addPlayer(current);
                            }
                        }

                        if(!validLocation){
                            System.out.println("Woops! You can't move to that room. Try again...\n");
                        }
                    }
                }else if((choice.equals("Upgrade")) && (inCO)){
                    current.upgrade();
                }
            }
        }

    }

    private static Room getRoom(String roomName){
        Room room = null;

        for(int i = 0; i < rooms.size(); i++){
            if(rooms.get(i).getName().equals(roomName)){
                room = rooms.get(i);
                return room;
            }
        }
        return room;
    }

    private static void startDay() {
        daysRemaining--;
        scenesRemaining = 12;

        Room trailer = getRoom("Trailer");

        for(int i = 0; i < numPlayers; i++){
            Player current = players.remove();
            current.updateRoom(trailer);
        }

    }

    private static void startGame() {

        Scanner console = new Scanner(System.in);
        //print greeting message
        System.out.println("DEADWOOD:\n");
        System.out.println("Welcome to Deadwood Studios, home of the million-movie month. You’re a bit actor with a simple dream.");
        System.out.println("The dream of getting paid. You and your cohorts will spend the next four days dressing up as cowboys,");
        System.out.println("working on terrible films, and pretending you can act.");
        System.out.println("So strap on your chaps and mosey up to the roof. Your line is “Aaaiiigggghh!");
        System.out.println("DEADWOOD is a fast-paced board game about actors, acting, and the thrill-filled life of a wandering ");
        System.out.println("bit player. It’s best with 2 to 6 players, but still decent with 7 or 8. Play time is about 60 minutes.\n");

        //ask user for info about the set up
        System.out.println("Please enter player information (must be at least 2 players and at most 8):\n");
        System.out.println("   -If there are 2 or 3 players, play only 3 days");
        System.out.println("   -If there are 4 players, play 4 days");
        System.out.println("   -If there are 5 players,35 	Zhang, Jianying start each player with 2 credits");
        System.out.println("   -If there are 6 players, start each player with 4 credits");
        System.out.println("   -If there are 7 or 8 players, start each player with the rank of 2\n");

        boolean playInfoNotDone = true;

        while(playInfoNotDone){
            System.out.println("Please enter the name of player " + numPlayers  + " (Type 'done' when you have finished entering player names)");
            String givenName = console.next();
            if(givenName.equals("done")){
                if(numPlayers < 2){
                    System.out.println("Error: Not enough players");
                }else{
                    playInfoNotDone = false;
                }
            }else{
                Player player = new Player(givenName, 0, 0, 1);
                players.add(player);
                numPlayers++;
                if(numPlayers == 9){
                    System.out.println("Maximum number of players reached.");
                }
            }
        }

        numPlayers--;

        if(numPlayers <= 3){
            daysRemaining = 3;
        }

        Room trailer = getRoom("Trailer");

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

            players.add(current);
        }

        //read from files to set up board
        readCards();

    }

    private static void endGame() {
        int winnerScore = 0;
        String[] winnerNames = null;
        int finalScore = 0;
        int index = 0;

        System.out.println("That's a wrap!\n");
        System.out.println("Calculating final scores...\n");

        for(int i = 0; i < numPlayers; i++){
            Player current = players.remove();
            finalScore = current.getCash() + current.getCredits() + (5 * current.getRank());
            System.out.println("Name: " + current.getName() + " Final Score: " + finalScore);
            if(finalScore > winnerScore){
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
            }else if(finalScore == (winnerScore)){
                winnerNames[index] = current.getName();
                index++;
            }
        }

        if(winnerNames[1] != null){
            System.out.println("It was a tie!\n");
            System.out.println("Winners:\n");
            for(int k = 0; k < winnerNames.length; k++){
                System.out.println(winnerNames[k] + "\n");
            }
        }else{
            System.out.println(winnerNames[0] + " is the winner!\n");
        }

        Scanner console = new Scanner(System.in);
        System.out.println("Game over!");
        System.out.print("Would you like to play again? ('Yes' or 'No')");

        if(console.next().equals("No")){
            wantToPlay = false;
        }
    }

    public static void decrementScene() {
        scenesRemaining--;
    }

    private static void displayTurnInfo(Player currentPlayer) {
        Room currentRoom = currentPlayer.getRoom();

        System.out.println(currentPlayer.getName() + "'s turn:\n");
        System.out.println("    -Money: " + currentPlayer.getCash());
        System.out.println("    -Credits: " + currentPlayer.getCredits() + "\n");
        System.out.println(currentRoom.getName() + "\n");

        if(currentRoom.getName().equals("Casting Office")){

            System.out.println("----Rank----Dollars----Credits----");
            System.out.println("----------------------------------");
            System.out.println("|    2        4           5      |");
            System.out.println("----------------------------------");
            System.out.println("|    3        10          10     |");
            System.out.println("----------------------------------");
            System.out.println("|    4        18          15     |");
            System.out.println("----------------------------------");
            System.out.println("|    5        28          20     |");
            System.out.println("----------------------------------");
            System.out.println("|    6        40          25     |");
            System.out.println("----------------------------------");

            displayAdjacentRooms(currentRoom);

        }else if(currentRoom.getName().equals("Trailer")){

            displayAdjacentRooms(currentRoom);

        }else{

            Set set = null;

            for(int i = 0; i < sets.size(); i++){
                if(sets.get(i).getName().equals(currentRoom.getName())){
                    set = sets.get(i);
                }
            }

            displaySceneCard(set.getScene());

            displayAdjacentRooms(currentRoom);
        }

        System.out.println("");
    }

    private static void displayAdjacentRooms(Room room){

        Room[] adjacent = room.getAdjacent();
        System.out.print("Adjacent Rooms: ");
        for(int i = 0; i < adjacent.length; i++){
            System.out.print(adjacent[i].getName() + " ");
        }
    }

    private static void displaySceneCard(SceneCard sceneCard){

        Role[] roles = sceneCard.getRoles();
        System.out.println(sceneCard.getName() + "\n");
        System.out.println("Roles:\n");
        for(int i = 0; i < roles.length; i++){
            String status = "Open";
            if(roles[i].checkForPlayer()){
                status = "Taken";
            }
            System.out.println("Name: " + roles[i].getName() + " Rank: " + roles[i].getRank() + " Status: " + status + "\n");
        }
    }

    // Reads rooms from a file
    public static void readRooms() {
      File file = new File("rooms");

      try {
        Scanner scanner = new Scanner(file);
        sceneCards = new ArrayList<SceneCard>(2);

        int roomNum = 0;
        while (scanner.hasNextLine()) {
          Room room;
          //name
          String n = scanner.nextLine();

          if (n.equals("Trailer")) {
            Trailer t = new Trailer(n);
            rooms.add(roomNum, t);
        } else if (n.equals("Casting Office")) {
            CastingOffice c = new CastingOffice(n);
            rooms.add(roomNum, c);
          } else {
          // roles
          int r = scanner.nextInt();
          scanner.nextLine();

          // numShotCounter
          int b = scanner.nextInt();
          scanner.nextLine();

          Role[] rol = new Role[r];

          for (int i = 0; i < r; i++) {
            String rName = scanner.nextLine();
            int rRank = scanner.nextInt();
            scanner.nextLine();
            Role rRole = new Role(rName, rRank);
            rol[i] = rRole;
          }
          Set set = new Set(n, r, b, rol);
          rooms.add(roomNum, set);

          }


          roomNum++;


        }
        scanner.close();
      }
      catch (FileNotFoundException e) {
        System.out.println("File not found");
      }

    }

    // Reads cards from a file
    public static void readCards() {
        File file = new File("cards");

        try {
            Scanner scanner = new Scanner(file);
            sceneCards = new ArrayList<SceneCard>(2);

            int cardNum = 0;
            while (scanner.hasNextLine()) {
                SceneCard card;
                //name
                String n = scanner.nextLine();
                // roles
                int r = scanner.nextInt();
                scanner.nextLine();
                //budget
                int b = scanner.nextInt();
                scanner.nextLine();
                Role[] rol = new Role[r];

                for (int i = 0; i < r; i++) {
                    String rName = scanner.nextLine();
                    int rRank = scanner.nextInt();
                    scanner.nextLine();
                    Role rRole = new Role(rName, rRank);
                    rol[i] = rRole;
                }
                card = new SceneCard(n, r, b, rol);
                sceneCards.add(cardNum, card);

                cardNum++;


            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }

    public static void main(String[] args) {

        //players = new PlayerQueue(numPlayers);

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
