public class Role {

    private String name;
    private Player player;

    private int roleRank;
    private int rehearseCounter;
    //private int sceneBonus;

    private boolean onCard;
    private boolean occupied;


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


    // This function should maybe not be in here
    public void resetRole() {

    }

    public void incrementSceneBonus() {
      this.sceneBonus++;
    }

    public String getName(){
        return this.name;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getRank(){
        return this.roleRank;
    }

    public boolean checkOnCard() {
        return onCard;
    }


    public boolean checkForPlayer() {
        return occupied;
    }



}
