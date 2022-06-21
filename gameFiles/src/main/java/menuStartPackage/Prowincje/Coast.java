package menuStartPackage.Prowincje;

import java.util.Arrays;
import java.util.List;

public class Coast extends Province {
    private List<String> resources         = Arrays.asList("bursztyn", "owoce morza");
    private String       type              = "Coast";
    private List<String> possibleBuildings = Arrays.asList("Amber Collector", "Sea Food Collector");
    private List<String> baseBuildings     = List.of();

    public Coast() {
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }

    @Override
    public String iconPath() {
        if (ownerId != 0) {
            return "provinceIcons/wybrzeze.jpg";
        } else {
            return "provinceIcons/wybrzeze (kopia).jpg";
        }
    }
}
