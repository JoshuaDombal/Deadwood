


public class Set extends Room {
    
    SceneCard scene;
    int shotTokens;
    boolean sceneFaceUp = false;

    Role[] roles;

    public Set(String name, int shotTokens, int numRoles, Role[] roles){
        super(name);
        this.scene = scene;
        this.roles = roles;
    }

    public static void addScene() {

    }

    public static void addRoles() {

    }

    private static void flipSceneCard() {

    }

    private static void endScene() {

    }

    public static void clearRoles() {

    }

    private static void removeShotToken() {

    }

    public static void payActors() {

    }

    public SceneCard getScene(){
        return this.scene;
    }
}
