package menuStartPackage.Prowincje;


import java.util.Arrays;
import java.util.List;

public class TrawaWyzyny extends Province {
    List<String> resources = Arrays.asList("brąz", "żelazo", "złoto");
    String type = "Trawa wyzyny";
    List<String> possibleBuildings = Arrays.asList("Kopalnia brąz", "Kopalnia żelazo", "Kopalnia złoto", "Hodowla krów", "Hodowla świń");
    List<String> baseBuildings = List.of("Farma");

    @Override
    public String iconPath(){return "../../resources/menuStartPackage/FXMLControllers/province_icons/trawa - wyz2.png";}

    TrawaWyzyny(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
