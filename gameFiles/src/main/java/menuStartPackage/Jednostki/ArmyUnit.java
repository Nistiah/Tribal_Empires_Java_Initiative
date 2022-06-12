package menuStartPackage.Jednostki;

import java.util.Arrays;
import java.util.List;

public class ArmyUnit {
    protected int life = 0;
    protected int attack = 0;
    protected int defence = 0; //pancerz
    protected int farAttack = 0;
    protected int farDefence= 0;
    protected int closeAttack = 0;
    protected int closeDefence = 0;
    String name = "";

    protected int amount = 0;

    private List<String> possibleUpgrades = Arrays.asList("Bronze Armor", "Iron Armor", "Banner");

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public int getLife() {
        return life;
    }

    public int getAttack() {
        return this.closeAttack + this.farAttack;
    }

    public int getDefence() {
        return this.closeDefence + this.farDefence;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getFarAttack() {
        return farAttack;
    }

    public void setFarAttack(int farAttack) {
        this.farAttack = farAttack;
    }

    public int getFarDefence() {
        return farDefence;
    }

    public void setFarDefence(int farDefence) {
        this.farDefence = farDefence;
    }

    public int getCloseAttack() {
        return closeAttack;
    }

    public void setCloseAttack(int closeAttack) {
        this.closeAttack = closeAttack;
    }

    public int getCloseDefence() {
        return closeDefence;
    }

    public void setCloseDefence(int closeDefence) {
        this.closeDefence = closeDefence;
    }

    public List<String> getPossibleUpgrades() {
        return possibleUpgrades;
    }
}
