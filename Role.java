package model;

import java.util.Collection;
import java.util.LinkedList;

public class Role {

    private String name;
    private Player player;

    private int roleRank;
    private int rehearseCounter;
    private String line;

    private boolean onCard;
    private boolean occupied;

    private Collection<Listener> listeners;

    public Role (String name, int roleRank, String line, boolean onCard) {
      listeners = new LinkedList<Listener>();
      this.name = name;
      this.roleRank = roleRank;
      this.player = null;
      this.rehearseCounter = 0;
      this.onCard = onCard;
      this.occupied = false;
      this.line = line;
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
}
