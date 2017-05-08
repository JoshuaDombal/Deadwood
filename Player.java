

public class Player {

    private String name;

    private int cash;
    private int credits;
    private int rank;

    private Room room;
    private Set set;
    private CastingOffice castingOffice;

    private Role role;
.




    // Constructor
    public Player (int nCash, int nCredits, int nRank){
        this.cash = nCash;
        this.credits = nCredits;
        this.rank = nRank;
        Room room = new Room("Trailer");
        this.room = room;
    }

    public void rehearse() {

    }


    // Updaters
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

    // Getters

    public String getName() {
        return this.name;
    }

    public int getCash() {
        return this.cash;
    }

    public int getCredits() {
        return this.credits;
    }

    public int getRank() {
        return this.rank;
    }

    public Room getRoom() {
        return this.room;
    }



    private int rollDice(){
        return 0;
    }

}
