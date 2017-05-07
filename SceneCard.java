

public class SceneCard {

    String name;
    int numRoles;
    int budget;
    boolean playersOnCard;
    Role[] roles;


    public SceneCard(String name, int numRoles, int budget, Role[] roles) {
      this.name = name;
      this.numRoles = numRoles;
      this.budget = budget;
      this.playersOnCard = false;
      this.roles = roles;
    }


    public void addRole(Role role) {
      roles[numRoles-1] = role;
    }

    public boolean checkForPlayers() {
      if (numRoles > 0) {
        return true;
      }
      return false;
    }

    public void bonusPayout() {

    }

    public int getBudget() {
      return budget;
    }

    public String getCardName() {
      return name;
    }





}
