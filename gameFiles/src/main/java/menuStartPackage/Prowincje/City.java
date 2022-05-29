package menuStartPackage.Prowincje;



import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class City extends Province {
    List<String> resources = List.of();
    String type = "City";
    List<String> possibleBuildings = List.of();
    List<String> baseBuildings = Arrays.asList("Dzielnica mieszkaniowa", "Targ", "Koszary", "Świątynia", "Magazyn");
    Vector<Province> provincelist = new Vector<>();

    public void assignProvince(Province province){
        provincelist.add(province);
    }

    private int getProvincesGold() {
        int retGold = 0;
        for (Province province:provincelist) {
            retGold+=province.getGold();
        }
        return retGold;
    }

    private int getProvincesBelief() {
        int retBelief = 0;
        for (Province province:provincelist) {
            retBelief+=province.getBelief();
        }
        return retBelief;
    }

    private int getProvincesFood() {
        int retFood = 0;
        for (Province province:provincelist) {
            retFood+=province.getFood();
        }
        return retFood;
    }

    private int getProvincesBronze() {
        int retBronze = 0;
        for (Province province:provincelist) {
            retBronze+=province.getBronze();
        }
        return retBronze;
    }

    private int getProvincesIron() {
        int retIron = 0;
        for (Province province:provincelist) {
            retIron+=province.getIron();
        }
        return retIron;
    }

    private int getProvincesDices() {
        int retDices = 0;
        for (Province province:provincelist) {
            retDices+=province.getDices();
        }
        return retDices;
    }

    private int getProvincesHorses() {
        int retHorses = 0;
        for (Province province:provincelist) {
            retHorses+=province.getHorses();
        }
        return retHorses;
    }

    private int getProvincesWood() {
        int retWood = 0;
        for (Province province:provincelist) {
            retWood+=province.getWood();
        }
        return retWood;
    }

    @Override
    public int getGold() {
        System.out.println("gold:"+gold+"  provinces:"+getProvincesGold());
        return gold+getProvincesGold();
    }
    @Override
    public int getBelief() {
        return belief+getProvincesBelief();
    }
    @Override
    public int getFood() {
        return food+getProvincesFood();
    }
    @Override
    public int getBronze() {
        return bronze+getProvincesBronze();
    }
    @Override
    public int getIron() {
        return iron+getProvincesIron();
    }
    @Override
    public int getDices() {
        return dices+getProvincesDices();
    }
    @Override
    public int getHorses() {
        return horses+getProvincesHorses();
    }
    @Override
    public int getWood() {
        return wood+getProvincesWood();
    }

    @Override
    public String iconPath(){return "provinceIcons/city.png";}

    public City(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
