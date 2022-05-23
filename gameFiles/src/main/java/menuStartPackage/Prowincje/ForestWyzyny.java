package menuStartPackage.Prowincje;

import java.util.Arrays;
import java.util.List;
public class ForestWyzyny extends Province {
    List<String> resources = Arrays.asList("drewno", "brąz", "żelazo", "złoto");
    String type = "Las wyzyny";
    List<String> possibleBuildings = Arrays.asList("Tartak", "Kopalnia brąz", "Kopalnia żelazo", "Kopalnia złoto", "Wycięcie lasu");
    List<String> baseBuildings = List.of("Łowca");

    @Override
    public String iconPath(){return "../../resources/menuStartPackage/FXMLControllers/province_icons/forest - wyz.png";}

    ForestWyzyny(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
