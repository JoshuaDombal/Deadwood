package model;

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

    public boolean checkPlayer(String name){
        for(int i = 0; i < 7; i++){
            if(players[i].equals(name)){
                return true;
            }else{i++;}
        }
        return false;
    }
}
