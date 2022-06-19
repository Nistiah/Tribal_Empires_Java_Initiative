package menuStartPackage.Jednostki;

import java.util.Vector;

public class Army {
    boolean isDeafult = false;
    boolean isOnSiege = false;
    private String name = "Default";
    private int archersAmount = 0;
    private int chariotsAmount = 0;
    private int warriorsAmount = 0;
    private int totalAmount = 0;

    Vector<ArmyUnit> units = new Vector<ArmyUnit>();

    public int getArchersAmount()
    {
        return this.archersAmount;
    }

    public int getChariotsAmount()
    {
        return this.chariotsAmount;
    }

    public int getWarriorsAmount()
    {
        return this.warriorsAmount;
    }

    public void clearArmy(){
        archersAmount=0;
        chariotsAmount=0;
        warriorsAmount=0;
        totalAmount=0;
        units=null;
    }

    public void updateAmount()
    {
        this.totalAmount = 0;
        this.archersAmount = 0;
        this.chariotsAmount = 0;
        this.warriorsAmount = 0;
        for(ArmyUnit unit: units)
        {
            switch (unit.getName())
            {
                case "Archer":
                    archersAmount += 1;
                    break;
                case "Chariots":
                    chariotsAmount += 1;
                    break;
                case "Infantry":
                    warriorsAmount += 1;
                    break;
                default:
                    break;
            }
        }
        setTotalAmount();
    }

    public void setTotalAmount()
    {
        totalAmount = archersAmount + chariotsAmount + warriorsAmount;
    }

    public int getTotalAmount()
    {
        return this.totalAmount;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setIsDeafult()
    {
        this.isDeafult = true;
    }

    public void isOnSiege()
    {
        this.isOnSiege = true;
    }

    public boolean getIsOnSiege()
    {
        return this.isOnSiege;
    }

    public Vector<ArmyUnit> getUnits()
    {
        return this.units;
    }

    public void addUnit(ArmyUnit u1)
    {
        this.units.add(u1);
        updateAmount();
    }

}
