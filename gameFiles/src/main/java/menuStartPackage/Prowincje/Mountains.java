package menuStartPackage.Prowincje;

import java.util.Arrays;
import java.util.List;

public class Mountains extends Province {
    private List<String> resources         = List.of();
    private String       type              = "Mountains";
    private List<String> possibleBuildings = List.of();
    private List<String> baseBuildings     = List.of();

    public Mountains() {
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }

    @Override
    public String iconPath() {
        if (ownerId != 0) {
            return "provinceIcons/mountain.png";
        } else {
            return "provinceIcons/mountain (kopia).png";
        }
    }
}
