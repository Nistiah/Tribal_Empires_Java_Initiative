package menuStartPackage.player;

import java.util.Vector;

import menuStartPackage.Frakcje.Assyria;
import menuStartPackage.Frakcje.Egypt;
import menuStartPackage.Frakcje.Fraction;
import menuStartPackage.Frakcje.Hittites;

import menuStartPackage.Prowincje.City;

public class Player {

    public static final int baseGoldProduction              = 1;
    public static final int baseBeliefProduction            = 0;
    public static final int baseBuildingResourcesProduction = 1;
    public static final int baseHorsesProduction            = 0;
    public static final int baseBronzeProduction            = 0;
    public static final int baseIronProduction              = 0;
    public static final int baseDyesProduction              = 0;

    private int             numberOfProvinces               = 1;
    private int             gold                            = 20;
    private int             belief                          = 0;
    private int             bronze                          = 0;
    private int             iron                            = 0;
    private int             dyes                            = 0;
    private int             buildingResources               = 1;
    private int             horses                          = 0;
    public int cityCounter                                  =0;
    public int id;

    // do dodania get production from cities list, gosix musi zrobic getery na to w cities, a w prowincjach na cities
    private Vector<City> cityList = new Vector<>();

    // tu dodac trzeba bedzie frakcje tez, ale gosix musi zrobic klase matke bo kurde polimorfizm konieczny
    private String   name;
    private Fraction fraction;

    public Player(String name) {
        this.setName(name);

        switch (name) {    // TODO: dodac reszte
        case "Egypt" :
            setFraction(new Egypt());

            break;

        case "Hittites" :
            setFraction(new Hittites());

            break;

        case "Assyria" :
            setFraction(new Assyria());

            break;
        }
    }

    public void createNewCity(City city) {
        getCityList().add(city);
        cityCounter=cityList.size();
    }

//  private int goldProduction=baseGoldProduction+getProvincesGold();
//  private int beliefProduction=baseBeliefProduction+getProvincesBelief();
//  private int buildingResourcesProduction=baseBuildingResourcesProduction;  //do rozwiazania to jeszcze
//  private int horsesProduction=baseHorsesProduction+getProvincesHorses();
//  private int bronzeProduction=baseBronzeProduction+getProvincesBronze();
//  private int ironProduction=baseIronProduction+getProvincesIron();
//  private int dyesProduction=baseDyesProduction+getProvincesDices();

    public void resourcesTourIncrease() {
        gold              += baseGoldProduction + getProvincesGold();
        belief            += baseBeliefProduction + getProvincesBelief();
        buildingResources += baseBuildingResourcesProduction + getProvincesWood();    // do rozwiazania
        horses            += baseHorsesProduction + getProvincesHorses();
        bronze            += baseBronzeProduction + getProvincesBronze();
        iron              += baseIronProduction + getProvincesIron();
        dyes              += baseDyesProduction + getProvincesDices();
    }

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }

    public int getBuildingResources() {
        return buildingResources;
    }

    public void setBuildingResources(int buildingResources) {
        this.buildingResources = buildingResources;
    }

    public Vector<City> getCityList() {
        return cityList;
    }

    public void setCityList(Vector<City> cityList) {
        this.cityList = cityList;
    }

    public int getDyes() {
        return dyes;
    }

    public void setDyes(int dyes) {
        this.dyes = dyes;
    }

    public int getFaith() {
        return belief;
    }

    public void setFaith(int faith) {
        this.belief = faith;
    }

    public Fraction getFraction() {
        return fraction;
    }

    public void setFraction(Fraction fraction) {
        this.fraction = fraction;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getHorses() {
        return horses;
    }

    public void setHorses(int horses) {
        this.horses = horses;
    }

    public int getIron() {
        return iron;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfProvinces() {
        return cityCounter;
    }

    public void setNumberOfProvinces(int numberOfProvinces) {
        this.numberOfProvinces = numberOfProvinces;
    }

    private int getProvincesBelief() {
        if (getCityList() == null) {
            return 0;
        }

        int retBelief = 0;

        for (City city : getCityList()) {
            retBelief += city.getBelief();
        }

        return retBelief;
    }

    private int getProvincesBronze() {
        if (getCityList() == null) {
            return 0;
        }

        int retBronze = 0;

        for (City city : getCityList()) {
            retBronze += city.getBronze();
        }

        return retBronze;
    }

    private int getProvincesDices() {
        if (getCityList() == null) {
            return 0;
        }

        int retDices = 0;

        for (City city : getCityList()) {
            retDices += city.getDyes();
        }

        return retDices;
    }

    private int getProvincesFood() {
        if (getCityList() == null) {
            return 0;
        }

        int retFood = 0;

        for (City city : getCityList()) {
            retFood += city.getFood();
        }

        return retFood;
    }

    private int getProvincesGold() {
        if (getCityList() == null) {
            return 0;
        }

        int retGold = 0;

        for (City city : getCityList()) {
            retGold += city.getGold();
        }

        return retGold;
    }

    private int getProvincesHorses() {
        if (getCityList() == null) {
            return 0;
        }

        int retHorses = 0;

        for (City city : getCityList()) {
            retHorses += city.getHorses();
        }

        return retHorses;
    }

    private int getProvincesIron() {
        if (getCityList() == null) {
            return 0;
        }

        int retIron = 0;

        for (City city : getCityList()) {
            retIron += city.getIron();
        }

        return retIron;
    }

    private int getProvincesWood() {
        if (getCityList() == null) {
            return 0;
        }

        int retWood = 0;

        for (City city : getCityList()) {
            retWood += city.getWood();
        }

        return retWood;
    }
}
