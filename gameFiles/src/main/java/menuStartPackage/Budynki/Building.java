package menuStartPackage.Budynki;

import java.util.Objects;

public class Building {
    int owner;
    int level = 0;
    int maxLevel;
    double [] levelProductionModificator= {1,2,5};
    double [] levelCostModificator= {1,1.5,3};

    int gold = 0;
    int belief = 0;
    int food = 0;
    int bronze = 0;
    int iron = 0;
    int dices = 0;
    int horses = 0;
    int wood = 0;
    int populationLimit = 0;

    public boolean upgradeExist(){
        return level < 3;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMaxLevel(String type) {
        if(Objects.equals(type, "CowBreeding") || Objects.equals(type, "PigBreeding") || Objects.equals(type, "HorseBreeding") || Objects.equals(type, "Hunter")){
            this.maxLevel = 2;
        }
        else{
            this.maxLevel = 3;
        }
    }

    public double getGold() {
        return gold*levelProductionModificator[level];
    }

    public double getBelief() {
        return belief*levelProductionModificator[level];
    }

    public double getFood() {
        return food*levelProductionModificator[level];
    }

    public double getBronze() {
        return bronze*levelProductionModificator[level];
    }

    public double getIron() {
        return iron*levelProductionModificator[level];
    }

    public double getDices() {
        return dices*levelProductionModificator[level];
    }

    public double getHorses() {return horses*levelProductionModificator[level];}

    public double getWood() {
        return wood*levelProductionModificator[level];
    }

    public void setBaseProduction(String type){
        switch(type){
            case "AmberCollector":
                gold=2;
                break;
            case "Barracks":
                ////TODO mechanika lepszych jednostek
                break;
            case "BronzeMine":
                bronze=1;
                break;
            case "Caravan":
                gold=1;
                break;
            case "CatchingBoar":
                ////TODO mechanika zdobycia zwierzat
                break;
            case "CowBreeding":
                food =2;
                break;
            case "Deforestation":
                ////TODO mechanika na wyciecie lasu
                break;
            case "Farm":
                food = 1;
                break;
            case "Fishermen":
                food = 1;
                break;
            case "GoldMine":
                gold = 3;
                break;
            case "HorseBreeding":
                horses = 1;
                break;
            case "Hunter":
                food = 1;
                break;
            case "IronMine":
                iron = 1;
                break;
            case "IrrigationSystem":
                ////TODO irygacja
                break;
            case "Lighthouse":
                ////TODO lighthouse
                break;
            case "Market":
                gold = 3;
                break;

            case "PigBreeding":
                food = 2;
                break;

            case "Pyramid":
                belief = 1;
                break;

            case "ResidentialDistrict":
                populationLimit=5;
                break;

            case "RiversideFarm":
                food = 2;
                break;
            case "Sawmill":
                wood = 2;
                break;

            case "ScarletFishermen":
                dices = 1;
                break;
            case "SeaFoodCollector":
                food = 1;
                break;

            case "Temple":
                belief = 2;
                break;
            case "Warehouse":
                ////TODO mechanika na zwiekszanie limitu surowcow
                break;

        }
    }
}
