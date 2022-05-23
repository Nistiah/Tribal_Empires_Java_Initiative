package menuStartPackage.Prowincje;
import java.util.Arrays;
import java.util.List;

public class TrawaFlat extends Province{
    List<String> resources = Arrays.asList("bydło", "konie");
    String type = "Trawa flat";
    List<String> possibleBuildings = Arrays.asList("Hodowla krów", "Hodowla świń", "Hodowla koni");
    List<String> baseBuildings = List.of("Farma");


    TrawaFlat(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
