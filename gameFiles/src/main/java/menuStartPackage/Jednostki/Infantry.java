package menuStartPackage.Jednostki;

public class Infantry extends ArmyUnit{

    public Infantry() {
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
