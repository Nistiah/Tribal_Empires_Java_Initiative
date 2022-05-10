package Prowincje;

import Prowincje.Province;

import java.util.List;

public class Mountains extends Province {
    List<String> resources = List.of();
    String type = "Gory";
    List<String> possibleBuildings = List.of();
    List<String> baseBuildings = List.of();


    Mountains(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
