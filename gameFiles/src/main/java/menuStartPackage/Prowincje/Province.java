package menuStartPackage.Prowincje;

import java.util.ArrayList;
import java.util.List;

public class Province {
    protected int        gold   = 0;
    protected int        belief = 0;
    protected int        food   = 0;
    protected int        bronze = 0;
    protected int        iron   = 0;
    protected int        dices  = 0;
    protected int        horses = 0;
    protected int        wood   = 0;
    private int          i;
    private int          j;
    protected int        ownerId;
    private String       type;
    private List<String> resources;
    private List<String> possibleBuildings;
    List<String>         baseBuildings;
    public List<String>  builtBuildings = new ArrayList<>();

    public String iconPath() {
        return null;
    }

    public void setBaseBuildings(List<String> baseBuildings) {
        this.baseBuildings = baseBuildings;
    }

    public List<String> getBaseBuildings() {
        return baseBuildings;
    }

    public void setBaseProduction(String type) {
        switch (type) {
        case "Sea" :
            food = 1;

            break;

        case "DesertFlat" :
            belief = 1;

            break;

        case "DesertWyzyny" :
            belief = 1;

            break;

        case "TrawaFlat" :
            food = 1;

            break;

        case "TrawaWyzyny" :
            food = 1;

            break;

        case "ForestFlat" :
            wood = 1;

            break;

        case "ForestWyzyny" :
            wood = 1;

            break;

        case "Mountains" :
            break;

        case "Coast" :
            food = 1;

            break;

        case "RiversideArea" :
            food = 2;

            break;

        case "City" :
            food = 1;
            gold = 1;

            break;
        }
    }

    public int getBelief() {
        return belief;
    }

    public int getBronze() {
        return bronze;
    }

    public void setCoordinates(int i, int j) {
        this.setI(i);
        this.j = j;
    }

    public int getDyes() {
        return dices;
    }

    public int getFood() {
        return food;
    }

    public int getGold() {
        return gold;
    }

    public int getHorses() {
        return horses;
    }

    public int getI() {
        return i;
    }

    protected void setI(int i) {
        this.i = i;
    }

    public int getIron() {
        return iron;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int x) {
        ownerId = x;
    }

    public List<String> getPossibleBuildings() {
        return possibleBuildings;
    }

    public void setPossibleBuildings(List<String> possibleBuildings) {
        this.possibleBuildings = possibleBuildings;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWood() {
        return wood;
    }
}
