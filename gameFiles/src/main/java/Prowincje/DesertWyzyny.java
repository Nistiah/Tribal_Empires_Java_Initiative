import java.util.Arrays;
import java.util.List;

public class DesertWyzyny extends Province{
    List<String> resources = Arrays.asList("brąz", "żelazo", "złoto");
    String type = "Pustynia wyzyny";
    List<String> possibleBuildings = Arrays.asList("Kopalnia brąz", "Kopalnia żelazo", "Kopalnia złoto");
    List<String> baseBuildings = Arrays.asList("Piramida", "Karawana");


    DesertWyzyny(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
