package menuStartPackage.Prowincje;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class City extends Province {
    private List<String> resources         = List.of();
    private String       type              = "City";
    private List<String> possibleBuildings = List.of();
    private List<String> baseBuildings     = Arrays.asList("Residential District",
                                                           "Market",
                                                           "Barracks",
                                                           "Temple",
                                                           "Warehouse");
    private Vector<Province> provincelist = new Vector<>();
    private String           name         = "xyz";

    public City() {
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }

    public void assignProvince(Province province) {
        provincelist.add(province);
    }

    @Override
    public String iconPath() {
        return "provinceIcons/city.png";
    }

    @Override
    public int getBelief() {
        return belief + getProvincesBelief();
    }

    @Override
    public int getBronze() {
        return bronze + getProvincesBronze();
    }

    @Override
    public int getDyes() {
        return dices + getProvincesDices();
    }

    @Override
    public int getFood() {
        return food + getProvincesFood();
    }

    @Override
    public int getGold() {return gold + getProvincesGold();}

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
}
