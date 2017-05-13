public class PlayerQueue {

        private Player[] players = new Player[8];
        private int index = 0;
        private int turn = 0;
        private int numPlayers;
        private int realNumPlayers = 0;

        public PlayerQueue(int numPlayers){
            this.numPlayers = numPlayers;
        }

        public void add(Player player)
        {
            players[index] = player;
            index++;

        }

        public Player remove(){

            Player player = players[turn];

            if((turn - 1) == numPlayers){
                turn = 0;
            }else{
                turn++;
            }

            return player;
        }
}
