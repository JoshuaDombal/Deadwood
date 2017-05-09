import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

public class SceneCard {

    private String name;
    private int numRoles;
    private int budget;
    private boolean playersOnCard = false;
    private Role[] roles;


    public SceneCard(String name, int numRoles, int budget, Role[] roles) {
      this.name = name;
      this.numRoles = numRoles;
      this.budget = budget;
      this.playersOnCard = false;
      this.roles = roles;
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










}
