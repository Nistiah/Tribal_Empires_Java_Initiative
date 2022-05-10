package Prowincje;

import Prowincje.Province;

import java.util.Arrays;
import java.util.List;

public class Coast extends Province {
    List<String> resources = Arrays.asList("bursztyn", "owoce morza");
    String type = "Wybrzeze";
    List<String> possibleBuildings = Arrays.asList("Zbieracze bursztynu", "Zbieracze owoców morza");
    List<String> baseBuildings = List.of("latarnia");


    Coast(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
