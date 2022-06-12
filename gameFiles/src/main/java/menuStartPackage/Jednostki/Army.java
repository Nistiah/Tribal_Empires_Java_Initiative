package menuStartPackage.Jednostki;

import java.util.Vector;

public class Army {
    boolean isDeafult = false;
    boolean isOnSiege = false;
    private String name = "Default";

    Vector<ArmyUnit> units = new Vector<ArmyUnit>();

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
    }

}
