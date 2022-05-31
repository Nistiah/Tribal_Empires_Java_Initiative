package menuStartPackage.Prowincje;

import java.util.Arrays;
import java.util.List;

public class TrawaFlat extends Province {
    private List<String> resources         = Arrays.asList("bydło", "konie");
    private String       type              = "TrawaFlat";
    private List<String> possibleBuildings = Arrays.asList("Hodowla krów", "Hodowla świń", "Hodowla koni");
    private List<String> baseBuildings     = List.of("Farma");

    public TrawaFlat() {
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }

    @Override
    public String iconPath() {
        if (ownerId != 0) {
            return "provinceIcons/trawa - flat3.png";
        } else {
            return "provinceIcons/trawa - flat3 (kopia).png";
        }
    }
}
