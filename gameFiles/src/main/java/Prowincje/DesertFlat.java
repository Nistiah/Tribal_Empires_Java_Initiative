import java.util.Arrays;
import java.util.List;

public class DesertFlat extends Province{
    List<String> resources = Arrays.asList();
    String type = "Pustynia flat";
    List<String> possibleBuildings = Arrays.asList();
    List<String> baseBuildings = Arrays.asList("Piramida", "Karawana");


    DesertFlat(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
