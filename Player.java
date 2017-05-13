import java.util.*;
import java.io.*;

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
            choice = console.next();
            int rankChoice = Integer.parseInt(choice);

            if((rankChoice == 2) && (rank == 1) && ((cash >= 4) || (credits >= 5))){
                rank = rankChoice;
            }else if((rankChoice == 3) && (rank < 3) && ((cash >= 10) || (credits >= 10))){
                rank =2;
            }
        }

        return success;
    }

    public void act(){

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

}
