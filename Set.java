public class Set extends Room {

    private SceneCard scene;
    private int shots;
    private int shotTokens;
    private boolean sceneFaceUp = false;

    private Role[] roles;

    public Set(String name, int shotTokens, int numRoles, Role[] roles, String[] neighbors){
        super(name, neighbors);
        this.scene = scene;
        this.roles = roles;
        this.shotTokens = shotTokens;
        this.shots = shotTokens;
    }

    public void addScene(SceneCard scene) {
        this.scene = scene;
    }

    public void flipSceneCard() {
        this.sceneFaceUp = true;
    }

    public void endScene(Player player) {

        // check for players on card
        if (scene.occupiedStatus()) {
            scene.bonusPayout();

            payActors();

        }

        scene = null;

        // Removes role from each player
        int i = 0;
        while (i < roles.length) {
            if (roles[i] != null) {
                if (roles[i].checkForPlayer()) {
                    roles[i].getPlayer().removeRole();
                }
            }

            i++;

        }

        roles = null;

        Deadwood.decrementScene();

    }

    public void clearRoles() {
        roles = null;
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

}
