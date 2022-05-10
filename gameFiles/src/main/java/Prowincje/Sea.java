package Prowincje;

import Prowincje.Province;

import java.util.Arrays;
import java.util.List;

public class Sea extends Province {

    List<String> resources = Arrays.asList("ryby", "barwiniki");
    String type = "Morze";
    List<String> possibleBuildings = Arrays.asList("Poławiacze ryb", "Poławiacze szkarłatników");
    List<String> baseBuildings = Arrays.asList();


    Sea(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
