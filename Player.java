import java.util.*;
import java.io.*;

import java.util.concurrent.ThreadLocalRandom;

public class Player {

    private String name;

    private int cash;
    private int credits;
    private int rank;

    private Room room;
    /*
    Set set;
    CastingOffice castingOffice;
    */
    private Role role = null;

    // Constructor
    public Player (String name){
        this.name = name;
    }

    public boolean rehearse(SceneCard card) {

        boolean success = false;

        int budget = card.getBudget();
        int rehearseCounter = role.getRehearseBonus();

        if((rehearseCounter - 2) <= budget){
            role.incrementRehearse();
            success = true;
            System.out.println("Successfully rehearsed your part!\n");
        }else if((rehearseCounter - 1) <= budget){
            System.out.println("You've already rehearsed enough to master the role! (Guaranteed successful act)\n");
        }

        if(!success){
            System.out.println("You cannot rehearse!\n");
        }

        return success;
    }

    public boolean upgrade(){

        boolean success = false;
        Scanner console = new Scanner(System.in);
        String choice;

        if(rank != 6){
            System.out.print("Which rank would you like to upgrade to?  ");
            choice = console.nextLine();
            int rankChoice = Integer.parseInt(choice);

            if((rankChoice == 2) && (rank == 1) && ((cash >= 4) || (credits >= 5))){
                rank = rankChoice;
            }else if((rankChoice == 3) && (rank < 3) && ((cash >= 10) || (credits >= 10))){
                rank =2;
            }
        }

        return success;
    }

    public void act(SceneCard card, Set set){
        int diceNum;
        int rehearseB;

        // Roll dice
        diceNum = ThreadLocalRandom.current().nextInt(1,7);

        // Get current rehearse bonus
        rehearseB = role.getRehearseBonus();

        // If roll is successful
        if ((rehearseB + diceNum) >= card.getBudget()) {
            System.out.println("You successfully acted!");

            set.removeShotToken();


            // If player is in an on card role
            // Else: off card
            if (role.checkOnCard()) {
                System.out.println("You earned 2 credits for acting!");
                credits += 2;
            } else if (!role.checkOnCard()) {
                System.out.println("You earned 1 credit and 1 dollar for acting!");
                credits += 1;
                cash += 1;
            }

            // Check if

        // Unsuccessful role
        } else {
            System.out.println("Sorry, you didn't role high enough.");

            // Pay off card player one dollar for losing
            if (!role.checkOnCard()) {
                System.out.println("You earned 1 dollar for being an extra.");
                cash += 1;
            }
        }


    }

    public void updateRoom(Room newRoom) {
        this.room = newRoom;
    }

    public void updateCash(int cashAmount) {
        this.cash += cashAmount;

    }

    public void updateCredits(int credAmount) {
        this.credits += credAmount;

    }

    public void updateRank(int newRank) {
        this.rank = newRank;

    }

    public void updateRole(Role newRole) {
        this.role = newRole;

    }

    private static int rollDice(){
        return 0;
    }

    public int getCredits(){
        return this.credits;
    }

    public int getCash(){
        return this.cash;
    }

    public String getName(){
        return this.name;
    }

    public Room getRoom(){
        return this.room;
    }

    public int getRank(){
        return this.rank;
    }

    public Role getRole(){
        return this.role;
    }

    public void removeRole() {
        role = null;
    }

}
