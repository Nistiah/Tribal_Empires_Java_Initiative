package menuStartPackage.Prowincje;

import java.util.Arrays;
import java.util.List;
public class ForestFlat extends Province {
    List<String> resources = Arrays.asList("drewno", "dziczyzna");
    String type = "LasFlat";
    List<String> possibleBuildings = Arrays.asList("Tartak", "Złapanie dzików", "Wycięcie lasu");
    List<String> baseBuildings = List.of("Łowca");

    @Override
    public String iconPath(){return "../../../resources/menuStartPackage/FXMLControllers/provinceIcons/forest - flat.png";}

    public ForestFlat() {


        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);

    }
}
