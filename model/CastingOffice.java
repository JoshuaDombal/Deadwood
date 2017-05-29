package model;

public class CastingOffice extends Room{
    Upgrade[] upgrades;

    public CastingOffice(String name, String[] neighbors, int[] area, Upgrade[] upgrades){
        super(name, neighbors, area);
        this.upgrades = upgrades;

    }
}
