package menuStartPackage.Prowincje;
import java.util.Arrays;
import java.util.List;

public class DesertWyzyny extends Province {
    List<String> resources = Arrays.asList("brąz", "żelazo", "złoto");
    String type = "PustyniaWyzyny";
    List<String> possibleBuildings = Arrays.asList("Kopalnia brąz", "Kopalnia żelazo", "Kopalnia złoto");
    List<String> baseBuildings = Arrays.asList("Piramida", "Karawana");

    @Override
    public String iconPath(){return "../../../resources/menuStartPackage/FXMLControllers/provinceIcons/desert - wyz.png";}

    public DesertWyzyny(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
