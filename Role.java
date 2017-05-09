public class Role {

    String name;
    Player player;

    int roleRank;
    int rehearseCounter;
    int sceneBonus;

    boolean onCard;
    boolean occupied;


    public Role (String name, int roleRank) {
      this.name = name;
      this.roleRank = roleRank;
      this.player = null;
      this.rehearseCounter = 0;
      this.sceneBonus = 0;
      this.onCard = false;
      this.occupied = false;
    }

    public void incrementRehearse() {
        rehearseCounter++;
    }

    public void addPlayer(Player player) {
        this.player = player;
    }

    public boolean checkForPlayer() {
        return occupied;
    }

    // This function should maybe not be in here
    public void resetRole() {

    }

    public void incrementSceneBonus() {
      this.sceneBonus++;
    }

    public String getName(){
        return this.name;
    }

    public int getRank(){
        return this.roleRank;
    }

}
