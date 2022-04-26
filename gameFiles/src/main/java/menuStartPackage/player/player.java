package menuStartPackage.player;

import java.util.Vector;

public class player {
    //tu dodac trzeba bedzie frakcje tez, ale gosix musi zrobic klase matke bo kurde polimorfizm konieczny

    private int gold=1;
    private int faith=1;
    private int bronze=1;
    private int iron=1;
    private int dyes=1;
    private int buildingResources=1;
    private int horses=1;

    private class cityCoordinates{
        public int q;
        public int r;
    }

    Vector<cityCoordinates> cityList = new Vector<>();

    public void createNewCity(int qq, int rr){
        cityCoordinates newCity = new cityCoordinates();
        newCity.q=qq;
        newCity.r=rr;
        cityList.add(newCity);
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getFaith() {
        return faith;
    }

    public void setFaith(int faith) {
        this.faith = faith;
    }

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }

    public int getIron() {
        return iron;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public int getDyes() {
        return dyes;
    }

    public void setDyes(int dyes) {
        this.dyes = dyes;
    }

    public int getBuildingResources() {
        return buildingResources;
    }

    public void setBuildingResources(int buildingResources) {
        this.buildingResources = buildingResources;
    }

    public int getHorses() {
        return horses;
    }

    public void setHorses(int horses) {
        this.horses = horses;
    }






}
