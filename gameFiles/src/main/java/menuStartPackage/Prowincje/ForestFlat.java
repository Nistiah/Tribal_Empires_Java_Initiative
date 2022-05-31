package menuStartPackage.Prowincje;

import java.util.Arrays;
import java.util.List;

public class ForestFlat extends Province {
    private List<String> resources         = Arrays.asList("drewno", "dziczyzna");
    private String       type              = "ForestFlat";
    private List<String> possibleBuildings = Arrays.asList("Tartak", "Złapanie dzików", "Wycięcie lasu");
    private List<String> baseBuildings     = List.of("Łowca");

    public ForestFlat() {
        setResources(getResources());
        setType(getType());
        setBaseProduction(getType());
        setPossibleBuildings(getPossibleBuildings());
        setBaseBuildings(getBaseBuildings());
    }

    @Override
    public String iconPath() {
        if (ownerId != 0) {
            return "provinceIcons/forest - flat.png";
        } else {
            return "provinceIcons/forest - flat (kopia).png";
        }
    }

    public List<String> getBaseBuildings() {
        return baseBuildings;
    }

    @Override
    public void setBaseBuildings(List<String> baseBuildings) {
        this.baseBuildings = baseBuildings;
    }

    public List<String> getPossibleBuildings() {
        return possibleBuildings;
    }

    @Override
    public void setPossibleBuildings(List<String> possibleBuildings) {
        this.possibleBuildings = possibleBuildings;
    }

    public List<String> getResources() {
        return resources;
    }

    @Override
    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }
}
