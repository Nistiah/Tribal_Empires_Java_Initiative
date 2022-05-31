package menuStartPackage.Budynki;

import java.util.Objects;

public class Building {
    private int      level                      = 0;
    private double[] levelProductionModificator = { 1, 2, 5 };
    private double[] levelCostModificator       = { 1, 1.5, 3 };
    private int      gold                       = 0;
    private int      belief                     = 0;
    private int      food                       = 0;
    private int      bronze                     = 0;
    private int      iron                       = 0;
    private int      dices                      = 0;
    private int      horses                     = 0;
    private int      wood                       = 0;
    private int      populationLimit            = 0;
    private int      owner;
    private int      maxLevel;

    public boolean upgradeExist() {
        return level < 3;
    }

    public void setBaseProduction(String type) {
        switch (type) {
        case "AmberCollector" :
            gold = 2;

            break;

        case "Barracks" :

            ////TODO mechanika lepszych jednostek
            break;

        case "BronzeMine" :
            bronze = 1;

            break;

        case "Caravan" :
            gold = 1;

            break;

        case "CatchingBoar" :

            ////TODO mechanika zdobycia zwierzat
            break;

        case "CowBreeding" :
            food = 2;

            break;

        case "Deforestation" :

            ////TODO mechanika na wyciecie lasu
            break;

        case "Farm" :
            food = 1;

            break;

        case "Fishermen" :
            food = 1;

            break;

        case "GoldMine" :
            gold = 3;

            break;

        case "HorseBreeding" :
            horses = 1;

            break;

        case "Hunter" :
            food = 1;

            break;

        case "IronMine" :
            iron = 1;

            break;

        case "IrrigationSystem" :

            ////TODO irygacja
            break;

        case "Lighthouse" :

            ////TODO lighthouse
            break;

        case "Market" :
            gold = 3;

            break;

        case "PigBreeding" :
            food = 2;

            break;

        case "Pyramid" :
            belief = 1;

            break;

        case "ResidentialDistrict" :
            populationLimit = 5;

            break;

        case "RiversideFarm" :
            food = 2;

            break;

        case "Sawmill" :
            wood = 2;

            break;

        case "ScarletFishermen" :
            dices = 1;

            break;

        case "SeaFoodCollector" :
            food = 1;

            break;

        case "Temple" :
            belief = 2;

            break;

        case "Warehouse" :

            ////TODO mechanika na zwiekszanie limitu surowcow
            break;
        }
    }

    public double getBelief() {
        return belief * levelProductionModificator[level];
    }

    public double getBronze() {
        return bronze * levelProductionModificator[level];
    }

    public double getDices() {
        return dices * levelProductionModificator[level];
    }

    public double getFood() {
        return food * levelProductionModificator[level];
    }

    public double getGold() {
        return gold * levelProductionModificator[level];
    }

    public double getHorses() {
        return horses * levelProductionModificator[level];
    }

    public double getIron() {
        return iron * levelProductionModificator[level];
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMaxLevel(String type) {
        if (Objects.equals(type, "CowBreeding")
                || Objects.equals(type, "PigBreeding")
                || Objects.equals(type, "HorseBreeding")
                || Objects.equals(type, "Hunter")) {
            this.maxLevel = 2;
        } else {
            this.maxLevel = 3;
        }
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public double getWood() {
        return wood * levelProductionModificator[level];
    }
}
