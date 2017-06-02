package model;

public class Upgrade {

    private int level;
    private String currency;
    private int amount;
    private int[] area;

    public Upgrade(int level, String currency, int amount, int[] area) {
        this.level = level;
        this.currency = currency;
        this.amount = amount;
        this.area = area;
    }
}
