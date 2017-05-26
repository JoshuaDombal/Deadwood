package model;

import java.util.Collection;
import java.util.LinkedList;

public class Role {

    public interface Listener{
        void changed(Role r);
    }

    private Collection<Listener> listeners;

    private String name;
    private Player player;

    private int roleRank;
    private int rehearseCounter;
    //private int sceneBonus;
    private String line;

    private boolean onCard;
    private boolean occupied;
    private int[] area;


    public Role (String name, int roleRank, String line, boolean onCard, int[] area) {
      listeners = new LinkedList<Listener>();
      this.name = name;
      this.roleRank = roleRank;
      this.player = null;
      this.rehearseCounter = 0;
      //this.sceneBonus = 0;

      this.occupied = false;
      this.line = line;
      this.onCard = onCard;
      this.area = area;
    }

    public void subscribe(Listener l){
        listeners.add(l);
    }

    protected void changed(){
        for(Listener l : listeners){
            l.changed(this);
        }
    }

    public void incrementRehearse() {
        rehearseCounter++;
    }

    public void addPlayer(Player player) {
        this.player = player;
        occupied = true;
    }

    public boolean checkForPlayer() {
        return occupied;
    }

    //*****************temporary!!!********************//
    public void abandon(){
        this.occupied = false;
    }

    public void take(){
        this.occupied = true;
    }

    // This function should maybe not be in here
    public void resetRole() {

    }

    /*
    public void setSceneBonus(int newBonus) {
      this.sceneBonus = newBonus;
    }
    */
    public String getName(){
        return this.name;
    }

    public int getRank(){
        return this.roleRank;
    }

    public String getLine(){
        return this.line;
    }

    public int getRehearseBonus(){
        return this.rehearseCounter;
    }

    public boolean checkOnCard() {
        return onCard;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getX(){
        return this.area[0];
    }

    public int getY(){
        return this.area[1];
    }

    public int getH(){
        return this.area[2];
    }

    public int getW(){
        return this.area[3];
    }
}
