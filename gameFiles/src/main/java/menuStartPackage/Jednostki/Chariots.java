package menuStartPackage.Jednostki;

public class Chariots extends ArmyUnit{
    public static final int baseChariotHp = 25;
    public Chariots() {
        int closeAttack = 3;
        setCloseAttack(closeAttack);
        int closeDefence = 3;
        setCloseDefence(closeDefence);
        int farAttack = 2;
        setFarAttack(farAttack);
        int farDefence = 2;
        setFarDefence(farDefence);
    }
}
