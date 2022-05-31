package menuStartPackage.Prowincje;

import java.util.List;

public class RiversideArea extends Province {
    private List<String> resources         = List.of();
    private String       type              = "RiversideArea";
    private List<String> possibleBuildings = List.of("System irygacji");
    private List<String> baseBuildings     = List.of("Farma nadrzeczna");

    public RiversideArea() {
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }

    @Override
    public String iconPath() {
        if (ownerId != 0) {
            return "provinceIcons/river2.png";
        } else {
            return "provinceIcons/river2 (kopia).png";
        }
    }
}