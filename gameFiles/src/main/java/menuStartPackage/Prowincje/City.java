package menuStartPackage.Prowincje;



import java.util.Arrays;
import java.util.List;

public class City extends Province {
    List<String> resources = List.of();
    String type = "City";
    List<String> possibleBuildings = List.of();
    List<String> baseBuildings = Arrays.asList("Dzielnica mieszkaniowa", "Targ", "Koszary", "Świątynia", "Magazyn");

    @Override
    public String iconPath(){return "provinceIcons/city.png";}

    public City(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
