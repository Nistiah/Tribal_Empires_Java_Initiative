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
    int belief = 0, wood = 0, gold = 0, food = 0;

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
            case "Morze":
                food = 1;
                break;
            case "PustyniaFlat":
                belief = 1;
                break;
            case "PustyniaWyzyny":
                belief = 1;
                break;
            case "TrawaFlat":
                food = 1;
                break;
            case "TrawaWyzyny":
                food = 1;
                break;
            case "LasFlat":
                wood = 1;
                break;
            case "LasWyzyny":
                wood = 1;
                break;
            case "Gory":
                break;
            case "Wybrzeze":
                food = 1;
                break;
            case "TerenNadrzeczny":
                food = 2;
                break;
            case "Miasto":
                food = 1;
                wood = 1;
                gold = 1;
                belief = 1;
                break;
        }
    }

}
