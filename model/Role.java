package model;

public class Role {

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
