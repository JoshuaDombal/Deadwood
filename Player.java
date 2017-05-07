

public class Player {

    String name;

    int cash;
    int credits;
    int rank;

    Room room;
    Set set;
    CastingOffice castingOffice;

    Role role;

    // Constructor
    public Player (){

    }

    public void rehearse() {

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

}
