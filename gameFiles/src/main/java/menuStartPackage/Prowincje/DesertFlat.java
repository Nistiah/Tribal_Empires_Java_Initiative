package menuStartPackage.Prowincje;

import java.util.Arrays;
import java.util.List;

public class DesertFlat extends Province {
    private List<String> resources         = Arrays.asList();
    private String       type              = "DesertFlat";
    private List<String> possibleBuildings = Arrays.asList();
    private List<String> baseBuildings     = Arrays.asList("Piramida", "Karawana");

    public DesertFlat() {
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }

    @Override
    public String iconPath() {
        if (ownerId != 0) {
            return "provinceIcons/desert - flat.png";
        } else {
            return "provinceIcons/desert - flat (kopia).png";
        }
    }
}
