package model;

public class Set extends Room {

    private SceneCard scene;
    private int shots;
    private int shotTokens;
    private boolean sceneFaceUp = false;

    private Role[] roles;
    private int[] area;
    private Take[] takes;

    public Set(String name, int shotTokens, int numRoles, Role[] roles, String[] neighbors, int[] area, Take[] takes){
        super(name, neighbors, area);
        this.scene = scene;
        this.roles = roles;
        this.shotTokens = shotTokens;
        this.shots = shotTokens;
        this.area = area;
        this.takes = takes;
    }

    public void addScene(SceneCard scene) {
        this.scene = scene;
    }

    public void flipSceneCard() {
        this.sceneFaceUp = true;
    }

    public void endScene(Player player) {

        System.out.println("\n*********Scene Ended!*********\n");

        // check for players on card
        if (scene.occupiedStatus()) {
            scene.bonusPayout();

            payActors();

        }

        scene.setDone();

        Role[] cardRoles = scene.getRoles();
        scene = null;

        // Removes role from each player on set
        int i = 0;
        while (i < roles.length) {
            if (roles[i] != null) {
                if (roles[i].checkForPlayer()) {
                    roles[i].getPlayer().removeRole();
                }
            }

            i++;

        }

        Board.decrementScene();

    }


    public void removeShotToken() {
        shotTokens--;
    }

    // Pays extras
    public void payActors() {
        int i = 0;
        while ((roles[i] != null) && (i < roles.length)) {
            if (roles[i].checkForPlayer()) {
                int payout = roles[i].getRank();
                roles[i].getPlayer().updateCash(payout);
                System.out.println(roles[i].getPlayer().getName() + "was awarded " + payout + " dollars in cash as a bonus for being an extra!\n");
            }
            i++;
        }
    }

    public SceneCard getScene(){
        return this.scene;
    }

    public Role[] getRoles(){
        return this.roles;
    }

    public int getNumTokens() {
        return this.shotTokens;
    }

    public int[] getArea(){
        return this.area;
    }

    public Take[] getTakes(){return this.takes;}

}
