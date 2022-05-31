package menuStartPackage.Prowincje;

import java.util.Arrays;
import java.util.List;

public class Sea extends Province {
    private List<String> resources         = Arrays.asList("ryby", "barwiniki");
    private String       type              = "Sea";
    private List<String> possibleBuildings = Arrays.asList("Poławiacze ryb", "Poławiacze szkarłatników");
    private List<String> baseBuildings     = Arrays.asList();

    public Sea() {
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }

    @Override
    public String iconPath() {
        if (ownerId != 0) {
            return "provinceIcons/sea2.jpg";
        } else {
            return "provinceIcons/sea2 (kopia).jpg";
        }
    }
}