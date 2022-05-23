package menuStartPackage.Prowincje;
import java.util.Arrays;
import java.util.List;

public class Sea extends Province {

    List<String> resources = Arrays.asList("ryby", "barwiniki");
    String type = "Morze";
    List<String> possibleBuildings = Arrays.asList("Poławiacze ryb", "Poławiacze szkarłatników");
    List<String> baseBuildings = Arrays.asList();

    @Override
    public String iconPath(){return "../../resources/menuStartPackage/FXMLControllers/province_icons/sea2.jpg";}

    Sea(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
