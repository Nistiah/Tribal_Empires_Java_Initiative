package Prowincje;

import java.util.List;

public class Province {
    int i;
    int j;
    String type;
    List<String> resources;
    List<String> possibleBuildings;
    List<String> baseBuildings;
    int belief = 0, wood = 0, gold = 0, food = 0;

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

    public void setBaseProduction(String type){
        switch(type){
            case "Morze":
                food = 1;
                break;
            case "Pustynia flat":
                belief = 1;
                break;
            case "Pustynia wyzyny":
                belief = 1;
                break;
            case "Trawa flat":
                food = 1;
                break;
            case "Trawa wyzyny":
                food = 1;
                break;
            case "Las flat":
                wood = 1;
                break;
            case "Las wyzyny":
                wood = 1;
                break;
            case "Gory":
                break;
            case "Wybrzeze":
                food = 1;
                break;
            case "Teren nadrzeczny":
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
