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
        String paymentChoice;

        if(rank != 6){
            System.out.print("Which rank would you like to upgrade to?  ");
            choice = console.nextLine();
            int rankChoice = Integer.parseInt(choice);

            System.out.println("Would you like to pay with cash or credits? ");
            paymentChoice = console.nextLine();
            //int payChoice = Integer.parseInt(paymentChoice);


            if(rankChoice == 2){
                if ((paymentChoice.equals("cash")) && (cash >= 4)) {
                    cash = cash - 4;
                    rank = 2;
                } else if ((paymentChoice.equals("credits")) && (credits >= 5)) {
                    credits = credits - 5;
                } else {
                    return false;
                }


            }else if((rankChoice == 3) && (rank < 3)){
                if ((paymentChoice.equals("cash")) && (cash >= 10)) {
                    cash = cash - 10;
                    rank = 3;
                } else if ((paymentChoice.equals("credits")) && (credits >= 10)) {
                    credits = credits - 10;
                    rank = 3;
                } else {
                    return false;
                }


            } else if((rankChoice == 4) && (rank < 4)){
                if ((paymentChoice.equals("cash")) && (cash >= 18)) {
                    cash = cash - 18;
                    rank = 4;
                } else if ((paymentChoice.equals("credits")) && (credits >= 15)) {
                    credits = credits - 15;
                    rank = 4;
                } else {
                    return false;
                }


            } else if((rankChoice == 5) && (rank < 5)){
                if ((paymentChoice.equals("cash")) && (cash >= 28)) {
                    cash = cash - 28;
                    rank = 5;
                } else if ((paymentChoice.equals("credits")) && (credits >= 20)) {
                    credits = credits - 20;
                    rank = 5;
                } else {
                    return false;
                }


            } else if((rankChoice == 6) && (rank < 6) && ((cash >= 40) || (credits >= 25))){
                if ((paymentChoice.equals("cash")) && (cash >= 40)) {
                    cash = cash - 40;
                    rank = 6;
                } else if ((paymentChoice.equals("credits")) && (credits >= 25)) {
                    credits = credits - 25;
                    rank = 6;
                } else {
                    return false;
                }

            }
        }

        return true;
    }

    public void act(SceneCard card, Set set){
        int diceNum;
        int rehearseB;

        // Roll dice
        diceNum = ThreadLocalRandom.current().nextInt(1,7);
        System.out.println("You rolled a " + diceNum + "!");

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
