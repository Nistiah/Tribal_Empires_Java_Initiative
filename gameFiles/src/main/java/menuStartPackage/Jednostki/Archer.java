package menuStartPackage.Jednostki;

public class Archer extends ArmyUnit{
    public static final int baseArcherHp = 15;
    public Archer() {
        life=baseArcherHp;
        name="Archer";
        int closeAttack = 1;
        setCloseAttack(closeAttack);
        int closeDefence = 1;
        setCloseDefence(closeDefence);
        int farAttack = 5;
        setFarAttack(farAttack);
        int farDefence = 5;
        setFarDefence(farDefence);
    }

}
