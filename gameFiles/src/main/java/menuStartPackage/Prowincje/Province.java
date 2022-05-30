package menuStartPackage.Prowincje;

import java.util.List;

public class Province {
    public int i;
    public int j;
    public int ownerId;
    String type;
    public List<String> resources;
    public List<String> possibleBuildings;
    List<String> baseBuildings;

    public int gold = 0;
    public int belief = 0;
    public int food = 0;
    int bronze = 0;
    int iron = 0;
    int dices = 0;
    int horses = 0;
    public int wood = 0;

    public int getGold() {
        return gold;
    }

    public int getBelief() {
        return belief;
    }

    public int getFood() {
        return food;
    }

    public int getBronze() {
        return bronze;
    }

    public int getIron() {
        return iron;
    }

    public int getDices() {
        return dices;
    }

    public int getHorses() {
        return horses;
    }

    public int getWood() {
        return wood;
    }

    public String iconPath(){return null;}

    public void setBaseBuildings(List<String> baseBuildings) {
        this.baseBuildings = baseBuildings;
    }

    public void setPossibleBuildings(List<String> possibleBuildings) {
        this.possibleBuildings = possibleBuildings;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public void setCoordinates(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {return this.type;}

    public void setBaseProduction(String type){
        switch(type){
            case "Sea":
                food = 1;
                break;
            case "DesertFlat":
                belief = 1;
                break;
            case "DesertWyzyny":
                belief = 1;
                break;
            case "TrawaFlat":
                food = 1;
                break;
            case "TrawaWyzyny":
                food = 1;
                break;
            case "ForestFlat":
                wood = 1;
                break;
            case "ForestWyzyny":
                wood = 1;
                break;
            case "Mountains":
                break;
            case "Coast":
                food = 1;
                break;
            case "RiversideArea":
                food = 2;
                break;
            case "City":
                food = 1;
                gold = 1;
                break;
        }
    }
}
