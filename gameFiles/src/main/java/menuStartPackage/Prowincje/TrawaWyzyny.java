package menuStartPackage.Prowincje;

import java.util.Arrays;
import java.util.List;

public class TrawaWyzyny extends Province {
    private List<String> resources         = Arrays.asList("brąz", "żelazo", "złoto");
    private String       type              = "TrawaWyzyny";
    private List<String> possibleBuildings = Arrays.asList("Kopalnia brąz",
                                                           "Kopalnia żelazo",
                                                           "Kopalnia złoto",
                                                           "Hodowla krów",
                                                           "Hodowla świń");
    private List<String> baseBuildings = List.of("Farma");

    public TrawaWyzyny() {
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }

    @Override
    public String iconPath() {
        if (ownerId != 0) {
            return "provinceIcons/trawa - wyz2.png";
        } else {
            return "provinceIcons/trawa - wyz2 (kopia).png";
        }
    }
}