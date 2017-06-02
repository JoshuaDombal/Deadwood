package model;

import view.BoardLayersListener;

import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

public class SceneCard {

    private String name;
    private int numRoles;
    private int budget;
    private boolean playersOnCard = false;
    private Role[] roles;
    private int sceneNumber;
    private boolean played = false;
    private boolean facedown = true;
    private boolean sceneDone = false;
    private String cardImage;


    public SceneCard(String name, int numRoles, int budget, Role[] roles, int sceneNumber, String cardImage) {
      this.name = name;
      this.numRoles = numRoles;
      this.budget = budget;
      this.playersOnCard = false;
      this.roles = roles;
      this.sceneNumber = sceneNumber;
      this.cardImage = cardImage;
    }


    public boolean checkForPlayers() {
        return this.playersOnCard;
    }

    public void bonusPayout() {
        if (playersOnCard == false) {
            return;
        } else {
            int[] dice = new int[budget];
            int[] bonus = new int[numRoles];

            // Roles the number of dice of budget
            for (int i = 0; i < budget; i++) {
                int r = ThreadLocalRandom.current().nextInt(1,7);
                dice[i] = r;
            }
            Arrays.sort(dice);

            // Adds the value of role of each dice to the bonus
            for (int i = 0; i < budget; i++) {
                bonus[i%numRoles] = dice[i];
            }

            // Gives players on scene card the appropriate bonus

            for (int i = 0; i < numRoles; i++) {
                if (roles[i].checkForPlayer()) {
                    roles[i].getPlayer().updateCash(bonus[i]);


                    //System.out.println(roles[i].getPlayer().getName() + " was awarded " + bonus[i] + " dollars as a bonus!");
                    if(i == 0){
                        BoardLayersListener.setMessage5(roles[i].getPlayer().getName() + " was awarded " + bonus[i] + " dollars as a bonus!");
                    }else if(i == 1){
                        BoardLayersListener.setMessage6(roles[i].getPlayer().getName() + " was awarded " + bonus[i] + " dollars as a bonus!");
                    }else if(i == 2){
                        BoardLayersListener.setMessage7(roles[i].getPlayer().getName() + " was awarded " + bonus[i] + " dollars as a bonus!");
                    }else if(i == 3){
                        BoardLayersListener.setMessage8(roles[i].getPlayer().getName() + " was awarded " + bonus[i] + " dollars as a bonus!");
                    }

                    // Removes role from Player class
                    roles[i].getPlayer().removeRole();


                }
            }

        }

    }


    // Updaters

    public void occupiedStatus(boolean status) {
        this.playersOnCard = status;
    }

    public void addRole(Role role) {
      roles[numRoles-1] = role;
    }


    // Getters
    public String getName() {
      return name;
    }

    public int getNumRoles() {
      return numRoles;
    }
    public int getBudget() {
      return budget;
    }

    public boolean occupiedStatus() {
      return playersOnCard;
    }

    public Role[] getRoles() {
      return roles;
    }

    public int getSceneNumber(){
        return sceneNumber;
    }

    public void setToPlayed(){
        played = true;
    }

    public boolean checkIfPlayed(){
        return played;
    }

    public boolean checkFacedown(){
        return this.facedown;
    }

    public void flipCard(){
        this.facedown = false;
    }

    public boolean checkIfDone(){
        return sceneDone;
    }

    public String getImage(){
        return this.cardImage;
    }

    public void setDone(){
        this.sceneDone = true;
    }

}
