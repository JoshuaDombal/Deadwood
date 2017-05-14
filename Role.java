public class Role {

    private String name;
    private Player player;

    private int roleRank;
    private int rehearseCounter;
    //private int sceneBonus;
    private String line;

    private boolean onCard;
    private boolean occupied;


    public Role (String name, int roleRank, String line) {
      this.name = name;
      this.roleRank = roleRank;
      this.player = null;
      this.rehearseCounter = 0;
      //this.sceneBonus = 0;
      this.onCard = false;
      this.occupied = false;
      this.line = line;
    }

    public void incrementRehearse() {
        rehearseCounter++;
    }

    public void addPlayer(Player player) {
        this.player = player;
        occupied = true;
    }

    public boolean checkForPlayer() {
        return this.occupied;
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
