import java.util.Arrays;
import java.util.List;

public class ForestFlat extends Province{
    List<String> resources = Arrays.asList("drewno", "dziczyzna");
    String type = "Las flat";
    List<String> possibleBuildings = Arrays.asList("Tartak", "Złapanie dzików", "Wycięcie lasu");
    List<String> baseBuildings = List.of("Łowca");


    ForestFlat(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
