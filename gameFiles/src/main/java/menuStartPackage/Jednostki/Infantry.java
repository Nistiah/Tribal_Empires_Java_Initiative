package menuStartPackage.Jednostki;

public class Infantry extends ArmyUnit{
    public static final int baseWarriorHp = 20;
    public Infantry() {
        life=baseWarriorHp;
        name="Infantry";
        int closeAttack = 3;
        setCloseAttack(closeAttack);
        int closeDefence = 3;
        setCloseDefence(closeDefence);
        int farAttack = 1;
        setFarAttack(farAttack);
        int farDefence = 1;
        setFarDefence(farDefence);
    }
}
