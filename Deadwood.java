import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Deadwood {
    static int daysRemaining;
    static int scenesRemaining;
    static int numPlayers;

    static Queue<Player> players;
    static ArrayList<SceneCard> sceneCards;
    static ArrayList<Room> rooms;
/*
    public Deadwood(int daysRemaining, int scenesRemaining, int numPlayers) {
      this.daysRemaining = daysRemaining;
      this.scenesRemaining = scenesRemaining;
      this.numPlayers = numPlayers;
    }*/

    private static void playGame() {

    }

    private static void startDay() {

    }

    private static void startGame() {
      readCards();

    }

    private static void endGame() {

    }

    private static void endDay() {

    }

    public static void decrementScene() {

    }

    private static void displayTurnInfo() {

    }



    public static void main(String[] args) {
      //int dRemaining = 4;
      //int sRemaining = 12;
      //int nPlayers = 3;
      //Deadwood game = new Deadwood(4, 12, 3);



        startGame();
        System.out.println()

        while (daysRemaining != 0) {
                startDay();

            while (scenesRemaining > 1) {
                playGame();
            }
            endDay();
        }
        endGame();


    }


    public static void readCards() {
      File file = new File("cards");

      try {
        Scanner scanner = new Scanner(file);
        sceneCards = new ArrayList<SceneCard>(2);

        int cardNum = 0;
        while (scanner.hasNextLine()) {
          SceneCard card;
          //name
          String n = scanner.nextLine();
          // roles
          int r = scanner.nextInt();
          scanner.nextLine();
          //budget
          int b = scanner.nextInt();
          scanner.nextLine();
          Role[] rol = new Role[r];

          for (int i = 0; i < r; i++) {
            String rName = scanner.nextLine();
            int rRank = scanner.nextInt();
            scanner.nextLine();
            Role rRole = new Role(rName, rRank);
            rol[i] = rRole;
          }
          card = new SceneCard(n, r, b, rol);
          sceneCards.add(cardNum, card);

          cardNum++;


        }
        scanner.close();
      }
      catch (FileNotFoundException e) {
        System.out.println("File not found");
      }

    }
}
