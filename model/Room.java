package model;

public class Room {

    private String name;
    private Player[] players = new Player[8];
    //private Room[] adjacent;
    private int roomIndex = 0;
    private String[] neighbors;
    private int[] area;

    public Room(String name, String[] neighbors, int[] area) {
        this.name = name;
        this.neighbors = neighbors;
        this.area = area;
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
