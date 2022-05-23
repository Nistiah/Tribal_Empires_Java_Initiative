package menuStartPackage.Prowincje;



import java.util.Arrays;
import java.util.List;

public class Coast extends Province {
    List<String> resources = Arrays.asList("bursztyn", "owoce morza");
    String type = "Wybrzeze";
    String iconPath = "../../resources/menuStartPackage/FXMLControllers/province_icons/wybrzeze.jpg";
    List<String> possibleBuildings = Arrays.asList("Zbieracze bursztynu", "Zbieracze owoców morza");
    List<String> baseBuildings = List.of("latarnia");

    @Override
    public String iconPath(){return "../../resources/menuStartPackage/FXMLControllers/province_icons/wybrzeze.jpg";}

    Coast(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
