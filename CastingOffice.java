

public class CastingOffice extends Room{

    public CastingOffice(String name, String[] neighbors){
        super(name, neighbors);
    }

    public static void displayUpgradeOptions() {
        System.out.println("\n\n---------Upgrade Menu-------------\n");
        System.out.println("----Rank----Dollars----Credits----");
        System.out.println("----------------------------------");
        System.out.println("|    2        4           5      |");
        System.out.println("----------------------------------");
        System.out.println("|    3        10          10     |");
        System.out.println("----------------------------------");
        System.out.println("|    4        18          15     |");
        System.out.println("----------------------------------");
        System.out.println("|    5        28          20     |");
        System.out.println("----------------------------------");
        System.out.println("|    6        40          25     |");
        System.out.println("----------------------------------\n");
    }

}
