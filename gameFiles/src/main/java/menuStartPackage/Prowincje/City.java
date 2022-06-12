package menuStartPackage.Prowincje;

import menuStartPackage.Jednostki.*;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class City extends Province {
    public static int    cityNamesAssyriaCounter  = 0;
    public static int    cityNamesEgyptCounter    = 0;
    public static int    cityNamesHittitesCounter = 0;


    public int           cityHp                   = 1000;

    private int          population               = 1;
    public double        popGrowthCostMultiplier  = 1;
    public double        popGrowthCost            = 5;
    public double        currentPopGrowth         = 0;
    private final double popGrowthScaler          = 1.1;

    private List<String> resources                = List.of();
    private String       type                     = "City";
    private List<String> possibleBuildings        = List.of();
    private List<String> baseBuildings            = Arrays.asList("Residential District",
                                                           "Market",
                                                           "Barracks",
                                                           "Temple",
                                                           "Warehouse");



    private List<String> possibleUnits = Arrays.asList("Archers", "Chariots", "Infantry");
    public List<String> getPossibleUnits() {
        return possibleUnits;
    }


    public Vector<Army> army = new Vector<Army>();

    public void initArmy()
    {
        Army defArmy = new Army();
        defArmy.setName("Default1");
        this.army.add(defArmy);
    }

    public void addArmy(Army newArmy)
    {
        newArmy.setName("Army " + this.army.size());
        this.army.add(newArmy);
    }

    @Override
    public int getPop(){
        return population;
    }


    private Vector<Province> provincelist = new Vector<>();
    private final String[]   cityNamesAssyria  = {
        "Ashur", "Nineveh", "Dur Sharrukin", "Babylon", "Susa", "Haran", "Calah"
    };
    private final String[]   cityNamesEgypt    = {
        "Memphis", "Thebes", "Amarna", "Avaris", "Alexandria", "Abydos", "Ptkheka"
    };
    private final String[]   cityNamesHittites = {
        "Hattusa", "Nerik", "Kusara", "Karkemisz", "Kanesh", "Aleppo", "Malatya"
    };
    private String           name              = "";

    public void populationGrowth(){
        currentPopGrowth+=getFood();
        if(currentPopGrowth>=popGrowthCost*popGrowthCostMultiplier){
            currentPopGrowth-=popGrowthCost;
            population++;
            popGrowthCost=popGrowthCost*popGrowthScaler;
        }
    }

    public City(int id) {
        this.name=getCityName(id);
        if(id==1){
            popGrowthCostMultiplier=0.9;
        }
        isCity=true;
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
        initArmy();
    }

    ///TODO:tura wyswietla ie na hettytach a nie na egipcie counter increment

    public void assignProvince(Province province) {
        provincelist.add(province);
    }

    @Override
    public String iconPath() {
        return "provinceIcons/city.png";
    }

    @Override
    public double getBelief() {
        return (belief + getProvincesBelief());
    }

    @Override
    public int getBronze() {
        return bronze + getProvincesBronze();
    }

    public String getCityName(int playerId) {
        switch (playerId) {
        case 1 :
            if (cityNamesEgyptCounter >= 7) {
                cityNamesEgyptCounter++;
                return "New " + cityNamesEgypt[cityNamesEgyptCounter - 7 - 1];
            }
            cityNamesEgyptCounter++;
            return cityNamesEgypt[cityNamesEgyptCounter - 1];

        case 2 :
            if (cityNamesHittitesCounter >= 7) {
                cityNamesHittitesCounter++;
                return "New " + cityNamesHittites[cityNamesHittitesCounter - 7 - 1];
            }
            cityNamesHittitesCounter++;
            return cityNamesHittites[cityNamesHittitesCounter - 1];

        case 3 :
            if (cityNamesAssyriaCounter >= 7) {
                cityNamesAssyriaCounter++;
                return "New " + cityNamesAssyria[cityNamesAssyriaCounter - 7 - 1];
            }
            cityNamesAssyriaCounter++;
            return cityNamesAssyria[cityNamesAssyriaCounter - 1];
        }

        return null;
    }

    @Override
    public int getDyes() {
        return dices + getProvincesDices();
    }

    @Override
    public int getFood() {
        return food + getProvincesFood() - population;
    }

    public int getFoodBeforePop() {
        return food + getProvincesFood();
    }

    @Override
    public double getGold() {
        return gold + getProvincesGold();
    }

    @Override
    public int getHorses() {
        return horses + getProvincesHorses();
    }

    @Override
    public int getIron() {
        return iron + getProvincesIron();
    }

    public String getName() {
        return name;
    }

    private int getProvincesBelief() {
        int retBelief = 0;

        for (Province province : provincelist) {
            retBelief += province.getBelief();
        }

        return retBelief;
    }

    private int getProvincesBronze() {
        int retBronze = 0;

        for (Province province : provincelist) {
            retBronze += province.getBronze();
        }

        return retBronze;
    }

    private int getProvincesDices() {
        int retDices = 0;

        for (Province province : provincelist) {
            retDices += province.getDyes();
        }

        return retDices;
    }

    private int getProvincesFood() {
        int retFood = 0;

        for (Province province : provincelist) {
            retFood += province.getFood();
        }

        return retFood;
    }

    private int getProvincesGold() {
        int retGold = 0;

        for (Province province : provincelist) {
            retGold += province.getGold();
        }

        return retGold;
    }

    private int getProvincesHorses() {
        int retHorses = 0;

        for (Province province : provincelist) {
            retHorses += province.getHorses();
        }

        return retHorses;
    }

    private int getProvincesIron() {
        int retIron = 0;

        for (Province province : provincelist) {
            retIron += province.getIron();
        }

        return retIron;
    }

    private int getProvincesWood() {
        int retWood = 0;

        for (Province province : provincelist) {
            retWood += province.getWood();
        }

        return retWood;
    }

    @Override
    public int getWood() {
        return wood + getProvincesWood();
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    Army army1 = new Army();
    Army army2 = new Army();
    public Siege siege = new Siege();

    public class Siege{

        public Army atackingArmy;
        public Army defendingArmy;
        int atackStrengthInitial=0;
        int defenseStrengthInitial=0;

        int atackCloseDamageInitial;
        int defenseCloseDamageInitial;

        int atackFarDamageInitial;
        int defenseFarDamageInitial;

        int atackCloseDefenceInitial;
        int defenceCloseDefenceInitial=5;

        int atackFarDefenceInitial;
        int defenceFarDefenceInitial=2;


        int atackStrength;
        int defenseStrength;
        int atackCloseDamage;
        int defenseCloseDamage;
        int atackFarDamage;
        int defenseFarDamage;
        int atackCloseDefence;
        int defenceCloseDefence;
        int atackFarDefence;
        int defenceFarDefence;


        public double getDefenseStrength() {
            System.out.println(defenseStrength+ " "+ defenseStrengthInitial);
            return (double)defenseStrength/defenseStrengthInitial*100;
        }

        public double getAtackStrength() {
            return (double)atackStrength/atackStrengthInitial*100;
        }

        public int getAtackCloseDamage() {
            return atackCloseDamage;
        }

        public int getDefenseCloseDamage() {
            return defenseCloseDamage;
        }

        public int getAtackFarDamage() {
            return atackFarDamage;
        }

        public int getDefenseFarDamage() {
            return defenseFarDamage;
        }

        public int getAtackCloseDefence() {
            return atackCloseDefence;
        }

        public int getDefenceCloseDefence() {
            return defenceCloseDefence;
        }

        public int getAtackFarDefence() {
            return atackFarDefence;
        }

        public int getDefenceFarDefence() {
            return defenceFarDefence;
        }



//        Siege(Army atackingArmy, Army defendingArmy){
//            this.atackingArmy=atackingArmy;
//            this.defendingArmy=defendingArmy;
//        }
        Siege(){
            atackingArmy=new Army();
            defendingArmy=new Army();

            atackingArmy.addUnit(new Archer());
            atackingArmy.addUnit(new Infantry());
            atackingArmy.addUnit(new Chariots());
            atackingArmy.addUnit(new Chariots());

            defendingArmy.addUnit(new Archer());
            defendingArmy.addUnit(new Infantry());
            defendingArmy.addUnit(new Chariots());

            for(ArmyUnit unit: atackingArmy.getUnits()){
                atackStrengthInitial+=unit.getLife();
                atackCloseDamageInitial+=unit.getCloseAttack();
                atackFarDamageInitial+=unit.getFarAttack();
                atackCloseDefenceInitial+=unit.getCloseDefence();
                atackFarDefenceInitial+= unit.getFarDefence();
            }
            for (ArmyUnit unit:defendingArmy.getUnits()){
                defenseStrengthInitial+=unit.getLife();
                defenseStrengthInitial+=unit.getCloseAttack();
                defenseStrengthInitial+=unit.getFarAttack();
                defenseStrengthInitial+=unit.getCloseDefence();
                defenseStrengthInitial+= unit.getFarDefence();
            }

            atackStrength=atackStrengthInitial;
            defenseStrength=defenseStrengthInitial;

            recalculateDamage();

        }


        void recalculateDamage(){
            atackCloseDamage=atackCloseDamageInitial*atackStrength;
            defenseCloseDamage=defenseCloseDamageInitial*defenseStrength;
            atackFarDamage=atackFarDamageInitial*atackStrength;
            defenseFarDamage=defenseFarDamageInitial*defenseStrength;
            atackCloseDefence=atackCloseDefenceInitial*atackStrength;
            defenceCloseDefence=defenceCloseDefenceInitial*defenseStrength;
            atackFarDefence=atackFarDefenceInitial*atackStrength;
            defenceFarDefence=defenceFarDefenceInitial*defenseStrength;
        }




        }










}
