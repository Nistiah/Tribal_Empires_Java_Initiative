package menuStartPackage.Jednostki;

public class ArmyUnit {
    protected int life = 0;
    protected int attack = 0;
    protected int defence = 0; //pancerz
    protected int farAttack = 0;
    protected int farDefence= 0;
    protected int closeAttack = 0;
    protected int closeDefence = 0;


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
}
