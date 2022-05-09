package menuStartPackage.Prowincje;
import java.util.List;

public class RiversideArea extends Province{
    List<String> resources = List.of();
    String type = "Teren nadrzeczny";
    List<String> possibleBuildings = List.of("System irygacji");
    List<String> baseBuildings = List.of("Farma nadrzeczna");


    RiversideArea(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
