

public class SceneCard {

    private String name;
    private int numRoles;
    private int budget;
    private boolean playersOnCard;
    private Role[] roles;


    public SceneCard(String name, int numRoles, int budget, Role[] roles) {
      this.name = name;
      this.numRoles = numRoles;
      this.budget = budget;
      this.playersOnCard = false;
      this.roles = roles;
    }


    public boolean checkForPlayers() {
      if (numRoles > 0) {
        return true;
      }
      return false;
    }

    public void bonusPayout() {
        if (playersOnCard == false) {
            return
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
    public String getCardName() {
      return name;
    }

    public int getNumRoles() {
      return name;
    }
    public int getBudget() {
      return budget;
    }

    public boolean occupiedStatus() {
      return playersOnCard;
    }

    public Roles[] getBudget() {
      return roles;
    }








}
