


public class Room {

    private String name;
    private Player[] players = new Player[8];
    //private Room[] adjacent;
    private int roomIndex = 0;
    private String[] neighbors;

    public Room(String name, String[] neighbors) {
        this.name = name;
        this.neighbors = neighbors;
    }

    public String[] getNeighbors(){
        return this.neighbors;
    }

    public String getName(){
        return name;
    }

    public void addPlayer(Player player){
        this.players[roomIndex] = player;
        this.roomIndex++;
    }
}
