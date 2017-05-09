


public class Room {

    private String name;
    private Player[] players;
    private Room[] adjacent;
    private int roomIndex = 0;

    public Room(String name) {
        this.name = name;
    }

    public Room[] getAdjacent(){
        return this.adjacent;
    }

    public String getName(){
        return this.name;
    }

    public void addPlayer(Player player){
        players[roomIndex] = player;
        roomIndex++;
    }
}
