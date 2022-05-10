package menuStartPackage.Prowincje;

import java.util.Arrays;
import java.util.List;

public class City extends Province{
    List<String> resources = List.of();
    String type = "Miasto";
    List<String> possibleBuildings = List.of();
    List<String> baseBuildings = Arrays.asList("Dzielnica mieszkaniowa", "Targ", "Koszary", "Świątynia", "Magazyn");


    public City(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }

}
