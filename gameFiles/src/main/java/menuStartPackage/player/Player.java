package menuStartPackage.player;

import menuStartPackage.Prowincje.City;
import menuStartPackage.Prowincje.Province;

import java.util.Vector;

public class Player {
    //tu dodac trzeba bedzie frakcje tez, ale gosix musi zrobic klase matke bo kurde polimorfizm konieczny
    public String name;

    public Player(String name){
        this.name=name;
    }

    private int gold=1;
    private int belief=0;
    private int bronze=1;
    private int iron=1;
    private int dyes=1;
    private int buildingResources=1;
    private int horses=1;

    private final int baseGoldProduction=1;
    private final int baseBeliefProduction=0;
    private final int baseBuildingResourcesProduction=1;
    private final int baseHorsesProduction=0;
    private final int baseBronzeProduction=0;
    private final int baseIronProduction=0;
    private final int baseDyesProduction=0;

//    private int goldProduction=baseGoldProduction+getProvincesGold();
//    private int beliefProduction=baseBeliefProduction+getProvincesBelief();
//    private int buildingResourcesProduction=baseBuildingResourcesProduction;  //do rozwiazania to jeszcze
//    private int horsesProduction=baseHorsesProduction+getProvincesHorses();
//    private int bronzeProduction=baseBronzeProduction+getProvincesBronze();
//    private int ironProduction=baseIronProduction+getProvincesIron();
//    private int dyesProduction=baseDyesProduction+getProvincesDices();

    public void resourcesTourIncrease(){
        gold+=baseGoldProduction+getProvincesGold();
        belief+=baseBeliefProduction+getProvincesBelief();
        buildingResources+=baseBuildingResourcesProduction;   //do rozwiazania
        horses+=baseBuildingResourcesProduction;
        bronze+=baseBronzeProduction+getProvincesBronze();
        iron+=baseIronProduction+getProvincesIron();
        dyes+=baseDyesProduction+getProvincesDices();
    }


    //do dodania get production from cities list, gosix musi zrobic getery na to w cities, a w prowincjach na cities

    Vector<City> cityList = new Vector<>();

    private int getProvincesGold() {
        if(cityList==null){
            return 0;
        }
        int retGold = 0;
        for (City city:cityList) {
            retGold+=city.getGold();
        }
        return retGold;
    }

    private int getProvincesBelief() {
        if(cityList==null){
            return 0;
        }
        int retBelief = 0;
        for (City city:cityList) {
            retBelief+=city.getBelief();
        }
        return retBelief;
    }

    private int getProvincesFood() {
        if(cityList==null){
            return 0;
        }
        int retFood = 0;
        for (City city:cityList) {
            retFood+=city.getFood();
        }
        return retFood;
    }

    private int getProvincesBronze() {
        if(cityList==null){
            return 0;
        }
        int retBronze = 0;
        for (City city:cityList) {
            retBronze+=city.getBronze();
        }
        return retBronze;
    }

    private int getProvincesIron() {
        if(cityList==null){
            return 0;
        }
        int retIron = 0;
        for (City city:cityList) {
            retIron+=city.getIron();
        }
        return retIron;
    }

    private int getProvincesDices() {
        if(cityList==null){
            return 0;
        }
        int retDices = 0;
        for (City city:cityList) {
            retDices+=city.getDices();
        }
        return retDices;
    }

    private int getProvincesHorses() {
        if(cityList==null){
            return 0;
        }
        int retHorses = 0;
        for (City city:cityList) {
            retHorses+=city.getHorses();
        }
        return retHorses;
    }

    private int getProvincesWood() {
        if(cityList==null){
            return 0;
        }
        int retWood = 0;
        for (City city:cityList) {
            retWood+=city.getWood();
        }
        return retWood;
    }




    public void createNewCity(City city){
        cityList.add(city);
    }


    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getFaith() {
        return belief;
    }

    public void setFaith(int faith) {
        this.belief = faith;
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
