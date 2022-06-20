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
    protected int lvl = 0;
    String name ="";

    protected int lvl1UpgradeCloseDef = 1;

    protected int lvl2UpgradeCloseDef = 2;

    protected int lvl2UpgradeFarDef = 1;

    protected int lvl3UpgradeCloseAttack = 2;

    protected int lvl3UpgradeFarAttack = 1;

    protected int lvl1BronzeCost = 5;

    protected int lvl2IronCost = 10;

    protected int lvl3DyesCost = 5;

    protected int lvl3FaithCost = 5;

    public int getLvl1BronzeCost(){
        return lvl1BronzeCost;
    }

    public int getLvl2IronCost(){
        return lvl2IronCost;
    }

    public int getLvl3DyesCost(){
        return lvl3DyesCost;
    }

    public int getLvl3FaithCost(){
        return lvl3FaithCost;
    }

    public int getLvl1UpgradeCloseDef(){
        return lvl1UpgradeCloseDef;
    }

    public int getLvl2UpgradeCloseDef(){
        return lvl2UpgradeCloseDef;
    }

    public int getLvl2UpgradeFarDef(){
        return lvl2UpgradeFarDef;
    }

    public int getLvl3UpgradeCloseAttack(){
        return lvl3UpgradeCloseAttack;
    }

    public int getLvl3UpgradeFarAttack(){
        return lvl3UpgradeFarAttack;
    }

    protected int amount = 0;

    protected int archerCost = 40;

    protected int chariotsGoldCost = 50;
    protected int chariotsHorsesCost = 2;

    protected int infantryCost = 30;

    public int getArcherCost() {
        return archerCost;
    }

    public int getChariotsGoldCost() {
        return chariotsGoldCost;
    }

    public int getChariotsHorsesCost() {
        return chariotsHorsesCost;
    }

    public int getInfantryCost() {
        return infantryCost;
    }


    public void setUpgrades()
    {
        switch (lvl)
        {
            case 1:
                setCloseDefence(closeDefence + lvl1UpgradeCloseDef);
                break;
            case 2:
                setCloseDefence(closeDefence + lvl2UpgradeCloseDef);
                setFarDefence(farDefence + lvl2UpgradeFarDef);
                break;
            case 3:
                setCloseAttack(closeAttack + lvl3UpgradeCloseAttack);
                setFarAttack(farAttack + lvl3UpgradeFarAttack);
                break;
        }

    }

    private List<String> possibleUpgrades = Arrays.asList("Bronze Armor", "Iron Armor", "Banner");

    public String getName() {
        return name;
    }

    public String getNameWithLvl() {
        return name + " LVL: "+getLvl();
    }

    public void increaseLvl(int lvl)
    {
        this.lvl += lvl;
    }

    public void setLvl(int lvl)
    {
        this.lvl = lvl;
    }

    public int getLvl()
    {
        return this.lvl;
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
