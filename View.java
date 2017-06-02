/**
 * Created by carpend3 on 5/15/17.
 */

 /*
public class View {

    //takes a sceneCard object and displays it's info like roles and if they're open
    public static void displaySceneCard(SceneCard sceneCard){

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
            System.out.println("Name: " + roles[i].getName() + "\n\n  -Line: \"" + roles[i].getLine() + "\"" + "\n  -Rank: " + roles[i].getRank() + "\n  -Status: " + status + "\n");
        }
    }

    //takes a room object as an argument and displays it's adjacent rooms
    public static void displayAdjacentRooms(Room room){

        String[] adjacent = room.getNeighbors();
        System.out.print("Adjacent Rooms:\n\n");
        for(int i = 0; i < adjacent.length; i++){

            if(adjacent[i] == null){
                break;
            }

            System.out.print("  -" + adjacent[i] + "\n");
        }
    }

    public static void who(Player currentPlayer) {

        //get the room the player is currently in
        Room currentRoom = currentPlayer.getRoom();

        //print the player's info
        System.out.println(currentPlayer.getName() + "'s turn:\n");
        System.out.println("    -Money: " + currentPlayer.getCash());
        System.out.println("    -Credits: " + currentPlayer.getCredits());
        System.out.println("    -rank: " + currentPlayer.getRank() + "\n");

        if (currentPlayer.getRole() != null) {
            Role role = currentPlayer.getRole();
            Set set = Deadwood.getSet(currentRoom.getName());
            System.out.println("Current Role:\n");
            System.out.println("    " + role.getName());
            System.out.println("    \"" + role.getLine() + "\"\n");
            System.out.println("    -Rank: " + role.getRank());
            System.out.println("    -Budget: " + set.getScene().getBudget());

            System.out.println("    -Rehearsal Bonus: " + role.getRehearseBonus() + "\n");
            System.out.println("    -Number of Shots Remaining: " + set.getNumTokens() + "\n");

            if (role.checkOnCard()) {
                System.out.println("    -Role Status: On card");

            } else {
                System.out.println("    -Role Status: Off card");
            }
        }
    }

    public static void where(Player currentPlayer){

        Room currentRoom = currentPlayer.getRoom();

        //if the player is in the casting office display the upgrade menu and the adjacent rooms
        if(currentRoom.getName().equals("office")){

            System.out.println("Casting Office:\n");

            displayAdjacentRooms(currentRoom);

            CastingOffice.displayUpgradeOptions();

            //if the player is in the trailer display the adjacent rooms
        }else if(currentRoom.getName().equals("trailer")){
            System.out.println("Trailer:\n");
            displayAdjacentRooms(currentRoom);

        }else{
            Set set = Deadwood.getSet(currentRoom.getName());

            System.out.println(currentRoom.getName() + ":\n");

            if(set.getScene() != null){
                displaySceneCard(set.getScene());
            }

            Role[] setRoles = set.getRoles();

            for(int i = 0; i < setRoles.length; i++){

                if(setRoles[i] == null){
                    break;
                }

                System.out.println("Roles in Set:\n");

                System.out.println("Name: " + setRoles[i].getName());
                System.out.println("\n  -Line: \"" + setRoles[i].getLine() + "\"");
                System.out.println("  -Rank: " + setRoles[i].getRank());
                System.out.println("  -Budget: " + set.getScene().getBudget());
                System.out.println("  -Rehearsal Bonus: " + setRoles[i].getRehearseBonus());

                String status = "Open";
                if(setRoles[i].checkForPlayer()){
                    status = "Taken";
                }

                System.out.println("  -Status: " + status + "\n");
            }

            displayAdjacentRooms(currentRoom);
        }

        System.out.println("");
    }

    /*
    public static void displayStartMSG(){
        //print greeting message
        System.out.println("DEADWOOD:\n");
        System.out.println("Welcome to Deadwood Studios, home of the million-movie month. You’re a bit actor with a simple dream.");
        System.out.println("The dream of getting paid. You and your cohorts will spend the next four days dressing up as cowboys,");
        System.out.println("working on terrible films, and pretending you can act.");
        System.out.println("So strap on your chaps and mosey up to the roof. Your line is “Aaaiiigggghh!\"");
        System.out.println("DEADWOOD is a fast-paced board game about actors, acting, and the thrill-filled life of a wandering ");
        System.out.println("bit player. It’s best with 2 to 6 players, but still decent with 7 or 8. Play time is about 60 minutes.\n");
        //info about the set up
        System.out.println("Please enter player information (must be at least 2 players and at most 8):\n");
        System.out.println("   -If there are 2 or 3 players, play only 3 days");
        System.out.println("   -If there are 4 players, play 4 days");
        System.out.println("   -If there are 5 players,35 start each player with 2 credits");
        System.out.println("   -If there are 6 players, start each player with 4 credits");
        System.out.println("   -If there are 7 or 8 players, start each player with the rank of 2\n");
    }
    */
//}
//
