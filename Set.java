


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

    private static void endScene() {

    }

    public static void clearRoles() {

    }

    private void removeShotToken() {
        shotTokens--;
    }

    public static void payActors() {

    }

    public SceneCard getScene(){
        return this.scene;
    }

    public Role[] getRoles(){
        return this.roles;
    }

}
