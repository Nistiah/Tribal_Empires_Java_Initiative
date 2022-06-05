package menuStartPackage.Prowincje;

import java.util.Arrays;
import java.util.List;

public class DesertWyzyny extends Province {
    private List<String> resources         = Arrays.asList("brąz", "żelazo", "złoto");
    private String       type              = "DesertWyzyny";
    private List<String> possibleBuildings = Arrays.asList("Bronze Mine", "Iron Mine", "Gold Mine");
    private List<String> baseBuildings     = Arrays.asList("Pyramid", "Caravan");

    public DesertWyzyny() {
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(getPossibleBuildings());
        setBaseBuildings(baseBuildings);
    }

    @Override
    public String iconPath() {
        if (ownerId != 0) {
            return "provinceIcons/desert - wyz.png";
        } else {
            return "provinceIcons/desert - wyz (kopia).png";
        }
    }

    public List<String> getPossibleBuildings() {
        return possibleBuildings;
    }

    @Override
    public void setPossibleBuildings(List<String> possibleBuildings) {
        this.possibleBuildings = possibleBuildings;
    }
}
