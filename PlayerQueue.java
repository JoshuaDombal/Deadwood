public class PlayerQueue {

        private Player[] players = new Player[8];
        private int index = 0;
        int numPlayers;

        public PlayerQueue(int numPlayers){
            this.numPlayers = numPlayers;
        }

        public void add(Player player){
            players[index] = player;
        }

        public Player remove(){
            Player player = players[index];
            if(index < (numPlayers - 1)){
                index++;
            }else{
                index = 0;
            }

            return player;
        }
}
