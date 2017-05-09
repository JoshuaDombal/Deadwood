

public class Player {

    private String name;

    private int cash;
    private int credits;
    private int rank;

    private Room room;
    /*
    Set set;
    CastingOffice castingOffice;
    */
    private Role role;

    // Constructor
    public Player (String name){
        this.name = name;
    }

    public void rehearse() {
        
    }

    public void upgrade(){

    }

    public void act(){

    }

    public void getLocation() {

    }

    public void changeRoom(Room newroom) {

    }

    public void updateRoom(Room newRoom) {
        this.room = newRoom;
    }

    public void updateCash(int cashAmount) {
        this.cash += cashAmount;

    }

    public void updateCredits(int credAmount) {
        this.credits += credAmount;

    }

    public void updateRank(int newRank) {
        this.rank = newRank;

    }

    public void updateRole(Role newRole) {
        this.role = newRole;

    }

    private static int rollDice(){
        return 0;
    }

    public int getCredits(){
        return this.credits;
    }

    public int getCash(){
        return this.cash;
    }

    public String getName(){
        return this.name;
    }

    public Room getRoom(){
        return this.room;
    }

    public int getRank(){
        return this.rank;
    }

    public Role getRole(){
        return this.role;
    }

}
