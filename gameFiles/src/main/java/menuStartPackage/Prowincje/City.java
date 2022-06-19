package menuStartPackage.Prowincje;

import menuStartPackage.Jednostki.*;
import menuStartPackage.player.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import static menuStartPackage.FXMLControllers.MainBoardController.playerList;

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
        defArmy.setName("Base army");

        defArmy.setIsDeafult();

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
        if(siege==null){
            return;
        }
        siege.turnPasses();
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



    public void setSiege(Siege siege){
        this.siege=siege;
    }

    void defendersVictory(){
        siege=null;
    }
    void attackersVictory(Army army, int id){
        this.army.clear();
        this.army.add(army);
        Player player;
        for(Player player1:playerList){
            if(player1.id==id){
                player=player1;
                player.createNewCity(this);
            }
            if(player1.id==ownerId){
                for(City city:player1.getCityList()){
                    if(city==this){
                        player1.getCityList().remove(this);
                    }
                }
            }
        }




        ownerId=id;
        provincelist.forEach(p->p.setOwnerId(id));
        siege=null;


    }
    public Siege siege = new Siege(2);



    public class Siege{

        Siege(int id){
            atackingArmy=new Army();
            defendingArmy=new Army();
            atackingArmy.addUnit(new Chariots());
            atackingArmy.addUnit(new Chariots());
            atackingArmy.addUnit(new Chariots());
            atackingArmy.addUnit(new Archer());
            defendingArmy.addUnit(new Chariots());
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
                defenseCloseDamageInitial+=unit.getCloseAttack();
                defenseFarDamageInitial+=unit.getFarAttack();
                defenseCloseDefenceInitial+=unit.getCloseDefence();
                defenseFarDefenceInitial+= unit.getFarDefence();
            }
            atackStrengthInitial*=2;
            defenseStrengthInitial*=2;

            atackStrength=atackStrengthInitial;
            defenseStrength=defenseStrengthInitial;
            recalculate();
            attackerId=id;

        }


        public Army atackingArmy;
        public Army defendingArmy;

        public int attackerId;




        public double getAtackCloseDamageInitial() {
            return round(atackCloseDamageInitial,1);
        }

        public double getDefenseCloseDamageInitial() {
            return round(defenseCloseDamageInitial,1);
        }

        public double getAtackFarDamageInitial() {
            return round(atackFarDamageInitial,1);
        }

        public double getDefenseFarDamageInitial() {
            return round(defenseFarDamageInitial,1);
        }

        public double getAtackCloseDefenceInitial() {
            return round(atackCloseDefenceInitial,1);
        }

        public double getDefenseCloseDefenceInitial() {
            return round(defenseCloseDefenceInitial,1);
        }

        public double getAtackFarDefenceInitial() {
            return round(atackFarDefenceInitial,1);
        }

        public double getDefenseFarDefenceInitial() {
            return round(defenseFarDefenceInitial,1);
        }

        int atackStrengthInitial=0;
        int defenseStrengthInitial=50;

        double atackCloseDamageInitial=0;
        double defenseCloseDamageInitial=0;

        double atackFarDamageInitial=0;
        double defenseFarDamageInitial=0;

        double atackCloseDefenceInitial=0;
        double defenseCloseDefenceInitial=5;

        double atackFarDefenceInitial=0;
        double defenseFarDefenceInitial=2;


        double atackStrength;
        double defenseStrength;
        double atackCloseDamage;
        double defenseCloseDamage;
        double atackFarDamage;
        double defenseFarDamage;
        double atackCloseDefence;
        double defenseCloseDefence;
        double atackFarDefence;
        double defenseFarDefence;

        public double round(double value, int places) {
            if (places < 0) throw new IllegalArgumentException();

            long factor = (long) Math.pow(10, places);
            value = value * factor;
            long tmp = Math.round(value);
            return (double) tmp / factor;
        }


        public double getDefenseStrength() {
            System.out.println(defenseStrength+ " "+ defenseStrengthInitial);
            return round((double)defenseStrength/defenseStrengthInitial*100,1);
        }

        public double getAtackStrength() {
            return round((double)atackStrength/atackStrengthInitial*100,1);
        }

        public double getAtackCloseDamage() {
            return round( atackCloseDamage,1);
        }

        public double getDefenseCloseDamage() {
            return round(defenseCloseDamage,1);
        }

        public double getAtackFarDamage() {
            return round(atackFarDamage,1);
        }

        public double getDefenseFarDamage() {
            return round(defenseFarDamage,1);
        }

        public double getAtackCloseDefence() {
            return round(atackCloseDefence,1);
        }

        public double getDefenceCloseDefence() {
            return round(defenseCloseDefence,1);
        }

        public double getAtackFarDefence() {
            return round(atackFarDefence,1);
        }

        public double getDefenceFarDefence() {
            return round(defenseFarDefence,1);
        }



//        Siege(Army atackingArmy, Army defendingArmy){
//            this.atackingArmy=atackingArmy;
//            this.defendingArmy=defendingArmy;
//        }


        void recalculate(){
            atackCloseDamage=atackCloseDamageInitial*atackStrength/atackStrengthInitial;
            defenseCloseDamage=defenseCloseDamageInitial*defenseStrength/defenseStrengthInitial;
            atackFarDamage=atackFarDamageInitial*atackStrength/atackStrengthInitial;
            defenseFarDamage=defenseFarDamageInitial*defenseStrength/defenseStrengthInitial;
            atackCloseDefence=atackCloseDefenceInitial*atackStrength/atackStrengthInitial;
            defenseCloseDefence=defenseCloseDefenceInitial*defenseStrength/defenseStrengthInitial;
            atackFarDefence=atackFarDefenceInitial*atackStrength/atackStrengthInitial;
            defenseFarDefence=defenseFarDefenceInitial*defenseStrength/defenseStrengthInitial;

        }

        int calculateDefendersCasualties(){
            int damage=0;
            damage += atackCloseDamage - defenseCloseDefence;
            damage += atackFarDamage - defenseFarDefence;
            return damage;
        }

        int calculateAtackersCasualties(){
            int damage=0;
            damage += defenseCloseDamage - atackCloseDefence;
            damage += defenseFarDamage - atackFarDefence;
            return damage;
        }
        public int lastRng=-1;


        public String getLastRng(){
            switch (lastRng) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    return "Roll " + lastRng + " - Cadia Stands!";
                case 5:
                case 6:
                    return "Roll " + lastRng + " - Plague!";
                case 7:
                case 8:
                    return "Roll " + lastRng + " - Water Shortage!";
                case 9:
                    return "Roll " + lastRng + " - Fire!";
                case 10:
                    return "Roll " + lastRng + " - Defenders Desert!";
                case -1:
                    return "Army Arrived! Siege is not yet started!";
            }
            return "";
        }


        public double  defCasualties;
        public double  atkCasualties;

        void turnPasses(){
            double defModifier;
            double atkModifier;
            atackStrength*=0.95;
            defenseStrength*=0.9;

            lastRng=(int)(Math.random()*10);
            switch (lastRng){
                case 0:
                    defModifier=2;
                    atkModifier=0;
                    break;
                case 1:
                    defModifier=1.6;
                    atkModifier=0;
                    break;
                case 2:
                    defModifier=1.2;
                    atkModifier=0;
                    break;
                case 3:
                    defModifier=1;
                    atkModifier=0.1;
                    break;
                case 4:
                    defModifier=1;
                    atkModifier=0.4;
                    break;
                case 5:
                    defModifier=1;
                    atkModifier=0.8;
                    break;
                case 6:
                    defModifier=0.9;
                    atkModifier=1.2;
                    break;
                case 7:
                    defModifier=0.8;
                    atkModifier=1.8;
                    break;
                case 8:
                    defModifier=0.7;
                    atkModifier=2.4;
                    break;
                case 9:
                    defModifier=0.6;
                    atkModifier=3.2;
                    break;
                default:
                    defModifier=0.5;
                    atkModifier=4;
                    break;
            }

            defCasualties=round(Math.abs(calculateDefendersCasualties()*atkModifier),1);
            atkCasualties=Math.abs(calculateAtackersCasualties()*defModifier);

            atackStrength-=atkCasualties;
            defenseStrength-=defCasualties;
            recalculate();

            if(atackStrength/atackStrengthInitial<0.25){
                defendersVictory();
            }
            if(defenseStrength/defenseStrengthInitial<0.20){
                attackersVictory(atackingArmy,attackerId);
            }


        }

        public String getDefenceStrenghtInitial() {
            return round(defenseStrengthInitial,1)+"";
        }

        public String getDefenceStrenghtMean() {
            return round(defenseStrength,1)+"";
        }

        public String getAttackStrenghtInitial() {
            return round(atackStrengthInitial,1)+"";
        }

        public String getAtackStrenghtMean() {
            return round(atackStrength,1)+"";
        }
    }
}
