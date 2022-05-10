package menuStartPackage.player;

import menuStartPackage.Prowincje.City;

import java.util.Vector;

public class Player {
    //tu dodac trzeba bedzie frakcje tez, ale gosix musi zrobic klase matke bo kurde polimorfizm konieczny
    public String name;

    public Player(String name){
        this.name=name;
    }

    private int gold=1;
    private int faith=0;
    private int bronze=1;
    private int iron=1;
    private int dyes=1;
    private int buildingResources=1;
    private int horses=1;

    private int baseGoldProduction=1;
    private int baseFaithProduction=0;
    private int baseuildingResourcesProduction=1;
    private int baseHorsesProduction=0;
    private int baseBronzeProduction=0;
    private int baseIronProduction=0;
    private int baseDyesProduction=0;

    private int goldProduction;
    private int faithProduction;
    private int buildingResourcesProduction;
    private int horsesProduction;
    private int bronzeProduction;
    private int ironProduction;
    private int dyesProduction;


    //do dodania get production from cities list, gosix musi zrobic getery na to w cities, a w prowincjach na cities

    Vector<City> cityList = new Vector<>();

    public void createNewCity(int qq, int rr){
        City newCity = new City();
        newCity.setCoordinates(qq, rr);
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
