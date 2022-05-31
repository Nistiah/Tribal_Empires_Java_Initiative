package menuStartPackage.Prowincje;

import java.util.Arrays;
import java.util.List;

public class ForestWyzyny extends Province {
    private List<String> resources         = Arrays.asList("drewno", "brąz", "żelazo", "złoto");
    private String       type              = "ForestWyzyny";
    private List<String> possibleBuildings = Arrays.asList("Tartak",
                                                           "Kopalnia brąz",
                                                           "Kopalnia żelazo",
                                                           "Kopalnia złoto",
                                                           "Wycięcie lasu");
    private List<String> baseBuildings = List.of("Łowca");

    public ForestWyzyny() {
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }

    @Override
    public String iconPath() {
        if (ownerId != 0) {
            return "provinceIcons/forest - wyz.png";
        } else {
            return "provinceIcons/forest - wyz (kopia).png";
        }
    }
}
